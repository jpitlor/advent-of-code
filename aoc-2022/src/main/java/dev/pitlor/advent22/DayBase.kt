package dev.pitlor.advent22

import java.io.File

open class DayBase(private val day: Int) {
    val input get() = File("src/main/resources/day$day.txt").readLines()
}
