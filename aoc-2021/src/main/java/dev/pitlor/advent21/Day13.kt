package dev.pitlor.advent21

import org.junit.Test
import kotlin.test.assertEquals

class Day13 : DayBase(13) {
    private val emptyLine = input.indexOf("")
    private val coordinates = input
        .subList(0, emptyLine)
        .map { it.split(",").map { c -> c.toInt() } /* .reversed() ??? */ }
    private val folds = input
        .subList(emptyLine + 1, input.size)
        .map { it.substring("fold along ".length).split("=") }

    @Test
    fun part1() {
        val answer = 847

        var map = MutableList(coordinates.maxOf { it[0] } + 1) { x ->
            MutableList(coordinates.maxOf { it[1] } + 1) { y ->
                coordinates.contains(listOf(x, y))
            }
        }

        val (direction, value) = folds.first()
        val location = value.toInt()
        when (direction) {
            "x" -> {
                for (x in 0..location) {
                    for (y in map[x].indices) {
                        map[x][y] = map[x][y] or map[location + (location - x), listOf()][y, false]
                    }
                }
                map = map.subList(0, location).toMutableList()
            }
            "y" -> {
                for (y in 0..location) {
                    for (x in map.indices) {
                        map[x][y] = map[x][y] or map[x][location + (location - y), false]
                    }
                }
                map = map.map { it.subList(0, location) }.toMutableList()
            }
            else -> throw Error()
        }

        assertEquals(answer, map.crossCount { it })
    }

    @Test
    fun part2() {
        var map = MutableList(coordinates.maxOf { it[0] } + 1) { x ->
            MutableList(coordinates.maxOf { it[1] } + 1) { y ->
                coordinates.contains(listOf(x, y))
            }
        }

        for ((direction, value) in folds) {
            val location = value.toInt()
            when (direction) {
                "x" -> {
                    for (x in 0..location) {
                        for (y in map[x].indices) {
                            map[x][y] = map[x][y] or map[location + (location - x), listOf()][y, false]
                        }
                    }
                    map = map.subList(0, location).toMutableList()
                }
                "y" -> {
                    for (y in 0..location) {
                        for (x in map.indices) {
                            map[x][y] = map[x][y] or map[x][location + (location - y), false]
                        }
                    }
                    map = map.map { it.subList(0, location) }.toMutableList()
                }
                else -> throw Error()
            }
        }

        for (x in map[0].indices) {
            for (y in map.indices) {
                print(if (map[y][x]) "#" else " ")
            }
            println()
        }
        // ANSWER: BCZRCEAB
        // TODO: Find a way to assert this
        TODO()
    }
}
