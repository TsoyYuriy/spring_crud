package org.example.crud.dto

import org.example.crud.entity.MotorcycleType

data class MotorcycleResponse(
  val id: Long,
  val brand: String,
  val model: String,
  val year: Int,
  val price: Double,
  val engineVolume: Double,
  val type: MotorcycleType? = null,
  val owner: OwnerSummary? = null,
)