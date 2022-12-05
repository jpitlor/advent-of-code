package dev.pitlor.advent22

import java.io.File

open class DayBase(private val day: Int) {
    val input get() = File("src/main/resources/day$day.txt").readLines()

    fun <T> List<List<T>>.rotate(): List<List<T>> {
        return (0 until this[0].size).map { i -> map { it[i] }.reversed() }
    }
}
