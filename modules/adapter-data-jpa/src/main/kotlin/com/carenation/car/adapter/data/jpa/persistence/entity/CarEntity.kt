package com.carenation.car.adapter.data.jpa.persistence.entity

import jakarta.persistence.*

/**
 * Car의 핵심 정보들을 갖고 있는 Entity
 */
@Entity
@Table(name = "car")
class CarEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // pk
    @Column(nullable = false)
    var modelName: String, // 모델명
    @Column(nullable = false)
    var manufacture: String, // 제조사
    @Column(nullable = false)
    var productionYear: Int, // 생산년도
    @Column(nullable = false)
    var rentAvailable: Boolean, // 대여가능여부
)
