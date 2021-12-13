package main.dev.pitlor.advent.day12

import main.dev.pitlor.advent.DayBase
import org.junit.Test

class Day12 : DayBase(12) {
    private val START = "start"
    private val END = "end"

    @Test
    fun part1() {
        val adjacents = mutableMapOf<String, MutableList<String>>()
        input.forEach {
            val (n1, n2) = it.split("-")
            adjacents[n1]?.add(n2) ?: adjacents.put(n1, mutableListOf(n2))
            adjacents[n2]?.add(n1) ?: adjacents.put(n2, mutableListOf(n1))
        }

        val tempAdjacents = adjacents.toMutableMap()

    }

    @Test
    fun part2() {

    }
}