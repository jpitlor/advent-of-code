package dev.pitlor.advent22

import org.junit.Test
import kotlin.math.abs
import kotlin.math.sign

class Day9 : DayBase(9) {
    data class Point(val x: Int, val y: Int)

    @Test
    fun part1() {
        var headLocation = Point(0, 0)
        var tailLocation = Point(0, 0)
        val locations = mutableSetOf(tailLocation)

        input.forEach {
            val (_, direction, amount) = Regex("(\\w) (\\d)").find(it)!!.groupValues
            repeat(amount.toInt()) {
                headLocation = when (direction) {
                    "U" -> Point(headLocation.x, headLocation.y + 1)
                    "D" -> Point(headLocation.x, headLocation.y - 1)
                    "L" -> Point(headLocation.x - 1, headLocation.y)
                    "R" -> Point(headLocation.x + 1, headLocation.y)
                    else -> throw Exception()
                }

                val xDiff = headLocation.x - tailLocation.x
                val yDiff = headLocation.y - tailLocation.y
                if (abs(xDiff) < 2 && abs(yDiff) < 2)

                tailLocation = Point(tailLocation.x + xDiff.sign, tailLocation.y + yDiff.sign)
                locations.add(tailLocation)
            }
        }
        println(locations.size)
    }
}
