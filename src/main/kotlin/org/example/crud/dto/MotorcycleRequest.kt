package org.example.crud.dto

import jakarta.validation.constraints.*
import org.example.crud.entity.MotorcycleType


data class MotorcycleRequest(
  @NotBlank
  val brand: String?,

  @NotBlank
  val model: String?,

  @Min(1900)
  @Max(2026)
  @NotNull
  val year: Int?,

  @Positive @NotNull
  val price: Double?,

  @Positive @NotNull
  val engineVolume: Double?,
  val type: MotorcycleType? = null,

  val ownerId: Long? = null,
)