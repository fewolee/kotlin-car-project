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
    
    :  애플리케이션의 부팅 전용
    
    나머지 domain, application, adapter-data-jpa, connector 모듈 의존
    
- domain module
    
    : 애플리케이션의 핵심 도메인 모델 정의
    
    
- application module
    
    : 애플리케이션의 비지니스 로직 담당 및 외부와의 인터페이스
    
    domain 모듈을 의존하고 기본적인 spring boot 어노테이션을 띄우기 위한
    
    spring-boot-starter, spring-tx  dependency 의존
    
- adapter-data-jpa module
    
    :  데이터베이스 접근 및 영속성 처리 담당
    
    application, domain 모듈 의존
    
- connector module
    
    : 외부와의 통신 담당
    
    관련 spring boot web, vaildation 과 mapstruct 관련 dependency 의존
    
    domain, application 모듈 의존 
    

---
## 구현 과정

- 엔티티 : 자동차는 한 개 이상의 카테고리를 가질 수 있고 하나의 카테고리도 여러개의 자동차를 가질 수 있기 떄문에 N:M 관계, CarCategory라는 중간 매핑 엔티티로 구현하여 1:N , M:1 관계 설정
- Validation : 컨트롤러로 들어오는 Dto를 spring- validation을 이용하여 유효성 검사

```kotlin
/**
 * 자동차를 생성하기 위해 요청올 보내기 위한 Dto
 */
data class CreateCarRequest (
    @field:NotBlank(message = "모델명은 필수입니다")
    val modelName: String,

    @field:NotEmpty(message = "제조사는 필수입니다")
    val manufacture: String,

    @field:Positive(message = "양수만 가능합니다")
    @field:Max(value = 2024, message="생산년도는 2024년을 넘을 수 없습니다")
    val productionYear: Int,

    @field:NotNull(message = "대여가능 여부는 필수입니다")
    val rentAvailable: Boolean,

    @NotBlankElementList
    val categoryNames: List<String> // 자동차 카테고리
)

 // 자동차 등록
    @PostMapping
    @Operation(summary = "자동차를 생성하는 API입니다", description = "자동차의 정보들을 입력받아 자동차를 생성합니다")
    fun create(
        @Validated @RequestBody req: CarCreateRequest,
    ): BaseResponse<Unit> {
        carCreateUseCase.create(carInMapper.toCarCreateInDto(req))
        return BaseResponse(
            statusCode = ResultCode.CREATE_SUCCESS.statusCode,
            statusMessage = ResultCode.CREATE_SUCCESS.message,
        )
    }

```

실패 시 화면

![image](https://github.com/user-attachments/assets/834d0868-0e1a-49e2-b549-1d41ea901b5c)



- Mapstruct :  Entity ↔ Dto, Dto ↔ Dto 간 매핑을 위해 mapstruct를 사용하여 매핑

```kotlin
/**
 * Adapter로 들어오는 Dto를 Application 단에서 사용하기 위한 Dto로 바꿔주기 위한 매퍼 인터페이스
 */
@Mapper(componentModel = "spring")
interface CarInMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(CarInMapper::class.java)
    }

    // CarUpdatRequest Dto와 자동차의 id를 받아 UpdateCarDto로 변환
    fun toCarUpdateInDto(id: Long, req: CarUpdateRequest): CarUpdateInDto

    //CreateCarRequest를 CarInfo 로 변환
    fun toCarAllInfo(createCarRequest: CreateCarRequest) : CarAllInfoDto

    //carInfoListRequest를 CarInfoListInDto로 변환
    fun toCarInfoListInDto(carInfoListRequest: CarInfoListRequest) : CarInfoListInDto
}
    
```

---

- Querydsl : 조회 성능을 높이기 위해 jpa 방식이 아닌 Queryds 사용, 동적으로 정보를 조회하기 위해 booleanexpression으로 함수화 해서 조건문 사용, 중복되는 constructor 코드는 Util 클래스로 분리 

```kotlin
@Component
class CarReadRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val carReadRepositoryUtil: CarReadRepositoryUtil,
) : CarReadRepository {
    /**
     * 자동차의 id로 자동차 엔티티를 조회해 CarModel 반환
     * @param carId
     * @return CarModel
     */

    override fun getById(carId: Long): CarModel =
        queryFactory
            .select(carReadRepositoryUtil.ceCarModel())
            .from(carEntity)
            .where(carEntity.id.eq(carId))
            .fetchOne()
            ?: throw IllegalArgumentException("자동차 ID $carId 이 없습니다")

    /**
     * 모든 자동차를 조회해 List<CarModel>로 반환
     * @return List<CarModel>
     */
    override fun getAll(): List<CarModel> =
        queryFactory
            .select(carReadRepositoryUtil.ceCarModel())
            .from(carEntity)
            .fetch()

```

```kotlin
@Component
class CarReadRepositoryUtil {
    fun ceCarModel(): ConstructorExpression<CarModel> =
        Projections.constructor(
            CarModel::class.java,
            carEntity.modelName,
            carEntity.manufacture,
            carEntity.productionYear,
            carEntity.rentAvailable,
        )

    fun ceCategoryModel(): ConstructorExpression<CategoryModel> =
        Projections.constructor(
            CategoryModel::class.java,
            categoryEntity.id,
            categoryEntity.categoryName,
        )
}

```
---

## Naming Convention

- 도메인 + 책임 + 기술

ex) CarCreateController,  CarCreateRequest ,

---



## API
![image](https://github.com/user-attachments/assets/1464b3e0-c453-4ea2-818b-d8c2efe3c661)

- 생성 관련 api
    
    : 모델명, 제조사, 생산년도, 대여 가능 여부, 카테고리 정보를 받아 자동차 생성 후
    
     상태코드 , 메세지, 생성 시간 응답
  
  ![image](https://github.com/user-attachments/assets/072fac7c-3b6b-43c7-a902-1972dd06fff7)


    
- 수정 관련 api

: 수정할 자동차의 id, 정보들을 받아 자동차 수정 후
상태코드, 메세지, 수정 시간 응답

![image](https://github.com/user-attachments/assets/cf5fce8e-9910-4888-accb-2c5631abeeb0)



- 삭제 관련 api

: 삭제할 자동차의 id를 받아 자동차 삭제 후
 상태코드, 메세지, 삭제 시간 응답

![image](https://github.com/user-attachments/assets/3c5b62a2-1555-48ce-99c4-14e3b0e427f7)



- 조회 관련 api : 정보 조회 후 상태코드, 메세지, 시간과 조회 정보 반환

- 자동차 정보 단건 조회
- 
![image](https://github.com/user-attachments/assets/93c010e2-79ab-44d9-9213-7b8fe9b0ae58)

- 모든 자동차 정보 조회

![image](https://github.com/user-attachments/assets/74a49a30-50ce-42af-b8e6-8ec82507519b)



- 카테고리 이름으로 자동차 정보 조회

![image](https://github.com/user-attachments/assets/d4f40d0d-92dc-46c4-9370-335a5544451c)



- 제조사, 모델명, 생산년도로 자동차 정보 조회

![image](https://github.com/user-attachments/assets/33c7085c-bb60-48fb-bc9f-b4c37239f5fc)

