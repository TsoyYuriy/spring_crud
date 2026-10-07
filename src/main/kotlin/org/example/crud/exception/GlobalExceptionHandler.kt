package org.example.crud.exception

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(NoSuchElementException::class)
  fun handleNotFoundException(ex: NoSuchElementException): ResponseEntity<String> {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
    var errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
  }
}