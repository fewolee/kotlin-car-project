package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.entity.QCarEntity.carEntity
import com.carenation.car.domain.car.dto.request.CarInfoListRequest
import com.carenation.car.domain.car.entity.QCarCategoryEntity.carCategoryEntity
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service


@Service
class CarQuerydslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val categoryRepository: CategoryRepository
) : CarQuerydslRepository {

    // 자동차의 id로 자동차 엔티티를 조회해 CarInfoDto로 반환
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

    // 자동차의 모든 엔티티를 조회해 CarInfoDto 리스트로 반환
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

    // 카테고리 이름으로 자동차 엔티티를 조회해 CarInfDto 리스트로 반환
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
            .join(carCategoryEntity).on(carEntity.id.eq(carCategoryEntity.carEntity.id))
            .where(carCategoryEntity.categoryEntity.id.eq(categoryEntity.id))
            .fetch()
    }


    // 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 CarInfoDto 리스트로 반환
        override fun getDynamicQuery(req: CarInfoListRequest): List<CarInfoDto> {
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
            .where(eqModelName(req.modelName),
                eqManufacture(req.manufacture),
                eqProductionYear(req.productionYear))
            .fetch()
    }


    // 모델명이 같은지 확인
    private fun eqModelName(modelName: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(modelName)) null else carEntity.modelName.eq(modelName)
    }

    // 제조사가 같은지 확인
    private fun eqManufacture(manufacture: String?): BooleanExpression? {
        return if (StringUtils.isNullOrEmpty(manufacture)) null else carEntity.manufacture.eq(manufacture)
    }

    // 생산년도가 같은지 확인
    private fun eqProductionYear(productionYear: Int?): BooleanExpression? {
        return if (productionYear == null ) null else carEntity.productionYear.eq(productionYear)
    }


}

