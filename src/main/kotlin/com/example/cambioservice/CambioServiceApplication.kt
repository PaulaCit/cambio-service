package com.example.cambioservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CambioServiceApplication

fun main(args: Array<String>) {
    runApplication<CambioServiceApplication>(*args)
}
