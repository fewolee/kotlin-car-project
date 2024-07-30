package com.carenation.car.adapter.data.jpa.repository

import com.carenation.adapter.data.jpa.car.persistence.entity.QCarEntity.carEntity
import com.carenation.car.application.dto.CarUpdateInDto
import com.carenation.car.application.port.out.CarUpdateRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * CarUpdateRepository 인터페이스의 구현체
 */
@Component
class CarUpdateRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val carDeleteRepository: CarDeleteRepositoryImpl,
    private val carCreateRepository: CarCreateRepositoryImpl,
) : CarUpdateRepository {
    /**
     * 자동차 정보 수정
     * @param CarUpdateInDto
     */
    override fun updateCar(req: CarUpdateInDto) {
        // 자동차 정보 수정
        queryFactory
            .update(carEntity)
            .where(carEntity.id.eq(req.id))
            .set(carEntity.modelName, req.modelName)
            .set(
                carEntity.manufacture,
                req.manufacture,
            ).set(carEntity.productionYear, req.productionYear)
            .set(carEntity.rentAvailable, req.rentAvailable)
            .execute()
    }

    /**
     * CarCategory Entity 정보 수정
     * @param carId, categoryNames
     */
    override fun updateCarCategory(
        carId: Long,
        categoryNames: List<String>,
    ) {
        // 기존 CarCategory 엔티티 삭제
        carDeleteRepository.deleteCarCategory(carId)
        // 수정된 CarCategory 엔티티 영속화
        carCreateRepository.saveCarCategory(categoryNames, carId)
    }
}
