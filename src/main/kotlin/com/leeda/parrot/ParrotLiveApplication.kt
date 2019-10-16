package com.leeda.parrot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParrotLiveApplication

fun main(args: Array<String>) {
    runApplication<ParrotLiveApplication>(*args)
}