package main.dev.pitlor.advent.day1

import java.io.File

fun main() {
    var timesIncreased = 0
    var last = Int.MAX_VALUE
    File("src/main/dev/pitlor/advent/day1/input.txt").forEachLine {
        val current = it.toInt()
        if (current > last) timesIncreased++
        last = current
    }
    println(timesIncreased)
}