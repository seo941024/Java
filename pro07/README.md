# pro07 · 서블릿과 DB 연동

서블릿이 DAO를 거쳐 Oracle `t_member` 테이블을 조회·등록·삭제하고 결과를 HTML 표로 출력하는 회원 관리 화면. DB 연결 방식을 DriverManager에서 JNDI 커넥션 풀로 바꾼 단계.

이전 단계와 차이: pro06은 서블릿 안에서 요청 값을 바로 출력했지만, pro07은 DB 작업을 DAO 클래스로, 회원 한 명의 값을 VO 클래스로 분리.

## 다룬 개념

| 개념 | 뜻과 사용 방법 |
|---|---|
| JDBC | Java에서 DB에 연결하고 SQL을 실행하는 표준 API. `Connection`으로 연결, `PreparedStatement`로 SQL 실행, `ResultSet`으로 조회 결과 순회 |
| DriverManager 연결 | `Class.forName()`으로 Oracle 드라이버를 불러온 뒤 `DriverManager.getConnection()`으로 요청마다 새 연결 생성 |
| 커넥션 풀과 JNDI | 커넥션 풀은 서버가 미리 만들어 둔 DB 연결을 빌려 쓰고 돌려주는 방식, JNDI는 서버에 등록한 자원을 이름으로 찾는 방법. `java:/comp/env`에서 `jdbc/oracle` 이름의 `DataSource`를 찾아 `getConnection()` 호출 |
| DAO | Data Access Object. DB 접근 코드만 모은 클래스. `listMembers()`, `addMember()`, `delMember()` |
| VO | Value Object. 테이블 한 행의 값을 필드와 getter·setter로 담는 클래스. `MemberVO`의 id, pwd, name, email, joinDate |
| `?` 바인딩 | SQL의 값 자리를 `?`로 두고 `setString(순번, 값)`으로 채워 실행하는 방식. 조회는 `executeQuery()`, 등록과 삭제는 `executeUpdate()` |
| `doHandle()` | `doGet()`과 `doPost()`가 같은 메소드를 호출하게 해 요청 방식과 무관하게 한곳에서 처리 |
| `command` 분기 | 요청 매개변수 `command`가 `addMember`이면 등록, `delMember`이면 삭제를 먼저 실행하고 목록 출력 |

## 요청 흐름

회원 등록·삭제가 있는 sec02.ex02 기준.

1. memberForm.html에서 아이디, 비밀번호, 이름, 이메일 입력 후 가입하기 클릭
2. `fn_sendMember()`가 빈 칸을 검사하고, hidden `command=addMember`와 함께 POST로 `member4` 전송
3. `@WebServlet("/member4")` MemberServlet의 `doPost()`가 `doHandle()` 호출
4. `command`가 `addMember`이면 요청 값으로 MemberVO를 채워 `dao.addMember(vo)` 실행, `insert` 문의 `?` 자리에 값 바인딩
5. `dao.listMembers()`가 커넥션 풀에서 연결을 받아 `select * from t_member` 결과를 MemberVO 목록으로 반환
6. 서블릿이 목록을 HTML 표로 출력, 행마다 `command=delMember&id=` 삭제 링크와 새 회원 등록 링크 포함
7. 삭제 링크를 누르면 GET으로 같은 서블릿을 호출해 `delete from t_member where id=?` 실행 후 목록 다시 출력

| 패키지 | 요청 경로 | DB 연결 | 기능 |
|---|---|---|---|
| sec01.ex01 | `/member` | DriverManager | 회원 목록 조회 |
| sec02.ex01 | `/member2` | JNDI `DataSource` | 회원 목록 조회 |
| sec02.ex02 | `/member4` | JNDI `DataSource` | 회원 목록 조회, 등록, 삭제 |

## 파일별 역할

| 파일 | 역할 | 핵심 코드 |
|---|---|---|
| [sec01/ex01/MemberDAO.java](src/main/java/sec01/ex01/MemberDAO.java) | 드라이버 로딩과 DriverManager 연결 후 회원 전체 조회. 접속 정보는 클래스 상수 | `Class.forName(driver)`, `DriverManager.getConnection(url, user, pwd)` |
| [sec01/ex01/MemberServlet.java](src/main/java/sec01/ex01/MemberServlet.java) | `/member` 요청에 회원 목록을 HTML 표로 출력 | `dao.listMembers()`, `out.print("<tr><td>" + id + ...)` |
| [sec01/ex01/MemberVO.java](src/main/java/sec01/ex01/MemberVO.java) | 회원 VO, 생성자에서 콘솔 메시지 출력 | `private Date joinDate`, getter·setter |
| [sec02/ex01/MemberDAO.java](src/main/java/sec02/ex01/MemberDAO.java) | 생성자에서 JNDI로 `DataSource`를 찾고, 조회 때 커넥션 풀 연결 사용 | `ctx.lookup("java:/comp/env")`, `envContext.lookup("jdbc/oracle")` |
| [sec02/ex01/MemberServlet.java](src/main/java/sec02/ex01/MemberServlet.java) | `/member2` 요청에 회원 목록 표 출력 | `@WebServlet("/member2")` |
| [sec02/ex01/MemberVO.java](src/main/java/sec02/ex01/MemberVO.java) | sec01과 같은 구성의 회원 VO | getter·setter |
| [sec02/ex02/MemberDAO.java](src/main/java/sec02/ex02/MemberDAO.java) | 커넥션 풀 기반 조회, 등록, 삭제 | `insert into t_member(id, pwd, name, email) values(?,?,?,?)`, `pstmt.setString(1, id)` |
| [sec02/ex02/MemberServlet.java](src/main/java/sec02/ex02/MemberServlet.java) | `/member4` 요청을 `doHandle()`에서 처리, `command` 값으로 등록·삭제 분기 후 삭제 링크가 있는 목록 출력 | `command.equals("addMember")`, `?command=delMember&id=` |
| [sec02/ex02/MemberVO.java](src/main/java/sec02/ex02/MemberVO.java) | sec01과 같은 구성의 회원 VO | getter·setter |
| [memberForm.html](src/main/webapp/memberForm.html) | 회원 가입 폼, 항목별 빈 값 검사 후 POST 전송 | `fn_sendMember()`, `frmMember.action = "member4"`, `name="command" value="addMember"` |
| [web.xml](src/main/webapp/WEB-INF/web.xml) | 시작 파일 목록만 둔 설정 파일 | `<welcome-file-list>` |

`jdbc/oracle` 자원을 등록하는 서버 설정 파일은 저장소에 없음.
