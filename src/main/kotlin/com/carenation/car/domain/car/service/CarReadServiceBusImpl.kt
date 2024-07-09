package com.carenation.car.domain.car.service

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.entity.CarEntity
import com.carenation.car.domain.car.entity.QCarEntity.carEntity
import com.carenation.car.domain.car.repository.CarRepository
import com.carenation.car.domain.category.entity.QCarCategoryEntity.carCategoryEntity
import com.carenation.car.domain.category.repository.CategoryRepository
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarReadServiceBusImpl(
    private val queryFactory: JPAQueryFactory,
    private val categoryRepository: CategoryRepository
) : CarReadServiceBus {

//    @Autowired
//    lateinit var queryFactory: JPAQueryFactory
//

    // id로 자동차 조회
    @Transactional
    override fun getById(carId: Long): CarInfoDto {

        return queryFactory.select(
            Projections.constructor(
                CarInfoDto::class.java,
                carEntity.modelName,
                carEntity.manufacture,
                carEntity.productionYear,
                carEntity.rentAvailable
            )
        )
            .from(carEntity)
            .where(carEntity.id.eq(carId))
            .fetchOne()
            ?: throw IllegalArgumentException("자동차 ID $carId 이 없습니다")

    }

    // 모든 자동차 조회
    @Transactional
    override fun getAll(): List<CarInfoDto> {
        return queryFactory.select(
            Projections.constructor(
                CarInfoDto::class.java,
                carEntity.modelName,
                carEntity.manufacture,
                carEntity.productionYear,
                carEntity.rentAvailable
            )
        )
            .from(carEntity)
            .fetch()
    }

    //카테고리 이름으로 자동차 조회
    @Transactional
    override fun getByCategoryName(category: String): List<CarInfoDto> {
        val categoryEntity = categoryRepository.findByCategoryName(category)
            ?: throw IllegalArgumentException("카테고리 이름이 없습니다: $category")

        return queryFactory.select(
            Projections.constructor(
                CarInfoDto::class.java,
                carEntity.modelName,
                carEntity.manufacture,
                carEntity.productionYear,
                carEntity.rentAvailable
            )
        )
            .from(carEntity)
            .join(carEntity.carCategories, carCategoryEntity)
            .where(carCategoryEntity.categoryEntity.id.eq(categoryEntity.id))
            .fetch()
    }


    // 모델명, 제조사, 생산년도로 자동차 초회하는 동적 쿼리 메서드
    @Transactional
    override fun getDynamicQuery(modelName: String?, manufacture: String?, productionYear: Int?): List<CarInfoDto> {
        return queryFactory.select(
            Projections.constructor(
                CarInfoDto::class.java,
                carEntity.modelName,
                carEntity.manufacture,
                carEntity.productionYear,
                carEntity.rentAvailable
            )
        )
            .from(carEntity)
            .where(eqModelName(modelName),
                eqManufacture(manufacture),
                eqProductionYear(productionYear))
            .fetch()
    }


    // 모델명이 같은지 확인
    @Transactional
    override fun eqModelName(modelName: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(modelName)) null else carEntity.modelName.eq(modelName)
    }

    // 제조사가 같은지 확인
    @Transactional
    override fun eqManufacture(manufacture: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(manufacture)) null else carEntity.manufacture.eq(manufacture)
    }

    // 생산년도가 같은지 확인
    @Transactional
    override fun eqProductionYear(productionYear: Int?): BooleanExpression? {
        return if (productionYear == null ) null else carEntity.productionYear.eq(productionYear)
    }


}





