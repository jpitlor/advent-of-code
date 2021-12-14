package main.dev.pitlor.advent.day14

import main.dev.pitlor.advent.DayBase
import main.dev.pitlor.advent.splitChars
import org.junit.Test
import kotlin.test.assertEquals

class Day14 : DayBase(14) {
    private val template = input.first()
    private val insertionRules = input
        .subList(2, input.size)
        .map { it.split(" -> ") }
        .associate { it[0] to it[1] }

    @Test
    fun part1() {
        val answer = 3906

        val result = template.splitChars().toMutableList()
        var pointer = 0
        for (i in 1..10) {
            while (pointer < result.size - 1) {
                result.add(pointer + 1, insertionRules[result[pointer] + result[pointer + 1]]!!)
                pointer += 2
            }
            pointer = 0
        }

        result
            .distinct()
            .associateWith { result.count { v -> v == it } }
            .also {
                val mode = it.maxOf { e -> e.value }
                val antimode = it.minOf { e -> e.value }
                assertEquals(answer, mode - antimode)
            }
    }

    @Test
    fun part2() {
        val answer = 4441317262452UL

        val hashedInsertionRules = insertionRules.entries.associate {
            val first = it.key[0]
            val second = it.key[1]
            val inserted = insertionRules[it.key]
            it.key to listOf("$first$inserted", "$inserted$second")
        }

        var combos = template
            .substring(0, template.length - 1)
            .splitChars()
            .mapIndexed { i, s -> s + template[i + 1] }
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toULong() }

        for (i in 1..40) {
            combos = combos.entries.fold(mutableMapOf()) { acc, e ->
                hashedInsertionRules[e.key]!!.forEach {
                    acc[it] = acc.getOrDefault(it, 0UL) + e.value
                }
                acc
            }
        }

        combos.entries
            .groupBy { it.key[0] }
            .mapValues { it.value.sumOf { x -> x.value } }
            .toMutableMap()
            .apply { this[template.last()] = this[template.last()]!! + 1UL }
            .let {
                val mode = it.maxOf { e -> e.value }
                val antimode = it.minOf { e -> e.value }
                assertEquals(answer, mode - antimode)
            }
    }
}