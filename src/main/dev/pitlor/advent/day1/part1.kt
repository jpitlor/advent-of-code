package main.dev.pitlor.advent.day1

import main.dev.pitlor.advent.Utils

fun main() {
    var timesIncreased = 0
    var last = Int.MAX_VALUE
    Utils.getInput(1).forEachLine {
        val current = it.toInt()
        if (current > last) timesIncreased++
        last = current
    }
    println(timesIncreased)
}