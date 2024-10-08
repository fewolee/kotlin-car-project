package com.carenation.car.adapter.data.jpa.persistence.entity

import jakarta.persistence.*

/**
 * Car의 Category 이름을 나타내기 위한 Entity
 */
@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // pk
    @Column(nullable = false)
    val categoryName: String, // 카테고리 이름
)
