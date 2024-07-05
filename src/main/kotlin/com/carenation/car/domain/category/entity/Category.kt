package com.carenation.car.domain.category.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val categoryName: String,

    @OneToMany(mappedBy = "categoryEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    val carCategories: MutableSet<CarCategoryEntity> = mutableSetOf()
)