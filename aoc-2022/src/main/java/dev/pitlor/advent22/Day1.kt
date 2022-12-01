package dev.pitlor.advent22

import org.junit.Test
import kotlin.test.assertEquals

class Day1 : DayBase(1) {
    @Test
    fun part1() {
        val answer = 71023
        val maxCalories = input
            .fold(mutableListOf(0)) { acc, it ->
                if (it == "") acc.add(0)
                else acc[acc.size - 1] += it.toInt()
                return@fold acc
            }
            .maxOrNull()

        assertEquals(answer, maxCalories)
    }

    @Test
    fun part2() {
        val answer = 206289
        val top3 = input
            .fold(mutableListOf(0)) { acc, it ->
                if (it == "") acc.add(0)
                else acc[acc.size - 1] += it.toInt()
                return@fold acc
            }
            .sortedDescending()
            .subList(0, 3)
            .sum()

        assertEquals(answer, top3)
    }
}
