package org.example.crud.controller

import jakarta.validation.Valid
import org.example.crud.dto.MotorcycleRequest
import org.example.crud.dto.MotorcycleResponse
import org.example.crud.service.MotorcycleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/motorcycles")
class MotorcycleController(private val service: MotorcycleService) {

  @PostMapping
  fun create(@Valid @RequestBody motorcycle: MotorcycleRequest): MotorcycleResponse {
    return service.create(motorcycle)
  }

  @GetMapping
  fun findAll(): List<MotorcycleResponse> {
    return service.getAll()
  }


  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): MotorcycleResponse {
    return service.getById(id)
  }

  @PutMapping("/{id}")
  fun update(
    @PathVariable id: Long,
    @Valid @RequestBody request: MotorcycleRequest
  ): MotorcycleResponse {
    return service.update(id, request)
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long) {
    return service.delete(id)
  }
}