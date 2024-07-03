package com.carenation.car.domain.car.entity

import com.carenation.car.domain.category.entity.CarCategory
import jakarta.persistence.*

@Entity
@Table(name = "car")
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val model: String,

    @Column(nullable = false)
    val rentAvailable: Boolean,

    @OneToMany(mappedBy = "car", cascade = [CascadeType.ALL], orphanRemoval = true)
    val carCategories: MutableList<CarCategory> = mutableListOf()
)