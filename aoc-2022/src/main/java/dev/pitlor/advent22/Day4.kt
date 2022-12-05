package dev.pitlor.advent22

import org.junit.Test
import kotlin.test.assertEquals

class Day4 : DayBase(4) {
    @Test
    fun part1() {
        val answer = 496
        val totalContained = input
            .map { it.split(",").map { e -> e.split("-").map(String::toInt) } }
            .count { it[0][0] >= it[1][0] && it[0][1] <= it[1][1] || it[1][0] >= it[0][0] && it[1][1] <= it[0][1] }
        assertEquals(answer, totalContained)
    }

    @Test
    fun part2() {
        val answer = 847
        val totalContained = input
            .map { it.split(",").map { e -> e.split("-").map(String::toInt) } }
            .count { it[0][1] >= it[1][0] && it[0][1] <= it[1][1] || it[1][1] >= it[0][0] && it[1][1] <= it[0][1] }
        assertEquals(answer, totalContained)
    }
}
