package main.dev.pitlor.advent.day1

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.test.assertEquals

class Day1 : DayBase(1) {
    private val depths = input.readLines().map(String::toInt)

    @Test
    fun part1() {
        val answer = 1451

        val timesIncreased = depths.foldRightIndexed(0) { i, d, acc ->
            if (i > 0 && depths[i - 1] < d) acc + 1
            else acc
        }

        assertEquals(answer, timesIncreased)
    }

    @Test
    fun part2() {
        val answer = 1395

        val averages = depths.mapIndexed { i, x ->
            x + depths.getOrElse(i + 1) { 0 } + depths.getOrElse(i + 2) { 0 }
        }

        val timesIncreased = averages.foldRightIndexed(0) { i, d, acc ->
            if (i > 0 && averages[i - 1] < d) acc + 1
            else acc
        }

        assertEquals(answer, timesIncreased)
    }
}