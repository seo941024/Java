# pro21 · Spring MVC 요청 매핑

DispatcherServlet이 `*.do` 요청을 받아 XML에 등록한 컨트롤러로 넘기고, ViewResolver가 뷰 이름으로 JSP 경로를 완성하는 Spring MVC 구조. DB 연결 없이 로그인·회원 가입 입력값을 결과 화면으로 전달.

이전 단계와 차이: pro17은 `@WebServlet("*.do")` 컨트롤러 서블릿이 `if` 문으로 기능을 나눴지만, pro21은 Spring의 DispatcherServlet이 요청을 받고 XML 설정의 URL 매핑과 메소드 이름 매핑으로 처리 메소드 결정.

## 다룬 개념

| 개념 | 뜻과 사용 방법 |
|---|---|
| Spring MVC | 요청 접수, 컨트롤러 선택, 뷰 연결을 Spring 프레임워크가 맡고 개발자는 컨트롤러와 화면을 작성하는 웹 MVC 구조 |
| DispatcherServlet | 모든 요청을 처음 받아 알맞은 컨트롤러에 넘기는 Spring의 중앙 서블릿. `web.xml`에 서블릿 이름 `action`, 주소 `*.do`로 등록하고, 이름 규칙에 따라 `action-servlet.xml` 설정을 읽음 |
| `Controller` 인터페이스 | `handleRequest()` 메소드 하나로 요청을 처리하는 컨트롤러 형식. SimpleUrlController |
| `SimpleUrlHandlerMapping` | 요청 URL과 컨트롤러 빈 이름을 짝지은 매핑. `/test/index.do`는 simpleUrlController, `/test/*.do`는 userController |
| `MultiActionController` | 한 컨트롤러 클래스 안에 여러 처리 메소드를 두는 컨트롤러 |
| `PropertiesMethodNameResolver` | 요청 URL마다 호출할 메소드 이름을 지정하는 객체. `/test/login.do`는 `login`, `/test/memberinfo.do`는 `memberinfo` |
| `ModelAndView` | 뷰에 넘길 값과 뷰 이름을 함께 담아 반환하는 객체. `addObject()`, `setViewName()` |
| `InternalResourceViewResolver` | 뷰 이름 앞뒤에 `prefix`와 `suffix`를 붙여 JSP 경로를 만드는 객체. `/test/` + 뷰 이름 + `.jsp` |
| `getViewName()` | 요청 URI에서 프로젝트 경로와 확장자를 잘라 뷰 이름을 만드는 메소드. `/pro21/test/memberinfo.do`에서 `/memberinfo` 추출 |

## 요청 흐름

회원 가입 정보 출력 기준.

1. `/pro21/test/memberForm.do` 요청으로 회원 가입 폼 표시
2. memberForm.jsp에서 아이디, 비밀번호, 이름, 이메일을 POST로 `/test/memberinfo.do` 전송
3. `web.xml`의 `*.do` 매핑으로 DispatcherServlet이 요청 수신
4. `userUrlMapping`의 `/test/*.do` 규칙으로 userController 선택
5. `userMethodNameResolver`가 `/test/memberinfo.do`를 `memberinfo` 메소드에 연결
6. `memberinfo()`가 네 값을 `ModelAndView`에 담고 `getViewName()`으로 만든 뷰 이름 지정
7. viewResolver가 `/test/` 접두어와 `.jsp` 접미어를 붙여 memberinfo.jsp를 찾고, JSP가 `${id}`, `${name}` 같은 EL로 값 출력

| 요청 주소 | 컨트롤러와 메소드 | 넘기는 값 | 뷰 |
|---|---|---|---|
| `/test/index.do` | SimpleUrlController `handleRequest()` | 없음 | 뷰 이름 `index.jsp` |
| `/test/login.do` | UserController `login()` | `userID`, `passwd` | login.jsp |
| `/test/memberForm.do` | UserController `memberForm()` | 없음 | memberForm.jsp |
| `/test/memberinfo.do` | UserController `memberinfo()` | `id`, `pwd`, `name`, `email` | memberinfo.jsp |

## 파일별 역할

| 파일 | 역할 | 핵심 코드 |
|---|---|---|
| [SimpleUrlController.java](src/main/java/com/spring/ex01/SimpleUrlController.java) | `Controller` 인터페이스를 구현해 뷰 이름 `index.jsp`를 담은 ModelAndView 반환 | `implements Controller`, `return new ModelAndView("index.jsp")` |
| [UserController.java](src/main/java/com/spring/ex02/UserController.java) | 로그인, 가입 폼, 가입 정보 처리 메소드와 뷰 이름 추출 메소드. 추출 단계마다 contextPath, uri, fileName을 콘솔 출력 | `extends MultiActionController`, `mav.addObject("userID", userID)`, `getViewName(request)` |
| [action-servlet.xml](src/main/webapp/WEB-INF/action-servlet.xml) | SimpleUrlController와 UserController 빈, URL 매핑, 메소드 이름 매핑, 뷰 리졸버 등록 | `SimpleUrlHandlerMapping`, `PropertiesMethodNameResolver`, `<property name="prefix" value="/test/" />` |
| [web.xml](src/main/webapp/WEB-INF/web.xml) | DispatcherServlet `action`을 `*.do`에 매핑, 서버 시작 시 생성 | `DispatcherServlet`, `<load-on-startup>1</load-on-startup>` |
| [test/index.jsp](src/main/webapp/test/index.jsp) | "Hello Spring" 문구 화면 | `<h1>index.jsp 파일 입니다.</h1>` |
| [test/loginForm.jsp](src/main/webapp/test/loginForm.jsp) | 아이디 `userID`, 비밀번호 `passwd`를 POST로 `/test/login.do` 전송하는 폼 | `action="${contextPath}/test/login.do"` |
| [test/login.jsp](src/main/webapp/test/login.jsp) | `/test/login.do`의 결과 화면, 아이디와 비밀번호를 `${id}`, `${pwd}`로 출력하는 표 | `${id}`, `${pwd}` |
| [test/result.jsp](src/main/webapp/test/result.jsp) | 아이디와 비밀번호를 `${userID}`, `${passwd}`로 출력하는 결과 표 | `${userID}`, `${passwd}` |
| [test/memberForm.jsp](src/main/webapp/test/memberForm.jsp) | 회원 가입 폼, POST로 `/test/memberinfo.do` 전송 | `action="${contextPath}/test/memberinfo.do"` |
| [test/memberinfo.jsp](src/main/webapp/test/memberinfo.jsp) | 컨트롤러가 넘긴 회원 정보를 표로 출력 | `${id}`, `${name}`, `${email}` |
