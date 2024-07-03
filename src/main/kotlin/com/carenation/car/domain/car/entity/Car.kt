package com.carenation.car.domain.car.entity

import com.carenation.car.domain.car.enum.CarCategory
import jakarta.persistence.*

@Entity
@Table(name = "car")
data class Car(
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val categories : Set<CarCategory> // 자동차 카테고리

)