# 🔐 OAuth2 Resource Server

## 📌 개요

이 프로젝트는 Spring Boot 기반의 **OAuth2 Resource Server**입니다.

Authorization Server에서 발급한 JWT Access Token을 검증하고, 보호된 API에 대한 접근을 제어합니다.

---

## 🏗️ 아키텍처

```
Client
   ↓ (Bearer Token)
Resource Server (8081)
   ↓
JWT 검증 (issuer, signature)
   ↓
권한 체크 (scope)
   ↓
Protected API
```

---

## 🚀 기술 스택

* Java 17
* Spring Boot 3.x
* Spring Security 6
* OAuth2 Resource Server
* Gradle

---

## ⚙️ 주요 기능

* JWT Access Token 검증
* Authorization Server와 연동 (issuer 기반)
* Scope 기반 권한 제어
* 보호된 REST API 제공

---

## ▶️ 실행 방법

```bash
./gradlew bootRun
```

서버 실행 주소:

```
http://localhost:8081
```

---

## 🔑 사전 조건

* Authorization Server가 실행 중이어야 함
* JWT 토큰이 필요함

예시 Authorization Server 주소:

```
http://localhost:8080
```

---

## ⚙️ 설정 (application.properties)

```properties
server.port=8081
spring.application.name=resource-server

spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080
```

---

## 🔐 API 호출 방법

```bash
curl -H "Authorization: Bearer ACCESS_TOKEN" \
http://localhost:8081/api/read
```

---

## 📥 정상 응답

```text
read ok: user
```

---

## 🚫 권한 부족 예시

```bash
curl -H "Authorization: Bearer ACCESS_TOKEN" \
http://localhost:8081/api/read
```

```text
403 Forbidden
```

---

## 📦 프로젝트 구조

```
resource-server/
├── config/
│   └── SecurityConfig.java
├── api/
│   └── ApiController.java
└── ResourceServerApplication.java
```

---

## 🧠 핵심 개념

### JWT 검증

* issuer (iss) 검증
* signature 검증 (JWK 기반)

### 권한 처리

* scope → GrantedAuthority 변환
* 예: SCOPE_read

### 인증 객체

* Authentication
* Jwt

---

## ⚠️ 주의사항

* issuer-uri는 반드시 Authorization Server의 issuer와 동일해야 함
* Authorization Server가 실행 중이어야 JWT 검증 가능
* 토큰이 만료되면 401 Unauthorized 발생

