package main.dev.pitlor.advent.day2

import main.dev.pitlor.advent.Utils
import kotlin.test.assertEquals

private const val answer = 1524750

fun main() {
    var x = 0
    var y = 0
    Utils.getInput(2).forEachLine {
        val (command, amount) = it.split(" ")
        when (command) {
            "forward" -> x += amount.toInt()
            "up" -> y -= amount.toInt()
            "down" -> y += amount.toInt()
        }
    }

    assertEquals(answer, x * y)
}