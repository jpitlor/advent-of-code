package dev.pitlor.advent21

import org.junit.Test
import kotlin.test.assertEquals

class Day6 : DayBase(6) {
    var fish = input[0].split(",").map(String::toInt).toMutableList()

    @Test
    fun part1() {
        val answer = 376194

        for (day in 1..80) {
            val newFish = fish.count { it == 0 }
            fish = fish.map { if (it == 0) 6 else it - 1 }.toMutableList()
            fish.addAll(List(newFish) { 8 })
        }
        assertEquals(answer, fish.size)
    }

    @Test
    fun part2() {
        val answer = 1693022481538UL

        var counts = fish.associateWith { fish.count { v -> v == it }.toULong() }.toMutableMap()
        for (day in 1..256) {
            counts = counts.mapKeys { (k) -> k - 1 }.toMutableMap()
            counts[6] = (counts[6] ?: 0UL) + (counts[-1] ?: 0UL)
            counts[8] = (counts[8] ?: 0UL) + (counts[-1] ?: 0UL)
            counts.remove(-1)
        }
        assertEquals(answer, counts.values.sum())
    }
}
