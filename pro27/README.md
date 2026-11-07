# pro27 · Tiles 공통 레이아웃과 로그인

Apache Tiles로 헤더, 사이드 메뉴, 본문, 푸터를 한 레이아웃에 조합하고, 로그인 성공 시 세션에 회원 정보를 저장해 헤더 문구를 바꾸는 회원 관리 화면. log4j로 컨트롤러 로그 기록.

이전 단계와 차이: pro26은 `InternalResourceViewResolver`가 뷰 이름을 JSP 파일 하나로 연결했지만, pro27은 `UrlBasedViewResolver`와 `TilesView`가 뷰 이름과 같은 Tiles 정의를 찾아 여러 JSP를 한 화면으로 조합. 뷰 이름 추출 메소드는 `.do` 앞까지만 잘라 `/member/listMembers` 형태로 반환.

## 다룬 개념

| 개념 | 뜻과 사용 방법 |
|---|---|
| Apache Tiles | 화면을 헤더, 메뉴, 본문, 푸터 조각으로 나누고 공통 틀 JSP에 조각을 끼워 한 페이지로 만드는 레이아웃 프레임워크 |
| Tiles 정의 | `tiles_member.xml`의 `<definition>`. `baseLayout`이 틀 JSP와 공통 조각을 정하고, 화면별 정의가 `extends="baseLayout"`으로 물려받아 제목과 본문만 교체 |
| `put-attribute`와 `tiles:insertAttribute` | 정의 파일에서 조각 이름과 JSP 경로를 지정하고, 틀 JSP의 해당 위치에서 그 조각 출력 |
| `TilesConfigurer` | Tiles 정의 파일 위치 `classpath:tiles/*.xml`을 Spring에 등록하는 빈 |
| `UrlBasedViewResolver`와 `TilesView` | 컨트롤러가 반환한 뷰 이름을 같은 이름의 Tiles 정의로 해석하는 뷰 설정. `main`, `/member/listMembers` |
| 세션 로그인 | 로그인 성공 시 `session.setAttribute("member", loginVO)`와 `isLogOn` 저장, 로그아웃 시 `removeAttribute()`로 삭제. 헤더 JSP가 세션 값으로 문구 전환 |
| 실패 결과 전달 | 로그인 실패 시 `redirect:/member/loginForm.do?result=loginFailed`, 폼 메소드가 `@RequestParam(value = "result", required = false)`로 받아 뷰에 전달, JSP가 알림창 표시 |
| `@ModelAttribute` 로그인 | 로그인 폼의 `id`, `pwd`를 MemberVO에 바로 담아 매퍼의 `parameterType="memberVO"` 조회에 사용 |
| log4j | 로그를 레벨별로 콘솔과 파일에 기록하는 라이브러리. `log4j.xml`에 콘솔 출력과 날짜별 파일 출력 설정, `logger.info()`로 뷰 이름 기록 |

## 요청 흐름

로그인 기준.

1. 헤더의 로그인 링크로 `/pro27/member/loginForm.do` GET 요청
2. `@RequestMapping("/member")` 클래스의 `/*Form.do` 메소드가 뷰 이름 `/member/loginForm` 반환
3. TilesView가 `/member/loginForm` 정의를 찾아 layout.jsp에 header.jsp, side.jsp, footer.jsp와 본문 loginForm.jsp를 넣어 화면 구성
4. 아이디와 비밀번호를 POST로 `/member/login.do` 전송, `@ModelAttribute("member")`가 MemberVO에 값 바인딩
5. `memberService.login()`이 `memberDAO.loginById()`를 호출해 매퍼 `loginById`의 `where id = #{id} and pwd = #{pwd}` 조회
6. 결과가 있으면 세션에 `member`, `isLogOn`을 저장하고 회원 목록으로 redirect, 헤더에 환영 문구와 로그아웃 링크 표시
7. 결과가 없으면 `result=loginFailed`를 붙여 로그인 창으로 redirect, 알림창 표시

| Tiles 정의 이름 | 제목 | 본문 JSP | 요청 주소 |
|---|---|---|---|
| `main` | 메인 페이지 | main.jsp | `/main.do` |
| `/member/listMembers` | 회원목록창 | member/listMembers.jsp | `/member/listMembers.do` |
| `/member/loginForm` | 로그인창 | member/loginForm.jsp | `/member/loginForm.do` |
| `/member/memberForm` | 회원 가입창 | member/memberForm.jsp | `/member/memberForm.do` |

## 파일별 역할

| 파일 | 역할 | 핵심 코드 |
|---|---|---|
| [HomeController.java](src/main/java/com/myspring/pro27/HomeController.java) | `/main.do` 요청에 Tiles 정의 이름 `main` 반환 | `@RequestMapping(value = "/main.do", method = RequestMethod.GET)`, `return "main"` |
| [member/controller/MemberControllerImpl.java](src/main/java/com/myspring/pro27/member/controller/MemberControllerImpl.java) | 회원 목록, 가입, 삭제, 로그인, 로그아웃, 폼 이동 처리와 log4j 로그 기록 | `@RequestMapping("/member")`, `session.setAttribute("isLogOn", true)`, `logger.info(...)` |
| [member/service/MemberService.java](src/main/java/com/myspring/pro27/member/service/MemberService.java) | 서비스 인터페이스, 로그인 메소드 추가 | `MemberVO login(MemberVO memberVO)` |
| [member/service/MemberServiceImpl.java](src/main/java/com/myspring/pro27/member/service/MemberServiceImpl.java) | `@Service` 빈, 로그인 조회를 DAO에 위임 | `return memberDAO.loginById(memberVO)` |
| [member/dao/MemberDAO.java](src/main/java/com/myspring/pro27/member/dao/MemberDAO.java) | DAO 인터페이스, 아이디·비밀번호 조회 메소드 추가 | `MemberVO loginById(MemberVO memberVO)` |
| [member/dao/MemberDAOImpl.java](src/main/java/com/myspring/pro27/member/dao/MemberDAOImpl.java) | `@Repository` 빈, SqlSession으로 목록·삽입·삭제·로그인 조회 | `sqlSession.selectOne("mapper.member.loginById", memberVO)` |
| [member/vo/MemberVO.java](src/main/java/com/myspring/pro27/member/vo/MemberVO.java) | 회원 VO | `MemberVO(String id, String pwd, String name, String email)` |
| [log4j.xml](src/main/java/log4j.xml) | 콘솔 appender와 날짜별 파일 appender, `com.myspring.pro27` 로거의 info 레벨 설정. 로그 파일 경로는 `D:\JavaProgram\logs\output.log` 고정 | `ConsoleAppender`, `DailyRollingFileAppender`, `<level value="info" />` |
| [mybatis/mappers/member.xml](src/main/java/mybatis/mappers/member.xml) | 목록 조회, 삽입, 삭제 SQL과 로그인 조회 `loginById` | `select * from t_member where id = #{id} and pwd = #{pwd}` |
| [mybatis/model/modelConfig.xml](src/main/java/mybatis/model/modelConfig.xml) | VO 타입 별칭 설정 | `alias="memberVO"` |
| [tiles/tiles_member.xml](src/main/java/tiles/tiles_member.xml) | 공통 레이아웃 정의와 화면별 상속 정의 | `<definition name="baseLayout" template="/WEB-INF/views/common/layout.jsp">`, `extends="baseLayout"` |
| [action-servlet.xml](src/main/webapp/WEB-INF/action-servlet.xml) | 애너테이션 처리 빈, TilesConfigurer, Tiles 뷰 리졸버, `component-scan` 설정 | `TilesConfigurer`, `<property name="viewClass" value="org.springframework.web.servlet.view.tiles2.TilesView" />` |
| [config/action-mybatis.xml](src/main/webapp/WEB-INF/config/action-mybatis.xml) | 커넥션 풀, SqlSessionFactoryBean, SqlSessionTemplate 등록 | `classpath:mybatis/mappers/*.xml` |
| [config/jdbc.properties](src/main/webapp/WEB-INF/config/jdbc.properties) | 드라이버 클래스, 접속 URL, 계정 값을 담은 속성 파일 | `jdbc.url` |
| [views/common/layout.jsp](src/main/webapp/WEB-INF/views/common/layout.jsp) | header, sidebar-left, content, footer 영역에 Tiles 조각을 넣는 공통 틀 | `<tiles:insertAttribute name="body" />` |
| [views/common/header.jsp](src/main/webapp/WEB-INF/views/common/header.jsp) | 홈 링크, 사이트 제목, 로그인 상태에 따라 환영 문구·로그아웃 링크 또는 로그인 링크 표시 | `<c:when test="${isLogOn == true && member != null}">`, `${member.name}` |
| [views/common/side.jsp](src/main/webapp/WEB-INF/views/common/side.jsp) | 노란 배경의 사이드 메뉴, 회원관리·게시판관리·상품관리 링크 자리 | `href="#"`, `.no-underline` |
| [views/common/footer.jsp](src/main/webapp/WEB-INF/views/common/footer.jsp) | 이메일, 주소, 약도 링크 자리를 둔 하단 영역 | `<p>회사주소:서울시 강동구</p>` |
| [views/main.jsp](src/main/webapp/WEB-INF/views/main.jsp) | 메인 페이지 본문 | `<h1>메인 페이지입니다!!</h1>` |
| [views/member/listMembers.jsp](src/main/webapp/WEB-INF/views/member/listMembers.jsp) | 회원 목록 본문, 삭제하기 링크와 회원가입 링크 | `${contextPath}/member/removeMember.do?id=${member.id}` |
| [views/member/loginForm.jsp](src/main/webapp/WEB-INF/views/member/loginForm.jsp) | 로그인 폼 본문, `result`가 `loginFailed`면 알림창 표시 | `<c:when test="${result == 'loginFailed'}">`, `action="${contextPath}/member/login.do"` |
| [views/member/memberForm.jsp](src/main/webapp/WEB-INF/views/member/memberForm.jsp) | 회원 가입 폼 본문 | `action="${contextPath}/member/addMember.do"` |
| [web.xml](src/main/webapp/WEB-INF/web.xml) | 인코딩 필터, ContextLoaderListener와 `action-mybatis.xml`, DispatcherServlet `*.do` 매핑 | `CharacterEncodingFilter`, `ContextLoaderListener` |
