package com.carenation.car.domain.car.entity

import com.carenation.car.domain.category.entity.CarCategoryEntity
import jakarta.persistence.*

@Entity
@Table(name = "car")
data class CarEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // pk

    @Column(nullable = false)
    var modelName: String , // 모델명

    @Column(nullable = false)
    var manufacture: String, // 제조사

    @Column(nullable = false)
    var productionYear : Int, //생산년도

    @Column(nullable = false)
    var rentAvailable: Boolean, // 대여가능여부

    @OneToMany(mappedBy = "carEntity", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val carCategories: MutableSet<CarCategoryEntity> = mutableSetOf()

)