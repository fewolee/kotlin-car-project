package com.carenation.car.domain.car.entity

import jakarta.persistence.*

@Entity
@Table(name = "car")
class CarEntity(
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

) {
    // 자동차의 정보들을 수정
     fun updateInfo(modelName: String, manufacture: String, productionYear: Int, rentAvailable: Boolean) {
        this.modelName = modelName
        this.manufacture = manufacture
        this.rentAvailable = rentAvailable
        this.productionYear = productionYear
    }
}

