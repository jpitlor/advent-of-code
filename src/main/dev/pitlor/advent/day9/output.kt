package main.dev.pitlor.advent.day9

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.test.assertEquals

class Day9 : DayBase(9) {
    @Test
    fun part1() {
        val answer = 425

        fun <T> List<List<T>>.flatFilterCrossIndexed(predicate: (T, Int, Int, List<List<T>>) -> Boolean): List<T> {
            return flatMapIndexed { x, list -> list.filterIndexed { y, item -> predicate(item, x, y, this) } }
        }

        fun <T> List<List<T>>.neighbours(x: Int, y: Int): List<T> {
            return listOfNotNull(
                if (x > 0) this[x - 1][y] else null,
                if (y > 0) this[x][y - 1] else null,
                if (x < size - 1) this[x + 1][y] else null,
                if (y < this[0].size - 1) this[x][y + 1] else null
            )
        }

        val totalRisk = input
            .map { it.toCharArray().map(Char::digitToInt) }
            .flatFilterCrossIndexed { height, x, y, map -> map.neighbours(x, y).all { height < it } }
            .sumOf { it + 1 }
        assertEquals(answer, totalRisk)
    }

    @Test
    fun part2() {

    }
}