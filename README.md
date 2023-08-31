# Spring Boot 3 + Spring Security 예제

## 참고 영상
https://www.youtube.com/watch?v=KxqlJblhzfI

## 사전 설정
1. 이 레포지토리 clone
2. docker 설치([링크](https://www.docker.com/))
3. db 설정
   ```bash
   cd security.demo
   docker-compose -f docker-compose.yml up -d
   docker ps
   
   docker exec -it security-db-container /bin/bash
   mariadb
   create user 'testuser'@'%' identified by 'testuser';
   grant all privileges on springboot_security.* to 'testuser'@'%';
   exit
   ```

## 서버 실행

1. key 생성
   사이트([링크](https://generate-random.org/encryption-key-generator?count=1&bytes=256&cipher=aes-256-cbc&string=&password=))
   에 접속 후 출력되는 키를 resources/application.yml => security.secret에 붙여넣기
2. IntelliJ에서 WAS 실행

## API 테스트

1. 로그인 안 한 상태로 인증이 필요한 API 호출 시도  
   - 요청
   `GET http://localhost:8080/api/v1/demo-controller`

   - 응답
   `403 Forbidden`


2. 회원 가입  
   - 요청
       ```POST http://localhost:8080/api/v1/auth/register
         {
            "firstname": "test",
            "lastname": "test",
            "email": "test@email.com",
            "password": "1234"
         }
     ```

   - 응답
      - 정상 처리 시
          ```
         200 OK
         { token: 토큰 }
         ```
   
      - 이미 같은 유저가 있을 시
         ```
         400 Bad Request
         {
           "errorType": "Bad Request",
           "msg": "에러 발생"
         }
         ```

3. 로그인
   - 요청
       ```POST http://localhost:8080/api/v1/auth/authenticate
         {
            "email": "test@email.com",
            "password": "1234"
         }
     ```
   - 응답
      - 정상 처리 시
        ```
        200 OK
        { token: 토큰 }
        ```
       - 이메일 또는 비번 틀렸을 시
         ```
         400 Bad Request
         {
           "errorType": "Bad Request",
           "msg": "에러 발생"
         }
         ```
4. 인증이 필요한 API 호출 시도


