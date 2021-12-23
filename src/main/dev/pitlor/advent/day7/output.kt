package main.dev.pitlor.advent.day7

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class Day7 : DayBase(7) {
    @Test
    fun part1() {
        val answer = 347449

        val positions = input[0].split(",").map(String::toInt)
        var minFuelSpent = Int.MAX_VALUE
        for (alignment in positions.minOrNull()!!..positions.maxOrNull()!!) {
            // We could use `.sum`, but it's less efficient since we can stop part
            // way through summing manually
            var fuelSpent = 0
            for (position in positions) {
                fuelSpent += abs(position - alignment)
                if (fuelSpent >= minFuelSpent) break
            }
            if (fuelSpent < minFuelSpent) minFuelSpent = fuelSpent
        }

        assertEquals(answer, minFuelSpent)
    }

    @Test
    fun part2() {
        val answer = 98039527

        fun crabSum(n: Int): Int {
            var sum = 0
            for (i in 1..n) {
                sum += i
            }
            return sum
        }

        val positions = input[0].split(",").map(String::toInt)
        var minFuelSpent = Int.MAX_VALUE
        for (alignment in positions.minOrNull()!!..positions.maxOrNull()!!) {
            // We could use `.sum`, but it's less efficient since we can stop part
            // way through summing manually
            var fuelSpent = 0
            for (position in positions) {
                fuelSpent += crabSum(abs(position - alignment))
                if (fuelSpent >= minFuelSpent) break
            }
            if (fuelSpent < minFuelSpent) minFuelSpent = fuelSpent
        }

        assertEquals(answer, minFuelSpent)
    }
}