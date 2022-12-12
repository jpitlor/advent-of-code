package dev.pitlor.advent22

import org.junit.Test
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.test.assertEquals

class Day10 : DayBase(10) {
    private val registerValues = input
        .asSequence()
        .map { Regex("addx (.*)").find(it)?.groupValues?.get(1)?.toInt() ?: 0 }
        .fold(mutableListOf(1)) { acc, x ->
            acc.add(acc.last());
            if (x != 0) acc.add(acc.last() + x);
            acc
        }

    @Test
    fun part1() {
        val answer = 14360
        val sum = registerValues
            .filterIndexed { i, _ -> i % 40 == 19 }
            .mapIndexed { i, x -> (i * 40 + 20) * x }
            .sum()

        assertEquals(answer, sum)
    }

    @Test
    fun part2() {
        registerValues
            .chunked(40)
            .forEach { r -> println(r.mapIndexed { i, x -> if (abs(x - i) < 2) "#" else "." }.joinToString("")) }

        // TODO How do we assert?
    }
}
