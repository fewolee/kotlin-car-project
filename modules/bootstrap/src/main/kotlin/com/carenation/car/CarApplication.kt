package com.carenation.car

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarApplication

fun main(args: Array<String>) {
    runApplication<CarApplication>(*args)
}
