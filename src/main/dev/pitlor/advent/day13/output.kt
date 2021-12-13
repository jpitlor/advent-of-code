package main.dev.pitlor.advent.day13

import main.dev.pitlor.advent.DayBase
import main.dev.pitlor.advent.crossCount
import org.junit.Test

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
        var map = MutableList(coordinates.maxOf { it[0] }) { x ->
            MutableList(coordinates.maxOf { it[1] }) { y ->
                coordinates.contains(listOf(x, y))
            }
        }

        val (direction, value) = folds.first()
        val location = value.toInt()
        when (direction) {
            "x" -> {
                for (x in 0..location) {
                    for (y in map[x].indices) {
                        map[x][y] = map[x][y] or map[location + x][y]
                    }
                }
                map = map.subList(0, location).toMutableList()
            }
            "y" -> {
                for (y in 0..location) {
                    for (x in map.indices) {
                        map[x][y] = map[x][y] or map[x][location + y]
                    }
                }
                map = map.map { it.subList(0, location) }.toMutableList()
            }
            else -> throw Error()
        }

        println(map.crossCount { it })
    }

    @Test
    fun part2() {

    }
}