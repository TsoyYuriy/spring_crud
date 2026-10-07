package org.example.crud.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Owner (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  var name: String,
  var email: String,
  var phone: String,

  @OneToMany(mappedBy = "owner")
  var motorcycles: MutableList<Motorcycle> = mutableListOf()
)