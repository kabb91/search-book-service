# search-book-service

## 사용한 라이브러리
- vue.js : 프론트엔드를 구현하기 위한 목적.
- vue-router.js : SPA 라우터 이용하기 위한 목적.
- axios.min.js : 웹서버와의 통신을 위한 목적.
- element-ui/lib/index.js : 화면 구성 UI를 구현하기 위한 목적.
- spring-cloud-starter-netflix-hystrix : Circuit breaker 패턴을 사용하기 위한 목적.
- lombok : 어노테이션을 통하여 코드 작성시 불필요한 반복적 작업을 줄이고 효율을 높이기 위한 목적.
- com.google.code.gson , json : json포맷을 라이브러리를 통해 쉽게 만들고, 웹쪽으로 json 포맷의 데이터로 보내주기 위한 목적.
- spring-boot-starter-freemarker : View Template Engine을 사용하기 위한 목적.

## jar파일 다운로드 경로
app-0.0.1-SNAPSHOT.jar 다운로드 경로 :

https://github.com/kabb91/search-book-service/releases/download/v0.0.1/app-0.0.1-SNAPSHOT.jar

## jar 실행방법
java -jar app-0.0.1-SNAPSHOT.jar

## 실행 순서
- 실행 전 다른 서비스에 대해서 포트 8080을 쓰고있는지 확인을 해줍니다. 
- 8080을 다른 서비스가 사용하지 않고 있다면,  java -jar app-0.0.1-SNAPSHOT.jar 으로 실행합니다.
- 만약 8080을 다른 서비스가 사용하고 있다면, java -Dserver.port=[원하는 포트번호] -jar app-0.0.1-SNAPSHOT.jar 로 실행 시켜 줍니다 . 예)java -Dserver.port=8089 -jar app-0.0.1-SNAPSHOT.jar
- 브라우저 창에 http://localhost:8080 입력을 하면(포트를 변경 했다면 http://localhost:8089), 로그인 페이지가 나타납니다.
- 로그인 페이지 하단에 회원가입 버튼을 누른 후 회원가입 페이지로 이동합니다.
- 회원가입 페이지에서 회원가입을 한 후에, 로그인 페이지로 이동하여 조금 전에 가입한 아이디와 비밀번호를 입력하고 로그인을 합니다.
- 로그인을 하면 책 검색 서비스 페이지로 이동합니다.  검색 창에 원하는 책 이름을 예) java 을 입력하고 좌측 돋보기 모양을 클릭합니다.
- 검색창 바로 아래 java에 대한 검색이 테이블 형태로 페이징된 상태로 보여지며, 테이블에서 맨 좌측에 > 을 마우스로 누르면 검색된 java 책에 대한 상세 정보(제목, 소개, ISBN, 저자, 출판사, 출판일, 정가, 판매가 )를 볼 수 있습니다.
- 검색과 동시에 화면 오른쪽에 사용자들이 많이 검색한 순서대로 최대 10개의 검색 키워드 및 키워드 별로 검색된 횟수가 보여집니다.
- 상단 책 검색 서비스 옆에 나의 검색 이력 버튼을 누르게 되면, 나의 검색 이력 페이지로 이동하며, 여태까지 사용자가 로그인해서 검색한 히스토리가 최신 검색 일지 기준으로 키워드와 함께 보여집니다.
- 만약 H2 DB의 데이터를 확인 하려면 브라우저 창에 http://localhost:8080/h2-console (포트를 변경 했다면 http://localhost:8089/h2-console ) 을 입력합니다.
- Driver Class:	org.h2.Driver , JDBC URL: jdbc:h2:mem:testdb, User Name: sa, Password:   으로 된 것을 확인 한 후 Connect을 해서 들어간 다음에 H2 DB의 데이터를 확인 하면 됩니다.


