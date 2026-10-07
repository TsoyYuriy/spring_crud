package org.example.crud.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.example.crud.entity.MotorcycleType


data class MotorcycleRequest (
  @NotBlank
  val brand: String,

  @NotBlank
  val model: String,

  @Min(1900)
  @Max(2026)
  val year: Int,

  @Positive
  val price: Double,

  @Positive
  val engineVolume: Double,
  val type: MotorcycleType? = null,

  val ownerId: Long? = null,
  )