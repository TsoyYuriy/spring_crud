package org.example.crud.exception

data class ErrorResponse(
  val status: Int,
  val error: String,
  val message: String? = null,
  val errors: Map<String, String?>? = null,
)
