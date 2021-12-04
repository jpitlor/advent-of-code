package main.dev.pitlor.advent.day2

import main.dev.pitlor.advent.Utils

private val answer = 1592426537

fun main() {
    var x = 0
    var y = 0
    var aim = 0
    Utils.getInput(2).forEachLine {
        val (command, amount) = it.split(" ")
        when (command) {
            "forward" -> {
                x += amount.toInt()
                y += aim * amount.toInt()
            }
            "up" -> aim -= amount.toInt()
            "down" -> aim += amount.toInt()
        }
    }

    println(x * y)
}