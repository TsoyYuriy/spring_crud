package org.example.crud.service

import org.example.crud.dto.MotorcycleRequest
import org.example.crud.dto.MotorcycleResponse
import org.example.crud.dto.OwnerSummary
import org.example.crud.entity.Motorcycle
import org.example.crud.repository.MotorcycleRepository
import org.example.crud.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class MotorcycleService(
  private val repository: MotorcycleRepository,
  private val ownerRepository: OwnerRepository
) {

  fun create(request: MotorcycleRequest): MotorcycleResponse {

    val owner = request.ownerId?.let { id ->
      ownerRepository.findById(id).orElseThrow { NoSuchElementException("Такого владельца нет :(") }
    }

    val motorcycle = Motorcycle(
      brand = request.brand,
      model = request.model,
      year = request.year,
      price = request.price,
      engineVolume = request.engineVolume,
      type = request.type,
      owner = owner
    )

    val saved = repository.save(motorcycle)

    return saved.toResponse();
  }

  fun getById(id: Long): MotorcycleResponse {

    val motorcycle = repository.findById(id)
      .orElseThrow { NoSuchElementException("Мотоцикл с id $id не найден") }

    return motorcycle.toResponse()
  }

  fun getAll(): List<MotorcycleResponse> {
    val motorcycles = repository.findAll()

    return motorcycles.map { it.toResponse() }
  }

  fun update(id: Long, request: MotorcycleRequest): MotorcycleResponse {
    val motorcycle = repository.findById(id)
      .orElseThrow { NoSuchElementException("Мотоцикл с id $id не найден") }

    motorcycle.brand = request.brand
    motorcycle.model = request.model
    motorcycle.year = request.year
    motorcycle.price = request.price
    motorcycle.engineVolume = request.engineVolume
    motorcycle.type = request.type

    val saved = repository.save(motorcycle)
    return saved.toResponse()

  }

  fun delete(id: Long) {

    repository.findById(id)
      .orElseThrow { NoSuchElementException("Мотоцикл с id $id не найден") }

    repository.deleteById(id)
  }
}

fun Motorcycle.toResponse(): MotorcycleResponse = MotorcycleResponse(
  id = this.id!!,
  brand = this.brand,
  model = this.model,
  year = this.year,
  price = this.price,
  engineVolume = this.engineVolume,
  type = this.type,
  owner = this.owner?.let { OwnerSummary(it.id!!, it.name, it.phone, it.email) }
)