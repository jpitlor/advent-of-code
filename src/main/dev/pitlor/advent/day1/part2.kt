package main.dev.pitlor.advent.day1

import main.dev.pitlor.advent.Utils

fun main() {
    val lines = Utils.getInput(1).readLines().map(String::toInt).toList()
    val averages = lines.mapIndexed { i, x -> x + lines.getOrElse(i + 1) { 0 } + lines.getOrElse(i + 2) { 0 } }

    var timesIncreased = 0
    var last = Int.MAX_VALUE
    averages.forEach {
        if (it > last) timesIncreased++
        last = it
    }
    println(timesIncreased)
}