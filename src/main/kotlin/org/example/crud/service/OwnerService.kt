package org.example.crud.service

import org.example.crud.dto.OwnerRequest
import org.example.crud.dto.OwnerResponse
import org.example.crud.entity.Owner
import org.example.crud.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(
  private val repository: OwnerRepository,
) {

  fun create(request: OwnerRequest): OwnerResponse {
    val owner = Owner(
      name = request.name,
      phone = request.phone,
      email = request.email,
    )
    return repository.save(owner).toResponse()
  }

  fun getById(id: Long): OwnerResponse {
    return repository.findById(id)
      .orElseThrow { NoSuchElementException("Владелец с id $id не найден") }.toResponse()
  }

  fun getAllOwners(): List<OwnerResponse> {
    return repository.findAll().map { it.toResponse() }
  }

  fun update(id: Long, request: OwnerRequest): OwnerResponse {
    val owner =
      repository.findById(id).orElseThrow { NoSuchElementException("Владелец с id $id не найден") }

    owner.name = request.name
    owner.phone = request.phone
    owner.email = request.email

    return repository.save(owner).toResponse()
  }

  fun delete(id: Long) {
    repository.findById(id)
      .orElseThrow { NoSuchElementException("Владелец с id $id не найден") }

    repository.deleteById(id)
  }


  private fun Owner.toResponse(): OwnerResponse = OwnerResponse(
    id = this.id!!,
    name = this.name,
    phone = this.phone,
    email = this.email,
    motorcycles = this.motorcycles.map { motorcycle -> motorcycle.toResponse() }
  )
}