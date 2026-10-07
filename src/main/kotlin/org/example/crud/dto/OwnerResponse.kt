package org.example.crud.dto

data class OwnerResponse(
  val id: Long,
  val name: String,
  val email: String,
  val phone: String,
  val motorcycles: List<MotorcycleResponse>
)
