package com.example.cambioservice.controller

import com.example.cambioservice.model.Cambio
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping(value = ["cambio-service"])
class CambioController(
    private var environment: Environment
) {

    @GetMapping(value = ["/{amount}/{from}/{to}"])
    fun getCambio(@PathVariable("amount") amount: BigDecimal,
                  @PathVariable("from") from: String,
                  @PathVariable("to") to: String,
    ): Cambio {

        val port = environment.getProperty("local.server.port")


        return Cambio(
            id = 1L,
            from = from,
            to = to,
            conversionFactor = BigDecimal.ZERO,
            convertedValue = BigDecimal.ONE,
            environment = "PORT $port",
        )
    }
}