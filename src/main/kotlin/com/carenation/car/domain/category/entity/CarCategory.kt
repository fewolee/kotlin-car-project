package com.carenation.car.domain.category.entity

import com.carenation.car.domain.car.entity.Car
import jakarta.persistence.*

@Entity
@Table(name = "car_category")
data class CarCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "car_id")
    val car: Car,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category
)