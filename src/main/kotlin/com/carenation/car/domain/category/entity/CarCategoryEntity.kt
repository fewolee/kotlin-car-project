package com.carenation.car.domain.category.entity

import com.carenation.car.domain.car.entity.CarEntity
import jakarta.persistence.*

@Entity
@Table(name = "car_category")
class CarCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "car_id", nullable = false)
    val carEntity: CarEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val categoryEntity: CategoryEntity
)