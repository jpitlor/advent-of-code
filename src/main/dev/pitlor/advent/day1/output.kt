package main.dev.pitlor.advent.day1

import main.dev.pitlor.advent.Utils
import org.junit.Test
import kotlin.test.assertEquals

class Day1 {
    private val input = Utils.getInput(1).readLines().map(String::toInt)

    @Test
    fun part1() {
        val answer = 1451

        val timesIncreased = input.foldRightIndexed(0) { i, d, acc ->
            if (i > 0 && input[i - 1] < d) acc + 1
            else acc
        }

        assertEquals(answer, timesIncreased)
    }

    @Test
    fun part2() {
        val answer = 1395

        val averages = input.mapIndexed { i, x -> x + input.getOrElse(i + 1) { 0 } + input.getOrElse(i + 2) { 0 } }

        var timesIncreased = 0
        var last = Int.MAX_VALUE
        averages.forEach {
            if (it > last) timesIncreased++
            last = it
        }

        assertEquals(answer, timesIncreased)
    }
}