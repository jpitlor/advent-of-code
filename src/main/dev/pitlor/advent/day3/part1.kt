package main.dev.pitlor.advent.day3

import main.dev.pitlor.advent.Utils

private val answer = 3277364

fun main() {
    fun <T> List<T>.mean(): T? {
        return distinct().maxByOrNull { x -> count { x == it } }
    }

    val numbers = Utils.getInput(3).readLines()

    var gammaStr = ""
    for (i in 0..11) {
        gammaStr += numbers.map { it[i] }.mean()
    }

    val gamma = gammaStr.toInt(2)
    val epsilon = gamma.inv() and 0b111111111111
    println(gamma * epsilon)
}