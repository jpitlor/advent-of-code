package dev.pitlor.advent22

import org.junit.Test
import kotlin.test.assertEquals

class Day3 : DayBase(3) {
    @Test
    fun part1() {
        val answer = 7875
        val prioritySum = input
            .map { it.toCharArray().toList().chunked(it.length / 2) }
            .sumOf { (c1, c2) -> c1
                .intersect(c2.toSet())
                .sumOf { c -> if (c.isLowerCase()) (c - 'a') + 1 else (c - 'A') + 27 }
            }

        assertEquals(answer, prioritySum)
    }

    @Test
    fun part2() {
        val answer = 2479
        val prioritySum = input
            .chunked(3)
            .sumOf { (c1, c2, c3) ->
                c1
                    .toCharArray()
                    .intersect(c2.toSet())
                    .intersect(c3.toSet())
                    .sumOf { c -> if (c.isLowerCase()) (c - 'a') + 1 else (c - 'A') + 27 }
            }

        assertEquals(answer, prioritySum)
    }
}
