package com.carenation.car.domain.category.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // pk

    @Column(nullable = false)
    val categoryName: String, // 카테고리 이름

)