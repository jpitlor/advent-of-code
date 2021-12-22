package main.dev.pitlor.advent.day17

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.math.sign

class Day17 : DayBase(17) {
    @Test
    fun part1() {
        val (minX, maxX, minY, maxY) = Regex("target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)")
            .find(input.first())!!
            .groupValues
            .drop(1)
            .map(String::toDouble)
        fun willHit(dX0: Double, dY0: Double): Pair<Boolean, Double> {
            var x = 0.0
            var y = 0.0
            var dX = dX0
            var dY = dY0
            var maxYInPath = y
            while (x < maxX && y < minY) {
                x += dX
                y += dY
                dX += -1 * sign(dX)
                dY--
                if (y > maxYInPath) maxYInPath = y
                if (x in minX..maxX && y in minY..maxY) return Pair(true, maxYInPath)
            }
            return Pair(false, maxYInPath)
        }

        var maxOfMaxY = 0.0
        for (dX0 in 0..100) {
            for (dY0 in -100..100) {
                val (hit, y) = willHit(dX0.toDouble(), dY0.toDouble())
                if (hit && y > maxOfMaxY) maxOfMaxY = y
            }
        }
        println(maxOfMaxY)
    }

    @Test
    fun part2() {
    }
}