package org.example.crud.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.module.kotlin.KotlinInvalidNullException

@RestControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(NoSuchElementException::class)
  fun handleNotFoundException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
    return buildError(HttpStatus.NOT_FOUND, ex.message)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
    val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }

    return buildError(HttpStatus.BAD_REQUEST, "Ошибка валидации", errors)
  }

  @ExceptionHandler(OwnerHasMotorcyclesException::class)
  fun handleHasMoto(ex: OwnerHasMotorcyclesException): ResponseEntity<ErrorResponse> {
    return buildError(HttpStatus.CONFLICT, ex.message)
  }

  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
    val cause = ex.cause as? KotlinInvalidNullException

    return if (cause != null) {
      val message = "Обязательное поле ${cause.kotlinPropertyName}"
      buildError(HttpStatus.BAD_REQUEST, message)
    } else {
      buildError(HttpStatus.BAD_REQUEST, "Некорректное тело запроса")
    }
  }

  private fun buildError(
    httpStatus: HttpStatus,
    message: String?,
    errors: Map<String, String?>? = null
  ): ResponseEntity<ErrorResponse> {
    val errorData = ErrorResponse(
      status = httpStatus.value(),
      error = httpStatus.reasonPhrase,
      message,
      errors,
    )

    return ResponseEntity.status(httpStatus).body(errorData)
  }
}