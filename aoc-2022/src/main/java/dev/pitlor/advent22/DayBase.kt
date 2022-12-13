package dev.pitlor.advent22

import java.io.File
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

open class DayBase(private val day: Int) {
    private val inputPath = "src/main/resources/day$day.txt"

    val input: List<String> get() {
        if (!File(inputPath).exists()) {
            downloadInput()
        }

        return File(inputPath).readLines()
    }

    private fun downloadInput() = runBlocking {
        val response = HttpClient(CIO).get("https://adventofcode.com/2022/day/$day/input") {
            cookie("session", System.getenv("AOC_SESSION_TOKEN"))
        }

        if (response.status.value !in 200..299) {
            throw Exception("Could not fetch puzzle input")
        }

        val responseBody: ByteArray = response.body()
        File(inputPath).writeBytes(responseBody)
        println("A file saved to $inputPath")
    }

    fun <T> List<List<T>>.rotate(): List<List<T>> {
        return (0 until this[0].size).map { i -> map { it[i] }.reversed() }
    }
}
