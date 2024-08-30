package com.example.demo.client

import com.example.demo.model.CommercialPlace
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*

suspend fun main() {
	val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			gson()
		}
	}

	try {
		val places: List<CommercialPlace> = client.get("http://localhost:8080/places").body()

		places.forEach {
			println(it)
		}
	} catch (e: Exception) {
		println("Error: ${e.message}")
	} finally {
		client.close()
	}
}