package org.example.crud.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class OwnerRequest(
  @NotBlank
  val name: String?,

  @NotBlank
  @Email
  val email: String?,

  @NotBlank
  val phone: String?,
)
