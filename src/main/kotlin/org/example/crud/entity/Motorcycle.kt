package org.example.crud.entity

import jakarta.persistence.*

@Entity
class Motorcycle(
  var brand: String,
  var model: String,

  @ManyToOne(fetch = FetchType.EAGER)
  var owner: Owner? = null,

  @Column(name = "release_year")
  var year: Int,

  var price: Double,
  var engineVolume: Double,

  @Enumerated(EnumType.STRING)
  var type: MotorcycleType? = null,

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
)