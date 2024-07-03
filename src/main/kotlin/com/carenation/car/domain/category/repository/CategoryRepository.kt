package com.carenation.car.domain.category.repository

import com.carenation.car.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

}
