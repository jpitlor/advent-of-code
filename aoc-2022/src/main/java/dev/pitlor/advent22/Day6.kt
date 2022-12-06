package dev.pitlor.advent22

import org.junit.Test
import kotlin.test.assertEquals

class Day6 : DayBase(6) {
    private fun findStart(packetSize: Int): Int {
        return input[0]
            .windowed(packetSize)
            .indexOfFirst { it.toList().distinct().size == packetSize } + packetSize
    }

    @Test
    fun part1() {
        val answer = 1155
        val packetStart = findStart(4)

        assertEquals(answer, packetStart)
    }

    @Test
    fun part2() {
        val answer = 2789
        val packetStart = findStart(14)

        assertEquals(answer, packetStart)
    }
}
