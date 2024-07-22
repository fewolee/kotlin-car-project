# Car-Project

## 요구사항

---

- 요구조건을 충족하는 API를 구성하시오.

1) 자동차 등록 API

2) 자동차 조회 API ( 개별 또는 통합 가능 )

- 자동차 대여 가능 여부 조회
- 자동차 카테고리 별 조회
- 자동차 제조사, 모델명, 생산년도를 통합 조회

3) 자동차 수정 API

**요구조건**

- 자동차는 하나 이상의 카테고리에 속할 수 있다.
- 자동차는 제조사, 모델명, 생산년도의 정보를 가지고 있다.
- 신규 자동차는 항상 카테고리가 필요하다.
- 자동차는 수리 또는 분실 등의 이유로 대여가 중단 될 수 있다.
- 자동차는 카테고리가 변경될 수 있다.
- 카테고리 별로 자동차를 검색 할 수 있다.
- 제조사, 모델명와 생산년도로 자동차를 검색 할 수 있다.
- 현재 차고에 있는 자동차 목록은 다음과 같다.
    - 카테고리 / 제조사 / 모델명 / 생산년도
    - 미니SUV / 현대 / 코나 / 2024
    - 준중형 SUV / 현대 / 아이오닉 / 2024
    - 대형RV / 현대 / 스타리아 / 2022
    - 중형 트럭 / 현대 / 포터 / 2024
    - 준중형 SUV / 현대 / 투싼 / 2023
    - 대형 RV / KIA / 카니발 / 2021
    - 경형 RV / KIA / 레이 / 2022
    - 중형 트럭 / KIA / 봉고3 / 2023a
    - 중형 SUV / KIA / 쏘렌토 / 2024

---

## **사용 언어와 프레임워크**

- JDK 17 , kotlin 1.9.24
- spring boot 3.3.1
- MySql RDBMS, ORM 사용

---

## 아키텍처

- Hexagonal Architecture + Multi Module 아키텍처 사용
- bootstrap module
    
    :  스프링 부트 실행에 필요한 코드만 존재
    
    나머지 domain, application, adapter-data-jpa, connector 모듈 의존
    
- domain module
    
    : 핵심 도메인인 Car 관련 Dto들만 존재
    
    kotlin(”jvm”) 플러그인 이외 다른 의존성 없음
    
- application module
    
    : Application 단에서 필요한 Port in, out 인터페이스와 구현체인 CarService 존재
    
    domain 모듈을 의존하고 기본적인 spring boot 어노테이션을 띄우기 위한
    
    spring-boot-starter, spring-tx  dependency 의존
    
- adapter-data-jpa module
    
    :  db 작업을 위한 설정 및 jpa를 사용하는 Entity 클래스. mapper, repository, querydsl 관련 설정 클래스 , port out 인터페이스를 구현한 adapter 클래스 존재
    
    application, domain 모듈 의존
    
- connector module
    
    : in adapter인 컨트롤러단 클래스 , mapper, 스웨거와 Request Dto 클래스 존재
    
    관련 spring boot web, vaildation 과 mapstruct 관련 dependency 의존
    
    domain, application 모듈 의존 
    

---

## API
![image](https://github.com/user-attachments/assets/1464b3e0-c453-4ea2-818b-d8c2efe3c661)

- 생성 관련 api
    
    : 모델명, 제조사, 생산년도, 대여 가능 여부, 카테고리 정보를 받아 자동차 생성 후
    
     생성된 정보 반환
  
![image](https://github.com/user-attachments/assets/a3e3a693-f3ee-4746-899e-afc5f4853210)

    
- 수정 관련 api

: 수정할 자동차의 id, 정보들을 받아 자동차 수정

![image](https://github.com/user-attachments/assets/ae67d957-84fd-4f98-95ce-f1715078f73c)


- 삭제 관련 api

: 삭제할 자동차의 id를 받아 자동차 삭제

![image](https://github.com/user-attachments/assets/f5d7c20d-35a0-4e07-8eba-1722c4add824)


- 조회 관련 api
- 자동차 id로 단건 정보 조회

![image](https://github.com/user-attachments/assets/881e4710-fd11-4725-a7f0-864979466795)


- 모든 자동차 정보 조회

![image](https://github.com/user-attachments/assets/f87b4e35-7237-454d-95d0-6d48c10ff629)


- 카테고리 이름으로 자동차 정보 조회

![image](https://github.com/user-attachments/assets/52349ae5-9118-4540-b5c3-4427e18434a9)


- 제조사, 모델명, 생산년도로 자동차 정보 조회

![image](https://github.com/user-attachments/assets/8bb1b9de-2e57-4bcc-9136-6e8542ed16bd)
