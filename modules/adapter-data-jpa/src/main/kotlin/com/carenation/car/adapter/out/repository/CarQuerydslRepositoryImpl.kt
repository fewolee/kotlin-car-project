package com.carenation.car.adapter.out.repository

import com.carenation.car.adapter.out.persistence.entity.QCarCategoryEntity.carCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.QCarEntity.carEntity
import com.carenation.car.application.domain.CarModel
import com.carenation.car.dto.CarInfoListInDto
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service

/**
 * CarQuerydslRepository 인터페이스의 구현체
 */
@Service
class CarQuerydslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val categoryRepository: CategoryRepository,
) : CarQuerydslRepository {
    /**
     * 자동차의 id로 자동차 엔티티를 조회해 CarModel 반환
     * @param carId
     * @return CarModel
     */
    override fun getById(carId: Long): CarModel =
        queryFactory
            .select(
                Projections.constructor(
                    CarModel::class.java,
                    carEntity.modelName,
                    carEntity.manufacture,
                    carEntity.productionYear,
                    carEntity.rentAvailable,
                ),
            ).from(carEntity)
            .where(carEntity.id.eq(carId))
            .fetchOne()
            ?: throw IllegalArgumentException("자동차 ID $carId 이 없습니다")

    /**
     * 모든 자동차를 조회해 List<CarModel>로 반환
     * @return List<CarModel>
     */
    override fun getAll(): List<CarModel> =
        queryFactory
            .select(
                Projections.constructor(
                    CarModel::class.java,
                    carEntity.modelName,
                    carEntity.manufacture,
                    carEntity.productionYear,
                    carEntity.rentAvailable,
                ),
            ).from(carEntity)
            .fetch()

    /**
     * 카테고리 이름으로 자동차 엔티티를 조회해 List<CarModel>로 반환
     * @param category
     * @return List<CarModel>
     */
    override fun getByCategoryName(category: String): List<CarModel> {
        val categoryEntity =
            categoryRepository.findByCategoryName(category)
                ?: throw IllegalArgumentException("카테고리 이름이 없습니다: $category")

        return queryFactory
            .select(
                Projections.constructor(
                    CarModel::class.java,
                    carEntity.modelName,
                    carEntity.manufacture,
                    carEntity.productionYear,
                    carEntity.rentAvailable,
                ),
            ).from(carEntity)
            .join(carCategoryEntity)
            .on(carEntity.id.eq(carCategoryEntity.carEntity.id))
            .where(carCategoryEntity.categoryEntity.id.eq(categoryEntity.id))
            .fetch()
    }

    /**
     * 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 List<CarInfoDto>로 반환
     * @param CarInfoListInDto
     * @return List<CarModel>
     */
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarModel> =
        queryFactory
            .select(
                Projections.constructor(
                    CarModel::class.java,
                    carEntity.modelName,
                    carEntity.manufacture,
                    carEntity.productionYear,
                    carEntity.rentAvailable,
                ),
            ).from(carEntity)
            .where(
                eqModelName(req.modelName),
                eqManufacture(req.manufacture),
                eqProductionYear(req.productionYear),
            ).fetch()

    // 모델명이 같은지 확인
    private fun eqModelName(modelName: String?): BooleanExpression? =
        if (StringUtils.isNullOrEmpty(modelName)) null else carEntity.modelName.eq(modelName)

    // 제조사가 같은지 확인
    private fun eqManufacture(manufacture: String?): BooleanExpression? =
        if (StringUtils.isNullOrEmpty(manufacture)) null else carEntity.manufacture.eq(manufacture)

    // 생산년도가 같은지 확인
    private fun eqProductionYear(productionYear: Int?): BooleanExpression? =
        if (productionYear == null) null else carEntity.productionYear.eq(productionYear)
}
