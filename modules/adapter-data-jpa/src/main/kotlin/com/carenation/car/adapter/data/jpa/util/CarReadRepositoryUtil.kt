package com.carenation.car.adapter.data.jpa.util

import com.carenation.adapter.data.jpa.car.persistence.entity.QCarEntity.carEntity
import com.carenation.adapter.data.jpa.car.persistence.entity.QCategoryEntity.categoryEntity
import com.carenation.car.domain.CarModel
import com.carenation.car.domain.CategoryModel
import com.querydsl.core.types.ConstructorExpression
import com.querydsl.core.types.Projections
import org.springframework.stereotype.Component

/**
 * Querydsl을 이용한 Constructor 클래스 util
 */
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
