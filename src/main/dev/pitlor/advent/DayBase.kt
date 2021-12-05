package main.dev.pitlor.advent

import java.io.File

open class DayBase(private val day: Int) {
    val input get() = File("src/main/dev/pitlor/advent/day$day/input.txt").readLines()
}