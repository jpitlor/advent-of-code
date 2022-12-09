package dev.pitlor.advent22

import org.junit.Test
import kotlin.math.max
import kotlin.test.assertEquals

class Day8 : DayBase(8) {
    private val heights = input.map { it.toList().map(Char::digitToInt) }

    private fun <T> List<T>.sumIndexed(predicate: (Int, T) -> Int) = mapIndexed(predicate).sum()
    private fun <T> List<T>.countIndexed(predicate: (Int, T) -> Boolean) = filterIndexed(predicate).size
    private fun <T> List<T>.allIndexed(predicate: (Int, T) -> Boolean) = mapIndexed(predicate).all { it }

    @Test
    fun part1() {
        val answer = 1662
        val treesVisible = heights.sumIndexed { i, row ->
            row.countIndexed { j, height ->
                val column = heights.map { it[j] }
                i == 0
                    || i == heights.size - 1
                    || j == 0
                    || j == row.size - 1
                    || column.allIndexed { i2, height2 -> i2 <= i || height2 < height }
                    || column.allIndexed { i2, height2 -> i2 >= i || height2 < height }
                    || row.allIndexed { j2, height2 -> j2 <= j || height2 < height }
                    || row.allIndexed { j2, height2 -> j2 >= j || height2 < height }
            }
        }

        assertEquals(answer, treesVisible)
    }

    @Test
    fun part2() {
        val answer = 537600

        fun List<Int>.adjust(): List<Int> {
            return when (size) {
                0 -> listOf(Int.MAX_VALUE)
                1 -> listOf(Int.MIN_VALUE, Int.MAX_VALUE)
                else -> listOf(Int.MIN_VALUE, *subList(0, size - 1).toTypedArray(), Int.MAX_VALUE)
            }
        }

        val maxScenicScore = heights
            .flatMapIndexed { i, row ->
                row.mapIndexed { j, height ->
                    val column = heights.map { it[j] }
                    val up = column
                        .subList(0, i)
                        .reversed()
                        .adjust()
                        .indexOfFirst { it >= height }
                    val down = column
                        .subList(i + 1, column.size)
                        .adjust()
                        .indexOfFirst { it >= height }
                    val left = row
                        .subList(0, j)
                        .reversed()
                        .adjust()
                        .indexOfFirst { it >= height }
                    val right = row
                        .subList(j + 1, row.size)
                        .adjust()
                        .indexOfFirst { it >= height }

                    up * down * left * right
                }
            }
            .max()

        assertEquals(answer, maxScenicScore)
    }
}
