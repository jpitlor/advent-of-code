package dev.pitlor.advent21

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
        val answer = 1135260

        fun List<List<Int?>>.basins() = object {
            operator fun iterator(): Iterator<Pair<Int, Int>> = object : Iterator<Pair<Int, Int>> {
                var x = 0
                var y = 0

                fun isAtBasin(_x: Int = x, _y: Int = y): Boolean {
                    return this@basins[_x][_y] != null && this@basins[_x][_y]!! < 9
                }

                fun findNextBasin() {
                    if (isAtBasin()) {
                        return
                    }

                    for (x in indices) {
                        for (y in this@basins[x].indices) {
                            if (isAtBasin(x, y)) {
                                this.x = x
                                this.y = y
                                return
                            }
                        }
                    }
                }

                override fun hasNext(): Boolean {
                    findNextBasin()
                    return isAtBasin()
                }

                override fun next(): Pair<Int, Int> {
                    findNextBasin()
                    return Pair(x, y)
                }
            }
        }

        fun List<List<Int?>>.neighbours(x: Int, y: Int): List<Pair<Int, Int>> {
            return listOfNotNull(
                if (x > 0 && this[x - 1][y] != null && this[x - 1][y]!! < 9) Pair(x - 1, y) else null,
                if (y > 0 && this[x][y - 1] != null && this[x][y - 1]!! < 9) Pair(x, y - 1) else null,
                if (x < size - 1 && this[x + 1][y] != null && this[x + 1][y]!! < 9) Pair(x + 1, y) else null,
                if (y < this[0].size - 1 && this[x][y + 1] != null && this[x][y + 1]!! < 9) Pair(x, y + 1) else null
            )
        }

        val basins = mutableListOf<Int>()
        val map = input.map { it.toCharArray().map(Char::digitToIntOrNull).toMutableList() }.toMutableList()

        for (basin in map.basins()) {
            var size = 0
            val points = mutableListOf(basin)
            while (points.isNotEmpty()) {
                val point = points.removeAt(0)
                if (map[point.first][point.second] == null) continue

                map[point.first][point.second] = null
                size++
                points.addAll(map.neighbours(point.first, point.second))
            }

            basins += size
        }

        val result = basins
            .sortedDescending()
            .subList(0, 3)
            .reduce { acc, i -> acc * i }
        assertEquals(answer, result)
    }
}
