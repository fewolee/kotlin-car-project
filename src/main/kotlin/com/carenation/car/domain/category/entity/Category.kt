package com.carenation.car.domain.category.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    val carCategories: MutableList<CarCategory> = mutableListOf()
)