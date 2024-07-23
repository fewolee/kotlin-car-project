package com.carenation.car.adapter.out.persistence.entity

import jakarta.persistence.*

/**
 * Car Entity와 Category Entity의 N:M 중간 매핑을 위한 엔티티
 */
@Entity
@Table(name = "car_category")
class CarCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // pk

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "car_id", nullable = false)
    val carEntity: CarEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val categoryEntity: CategoryEntity
)