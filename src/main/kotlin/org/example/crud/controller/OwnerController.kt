package org.example.crud.controller

import jakarta.validation.Valid
import org.example.crud.dto.OwnerRequest
import org.example.crud.dto.OwnerResponse
import org.example.crud.service.OwnerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/owners")
class OwnerController(private val service: OwnerService) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@Valid @RequestBody owner: OwnerRequest): OwnerResponse {
    return service.create(owner)
  }

  @GetMapping
  fun findAll(): List<OwnerResponse> {
    return service.getAllOwners()
  }


  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): OwnerResponse {
    return service.getById(id)
  }

  @PutMapping("/{id}")
  fun update(
    @PathVariable id: Long,
    @Valid @RequestBody request: OwnerRequest
  ): OwnerResponse {
    return service.update(id, request)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable id: Long) {
    return service.delete(id)
  }
}