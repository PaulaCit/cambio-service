package com.example.cambioservice.repository

import com.example.cambioservice.model.Cambio
import org.springframework.data.jpa.repository.JpaRepository

interface CambioRespository : JpaRepository<Cambio, Long> {

    fun findByFromAndTo(from: String, to: String): Cambio?
}