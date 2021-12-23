package main.dev.pitlor.advent.day17

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.math.sign
import kotlin.test.assertEquals

class Day17 : DayBase(17) {
    private val limits = Regex("target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)")
        .find(input.first())!!
        .groupValues
        .drop(1)
        .map(String::toInt)
    private fun willHit(dX0: Int, dY0: Int): Pair<Boolean, Int> {
        val (minX, maxX, minY, maxY) = limits
        var x = 0
        var y = 0
        var dX = dX0
        var dY = dY0
        var maxYInPath = y
        while (true) {
            x += dX
            y += dY
            dX += -1 * sign(dX.toDouble()).toInt()
            dY--
            if (y > maxYInPath) maxYInPath = y
            if (x in minX..maxX && y in minY..maxY) return Pair(true, maxYInPath)
            if (x > maxX || (dY < 0 && y < minY)) return Pair(false, maxYInPath)
        }
    }

    @Test
    fun part1() {
        val answer = 5253

        var maxOfMaxY = 0
        for (dX0 in 0..10000) {
            for (dY0 in -10000..10000) {
                val (hit, y) = willHit(dX0, dY0)
                if (hit && y > maxOfMaxY) maxOfMaxY = y
            }
        }
        assertEquals(answer, maxOfMaxY)
    }

    @Test
    fun part2() {
        val answer = 1770
        var successes = 0
        for (dX0 in 0..10000) {
            for (dY0 in -10000..10000) {
                val (hit) = willHit(dX0, dY0)
                if (hit) successes++
            }
        }
        assertEquals(answer, successes)
    }
}