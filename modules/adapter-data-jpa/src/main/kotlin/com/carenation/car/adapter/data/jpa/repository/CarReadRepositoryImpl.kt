package com.carenation.car.adapter.data.jpa.repository

import com.carenation.adapter.data.jpa.car.persistence.entity.QCarCategoryEntity.carCategoryEntity
import com.carenation.adapter.data.jpa.car.persistence.entity.QCarEntity.carEntity
import com.carenation.adapter.data.jpa.car.persistence.entity.QCategoryEntity.categoryEntity
import com.carenation.car.adapter.data.jpa.persistence.entity.CarEntity
import com.carenation.car.adapter.data.jpa.persistence.entity.CategoryEntity
import com.carenation.car.adapter.data.jpa.util.CarReadRepositoryUtil
import com.carenation.car.application.dto.CarInfoListInDto
import com.carenation.car.application.port.out.CarReadRepository
import com.carenation.car.domain.CarModel
import com.carenation.car.domain.CategoryModel
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.util.StringUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * CarQuerydslRepository 인터페이스의 구현체
 */
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

    /**
     *  카테고리 이름으로 자동차 정보 조회
     * @param categoryName
     * @return List<CarModel>
     */
    override fun getByCategoryName(categoryName: String): List<CarModel> {
        val categoryModel = getCategoryModelByCategoryName(categoryName)
        return getCarModelByCategoryId(categoryModel.id)
    }

    /**
     * 카테고리  ID로 자동차 엔티티를 조회해 List<CarModel>로 반환
     * @param categoryId
     * @return List<CarModel>
     */
    fun getCarModelByCategoryId(categoryId: Long): List<CarModel> =
        queryFactory
            .select(carReadRepositoryUtil.ceCarModel())
            .from(carEntity)
            .join(carCategoryEntity)
            .on(carEntity.id.eq(carCategoryEntity.carEntity.id))
            .where(carCategoryEntity.categoryEntity.id.eq(categoryId))
            .fetch()

    /**
     * 자동차 ID 자동차 엔티티를 조회해 CarEntity 반환
     * @param carId
     * @return CatEntity
     */
    fun getCarEntityByCarId(carId: Long): CarEntity =
        queryFactory
            .select(carEntity)
            .from(carEntity)
            .where(carEntity.id.eq(carId))
            .fetchOne()
            ?: throw IllegalArgumentException("존재하는 자동차 ID $carId 이 없습니다")

    /**
     * 카테고리 이름으로 카테고리 엔티티를 조회해 CategoryEntity 반환
     * @param categoryName
     * @return CategoryModel
     */
    fun getCategoryEntityByCategoryName(categoryName: String): CategoryEntity =
        queryFactory
            .select(categoryEntity)
            .from(categoryEntity)
            .where(categoryEntity.categoryName.eq(categoryName))
            .fetchOne()
            ?: throw IllegalArgumentException("존재하는 $categoryName 이 없습니다")

    /**
     * 카테고리 이름로 카테고리 엔티티를 조회해 CategoryModel 반환
     * @param categoryName
     * @return CategoryModel
     */
    fun getCategoryModelByCategoryName(categoryName: String): CategoryModel =
        queryFactory
            .select(carReadRepositoryUtil.ceCategoryModel())
            .from(categoryEntity)
            .where(categoryEntity.categoryName.eq(categoryName))
            .fetchOne()
            ?: throw IllegalArgumentException("존재하는 $categoryName 이 없습니다")

    /**
     * 모델명, 제조사, 생상년도로 동적으로 자동차를 조회해 List<CarModel>로 반환
     * @param CarInfoListInDto
     * @return List<CarModel>
     */
    override fun getDynamicQuery(req: CarInfoListInDto): List<CarModel> =
        queryFactory
            .select(carReadRepositoryUtil.ceCarModel())
            .from(carEntity)
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
