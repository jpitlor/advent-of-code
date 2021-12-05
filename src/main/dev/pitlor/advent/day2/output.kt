package main.dev.pitlor.advent.day2

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.test.assertEquals

class Day2 : DayBase(2) {
    @Test
    fun part1() {
        val answer = 1524750

        var x = 0
        var y = 0
        input.forEachLine {
            val (command, amount) = it.split(" ")
            when (command) {
                "forward" -> x += amount.toInt()
                "up" -> y -= amount.toInt()
                "down" -> y += amount.toInt()
            }
        }

        assertEquals(answer, x * y)
    }

    @Test
    fun part2() {
        val answer = 1592426537

        var x = 0
        var y = 0
        var aim = 0
        input.forEachLine {
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

        assertEquals(answer, x * y)
    }
}