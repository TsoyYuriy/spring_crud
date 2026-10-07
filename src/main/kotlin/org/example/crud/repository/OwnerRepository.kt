package org.example.crud.repository

import org.example.crud.entity.Owner
import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository: JpaRepository<Owner, Long> {
  fun findByPhone(phone: String): Owner?
  fun findByEmail(email: String): Owner?
}