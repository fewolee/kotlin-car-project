package com.carenation.car.domain.car.repository

import com.carenation.car.domain.car.dto.CarInfoDto
import com.carenation.car.domain.car.entity.QCarEntity.carEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service

@Service
class CarQuerydslRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CarQuerydslRepository {


//    @Autowired
//    lateinit var queryFactory: JPAQueryFactory
//

    override fun findByCustomId(carId: Long): CarInfoDto {
//
//        var carInfoDto = queryFactory.select(Projections.constructor(CarInfoDto.class, carEntity.modelName)).from(carEntity).where(carEntity.id.eq(carId))


        val carEntity = queryFactory.select(carEntity).from(carEntity).where(carEntity.id.eq(carId)).fetchOne()


        val categoryNames = carEntity?.carCategories?.map { it.categoryEntity.categoryName }

        if (carEntity != null) {
            return CarInfoDto(
                modelName = carEntity.modelName,
                manufacture = carEntity.manufacture,
                productionYear = carEntity.productionYear,
                rentAvailable = carEntity.rentAvailable,
                categoryNames = categoryNames
            )

        }
    }






