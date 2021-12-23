package main.dev.pitlor.advent.day11

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.test.assertEquals

typealias T = List<List<ObservableInt>>

data class ObservableInt(var value: Int, val flash: () -> Unit) {
    fun inc() {
        value++
        if (value == 10) flash()
    }

    fun reset() {
        if (value > 9) value = 0
    }
}

class Day11 : DayBase(11) {
    @Test
    fun part1() {
        val answer = 1694

        var flashes = 0
        val feedbackQueue = mutableListOf<Pair<Int, Int>>()
        val levels = input
            .map(String::splitChars)
            .crossMapIndexed { c, i, j, matrix -> ObservableInt(c.toInt()) {
                flashes++
                for (k in max(0, i - 1)..min(matrix.size - 1, i + 1)) {
                    for (l in max(0, j - 1)..min(matrix[i].size - 1, j + 1)) {
                        feedbackQueue += Pair(k, l)
                    }
                }
            } }

        for (step in 1..100) {
            levels.crossForEach(ObservableInt::inc)
            while(feedbackQueue.isNotEmpty()) {
                val (i, j) = feedbackQueue.removeAt(0)
                levels[i][j].inc()
            }
            levels.crossForEach(ObservableInt::reset)
        }

        assertEquals(answer, flashes)
    }

    @Test
    fun part2() {
        val answer = 346

        val feedbackQueue = mutableListOf<Pair<Int, Int>>()
        val levels = input
            .map(String::splitChars)
            .crossMapIndexed { c, i, j, matrix -> ObservableInt(c.toInt()) {
                for (k in max(0, i - 1)..min(matrix.size - 1, i + 1)) {
                    for (l in max(0, j - 1)..min(matrix[i].size - 1, j + 1)) {
                        feedbackQueue += Pair(k, l)
                    }
                }
            } }

        var step = 0
        while (!levels.crossAll { it.value == 0 }) {
            levels.crossForEach(ObservableInt::inc)
            while(feedbackQueue.isNotEmpty()) {
                val (i, j) = feedbackQueue.removeAt(0)
                levels[i][j].inc()
            }
            levels.crossForEach(ObservableInt::reset)
            step++
        }

        assertEquals(answer, step)
    }
}