package com.carenation.car.adapter.out.repository

import com.carenation.car.adapter.out.persistence.entity.CarCategoryEntity
import com.carenation.car.adapter.out.persistence.entity.CarEntity
import com.carenation.car.adapter.out.persistence.entity.QCarEntity.carEntity
import com.carenation.car.dto.CarCreateInDto
import com.carenation.car.port.out.CarCreateRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

/**
 * CarCreateRepository 인터페이스의 구현체
 */
@Component
class CarCreateRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val entityManager: EntityManager,
    private val carReadRepository: CarReadRepositoryImpl,
) : CarCreateRepository {
    /**
     * Car Entity 영속화
     * @param CarCreateInDto
     * @return carId
     */
    override fun saveCar(req: CarCreateInDto): Long? {
        val car =
            CarEntity(
                modelName = req.modelName,
                manufacture = req.manufacture,
                productionYear = req.productionYear,
                rentAvailable = req.rentAvailable,
            )
        // Car Entity 영속화
        entityManager.persist(car)

        return car.id
    }

    /**
     * CarCategory Entity 영속화
     * @param categoryNames, carEntity
     */
    override fun saveCarCategory(
        categoryNames: List<String>,
        carId: Long,
    ) {
        val car =
            queryFactory
                .select(carEntity)
                .from(carEntity)
                .where(carEntity.id.eq(carId))
                .fetchOne()
                ?: throw IllegalArgumentException("존재하는 자동차 ID $carId 이 없습니다")

        categoryNames.forEach { category ->
            val categoryName = carReadRepository.getCategoryModelByCategoryName(category).categoryName
            val categoryEntity = carReadRepository.getCategoryEntityByCategoryName(categoryName)
            val carCategoryEntity = CarCategoryEntity(carEntity = car, categoryEntity = categoryEntity)
            // CarCategory 엔티티 영속화
            entityManager.persist(carCategoryEntity)
        }
    }
}
