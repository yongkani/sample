# Sample Spring Boot Project

간단한 정보 제공 웹 사이트

## 기술 스택

- **Spring Boot 3.2.0**
- **Java 17**
- **MySQL**
- **Spring Data JPA**
- **Thymeleaf**
- **Lombok**

## 데이터베이스 설정

### MySQL 데이터베이스 생성

```sql
CREATE DATABASE testdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 접속 정보

- **URL**: jdbc:mysql://localhost:3306/testdb
- **Username**: root
- **Password**: root

## 실행 방법

### Gradle을 사용한 실행

```bash
./gradlew bootRun
```

### 또는 IntelliJ에서 실행

`SampleApplication.java`를 실행하세요.

## 접속

브라우저에서 `http://localhost:8080` 접속

## 주요 기능

- 정보 목록 조회
- 정보 상세 조회
- 정보 등록
- 정보 삭제

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/example/sample/
│   │       ├── SampleApplication.java
│   │       ├── controller/
│   │       │   ├── HomeController.java
│   │       │   └── InfoController.java
│   │       ├── service/
│   │       │   └── InfoService.java
│   │       ├── repository/
│   │       │   └── InfoRepository.java
│   │       └── entity/
│   │           └── Info.java
│   └── resources/
│       ├── application.yml
│       └── templates/
│           └── info/
│               ├── list.html
│               ├── form.html
│               └── detail.html
└── test/
```
