package com.carenation.car.domain.category.entity
import jakarta.persistence.*
@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val categoryName: String,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val carCategories: MutableSet<CarCategory> = mutableSetOf()
)