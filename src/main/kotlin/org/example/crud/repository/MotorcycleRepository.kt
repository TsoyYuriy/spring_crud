package org.example.crud.repository

import org.example.crud.entity.Motorcycle
import org.example.crud.entity.MotorcycleType
import org.springframework.data.jpa.repository.JpaRepository

interface MotorcycleRepository : JpaRepository<Motorcycle, Long> {
  fun findByYear(year: Int): List<Motorcycle>
  fun findByType(type: MotorcycleType): List<Motorcycle>
  fun findByTypeAndYear(type: MotorcycleType, year: Int): Motorcycle?
}