package com.carenation.car.domain.car.service


interface CarDeleteServiceBus {
    fun delete(carId: Long): Unit
}