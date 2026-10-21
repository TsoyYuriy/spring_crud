package org.example.crud.entity

import jakarta.persistence.*

@Entity
class Owner(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  var name: String,
  var email: String,
  var phone: String,

  @OneToMany(mappedBy = "owner")
  var motorcycles: MutableList<Motorcycle> = mutableListOf()
)