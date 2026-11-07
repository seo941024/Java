# pro23 · MyBatis 매퍼로 회원 관리

SQL을 Java 코드에서 떼어 MyBatis 매퍼 XML에 두고, 서블릿과 DAO가 `SqlSession`으로 조회·삽입·수정·삭제를 실행하는 회원 관리. Spring 없이 MyBatis만 사용.

이전 단계와 차이: pro22는 DAO 안에 SQL 문자열을 두고 `JdbcTemplate`으로 실행했지만, pro23은 SQL을 `member.xml`에 id와 함께 등록하고 DAO는 `mapper.member.selectAllMemberList` 같은 이름만 호출. Spring 설정 대신 `@WebServlet` 서블릿과 `SqlSessionFactoryBuilder`로 구성.

## 다룬 개념

| 개념 | 뜻과 사용 방법 |
|---|---|
| MyBatis | SQL을 XML 파일에 따로 적고, 실행 결과를 Java 객체에 자동으로 담아 주는 퍼시스턴스 프레임워크 |
| `SqlMapConfig.xml` | MyBatis 전체 설정 파일. 타입 별칭, JDBC 트랜잭션 관리, 커넥션 풀 방식 `POOLED` 데이터 소스, 매퍼 파일 위치 지정 |
| 매퍼 XML | `<select>`, `<insert>`, `<update>`, `<delete>` 태그에 SQL을 id와 함께 적은 파일. `namespace`와 id를 합친 `mapper.member.insertMember` 같은 이름으로 호출 |
| `SqlSessionFactory`와 `SqlSession` | 설정 파일을 읽어 공장 객체를 한 번 만들고, 공장에서 연 `SqlSession`으로 SQL 실행. DAO는 정적 변수에 공장을 보관해 재사용 |
| `selectList`, `selectOne` | 여러 행 결과는 `List`로, 한 행이나 한 값은 객체 하나로 받는 조회 메소드 |
| `resultMap` | 조회 결과 컬럼과 VO 필드를 짝지은 규칙. `memResult` |
| `resultType`과 `parameterType` | 결과를 담을 자료형과 SQL에 넘길 값의 자료형. `String`, `int`, `memberVO`, `java.util.HashMap` |
| `#{ }` 바인딩 | 넘긴 값에서 같은 이름의 필드나 키를 찾아 SQL 값 자리를 채우는 표기. `#{id}`, `#{pwd}` |
| 타입 별칭 | 긴 클래스 이름 대신 쓰는 짧은 이름. `com.spring.ex01.MemberVO`를 `memberVO`로 등록 |
| `CDATA` 구역 | SQL 안의 `<`, `>` 같은 문자를 XML 태그로 해석하지 않도록 감싸는 구역 |
| `commit()` | 삽입·수정·삭제 뒤 변경 내용을 DB에 확정하는 메소드. `openSession()`으로 연 세션은 자동 확정이 꺼진 상태 |

## 요청 흐름

ex04 회원 추가 기준.

1. test03/memberForm.jsp에서 회원 정보를 POST로 `/mem4.do?action=insertMember` 전송
2. `@WebServlet("/mem4.do")` MemberServlet이 `action` 값으로 분기하고 요청 값을 MemberVO에 담음
3. `dao.insertMember(memberVO)`가 `getInstance()`로 SqlSessionFactory를 얻고 세션을 열어 `session.insert("mapper.member.insertMember", memberVO)` 실행
4. MyBatis가 매퍼의 `#{id}`, `#{pwd}`, `#{name}`, `#{email}` 자리에 VO 값을 넣어 insert 실행, DAO가 `session.commit()`으로 확정
5. 서블릿이 `/mem4.do?action=listMembers`로 forward해 목록을 다시 조회, test03/listMembers.jsp에 삭제 링크와 함께 표시

| 패키지 | 요청 경로 | 호출하는 매퍼 id | 화면 |
|---|---|---|---|
| ex01 | `/mem.do` | selectAllMemberList | test01/listMembers.jsp |
| ex02 | `/mem2.do` | selectName, selectPwd | `alert()`로 이름 출력 |
| ex03 | `/mem3.do` | selectAllMemberList, selectMemberById, selectMemberByPwd | test02/search.jsp에서 검색, test02/listMembers.jsp 또는 test02/memberInfo.jsp |
| ex04 | `/mem4.do` | selectAllMemberList, insertMember, insertMember2, updateMember, deleteMember | test03 폴더의 목록·가입·수정 화면 |

## 파일별 역할

| 파일 | 역할 | 핵심 코드 |
|---|---|---|
| [ex01/MemberDAO.java](src/main/java/com/spring/ex01/MemberDAO.java) | 설정 파일로 SqlSessionFactory를 한 번 만들고 회원 전체 목록 조회 | `Resources.getResourceAsReader("mybatis/SqlMapConfig.xml")`, `new SqlSessionFactoryBuilder().build(reader)`, `session.selectList(...)` |
| [ex01/MemberServlet.java](src/main/java/com/spring/ex01/MemberServlet.java) | `/mem.do` 요청에 목록을 조회해 test01/listMembers.jsp로 forward | `@WebServlet("/mem.do")` |
| [ex01/MemberVO.java](src/main/java/com/spring/ex01/MemberVO.java) | 회원 VO, ex02부터 ex04까지 같은 클래스 사용 | `java.util.Date joinDate` |
| [ex02/MemberDAO.java](src/main/java/com/spring/ex02/MemberDAO.java) | 아이디가 `hong`인 회원의 이름 문자열과 비밀번호 값 조회 | `session.selectOne("mapper.member.selectName")` |
| [ex02/MemberServlet.java](src/main/java/com/spring/ex02/MemberServlet.java) | 조회한 이름을 자바스크립트 `alert()`로 출력, 비밀번호 조회 호출은 주석으로 보관 | `pw.write("alert(' 이름: " + name + "');")` |
| [ex03/MemberDAO.java](src/main/java/com/spring/ex03/MemberDAO.java) | 전체 조회, 아이디로 한 명 조회, 비밀번호로 여러 명 조회 | `session.selectOne("mapper.member.selectMemberById", id)`, `selectList("mapper.member.selectMemberByPwd", pwd)` |
| [ex03/MemberServlet.java](src/main/java/com/spring/ex03/MemberServlet.java) | `/mem3.do` 요청의 `action`과 `value` 값으로 검색 방식 분기 | `action.equals("selectMemberById")`, `Integer.parseInt(request.getParameter("value"))` |
| [ex04/MemberDAO.java](src/main/java/com/spring/ex04/MemberDAO.java) | VO 삽입, HashMap 삽입, 수정, 삭제 후 `commit()` | `session.insert("mapper.member.insertMember2", memberMap)`, `session.update(...)`, `session.delete(...)` |
| [ex04/MemberServlet.java](src/main/java/com/spring/ex04/MemberServlet.java) | `/mem4.do` 요청을 목록, VO 삽입, HashMap 삽입, 수정, 삭제로 분기하고 작업 뒤 목록으로 forward | `Map<String, String> memberMap = new HashMap<String, String>()`, `nextPage = "/mem4.do?action=listMembers"` |
| [mybatis/SqlMapConfig.xml](src/main/java/mybatis/SqlMapConfig.xml) | 타입 별칭, 트랜잭션 관리 방식, 커넥션 풀 데이터 소스, 매퍼 파일 위치 설정 | `<typeAlias type="com.spring.ex01.MemberVO" alias="memberVO" />`, `<dataSource type="POOLED">`, `<mapper resource="mybatis/mappers/member.xml" />` |
| [mybatis/mappers/member.xml](src/main/java/mybatis/mappers/member.xml) | `mapper.member` 네임스페이스의 조회·삽입·수정·삭제 SQL과 `memResult` 결과 매핑 | `<resultMap id="memResult" type="memberVO">`, `values(#{id}, #{pwd}, #{name}, #{email})` |
| [test01/listMembers.jsp](src/main/webapp/test01/listMembers.jsp) | 회원 목록 표 | `<c:forEach var="member" items="${membersList}">` |
| [test02/listMembers.jsp](src/main/webapp/test02/listMembers.jsp) | test01/listMembers.jsp와 같은 내용의 목록 표, ex03 검색 결과 화면 | `${member.joinDate}` |
| [test02/memberInfo.jsp](src/main/webapp/test02/memberInfo.jsp) | 아이디로 조회한 회원 한 명의 정보 표 | `${member.id}`, `${member.email}` |
| [test02/search.jsp](src/main/webapp/test02/search.jsp) | 검색어 입력칸과 전체·아이디·비밀번호 선택 목록, `/mem3.do`로 전송 | `<select name="action">`, `name="value"` |
| [test03/listMembers.jsp](src/main/webapp/test03/listMembers.jsp) | 행마다 삭제 링크가 있는 목록과 회원가입 링크 | `${contextPath}/mem4.do?action=deleteMember&id=${member.id}` |
| [test03/memberForm.jsp](src/main/webapp/test03/memberForm.jsp) | 회원 가입 폼, `insertMember` 동작으로 전송 | `action="${contextPath}/mem4.do?action=insertMember"` |
| [test03/modMember.jsp](src/main/webapp/test03/modMember.jsp) | 회원 정보 수정 폼, `updateMember` 동작으로 전송 | `action="${contextPath}/mem4.do?action=updateMember"` |
| [web.xml](src/main/webapp/WEB-INF/web.xml) | 시작 파일 목록만 둔 설정 파일 | `<welcome-file-list>` |

매퍼 XML과 설정 파일은 `src/main/java` 아래에 두어 클래스 경로에서 `mybatis/SqlMapConfig.xml` 이름으로 읽음.
