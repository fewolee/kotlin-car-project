package com.carenation.car.adapter.out.repository

import com.carenation.car.adapter.out.persistence.entity.QCarCategoryEntity.carCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.QCarEntity.carEntity
import com.carenation.car.port.out.CarDeleteRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * CarDeleteRepository 인터페이스의 구현체
 */
@Component
class CarDeleteRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : CarDeleteRepository {
    /**
     * 자동차 ID로 CarCategoryEntity 삭제
     * @param carId
     */
    override fun deleteCarCategory(carId: Long) {
        queryFactory
            .delete(carCategoryEntity)
            .where(carCategoryEntity.carEntity.id.eq(carId))
            .execute()
    }

    /**
     * 자동차 ID로 CarEntity 삭제
     * @param carId
     */
    override fun deleteCar(carId: Long) {
        queryFactory
            .delete(carEntity)
            .where(carEntity.id.eq(carId))
            .execute()
    }
}
