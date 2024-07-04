package com.carenation.car.domain.category.entity

import com.carenation.car.domain.car.entity.Car
import jakarta.persistence.*

@Entity
data class CarCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    val car: Car,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category
)