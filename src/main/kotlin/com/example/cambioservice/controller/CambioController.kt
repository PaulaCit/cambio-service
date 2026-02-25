package com.example.cambioservice.controller

import com.example.cambioservice.model.Cambio
import com.example.cambioservice.repository.CambioRespository
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.math.RoundingMode

@RestController
@RequestMapping(value = ["cambio-service"])
class CambioController(
    private var environment: Environment,
    private var repository: CambioRespository,
) {

    @GetMapping(value = ["/{amount}/{from}/{to}"])
    fun getCambio(@PathVariable("amount") amount: BigDecimal,
                  @PathVariable("from") from: String,
                  @PathVariable("to") to: String,
    ): Cambio {

        val cambio = repository.findByFromAndTo(from, to) ?: throw  RuntimeException("Currency Unsupported")
        val port = environment.getProperty("local.server.port")

        val convesionFactor = cambio.conversionFactor
        val convertedValue = convesionFactor.multiply(amount)

        cambio.convertedValue = convertedValue.setScale(2, RoundingMode.CEILING)
        cambio.environment = port


        return cambio
    }
}