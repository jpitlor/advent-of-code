package dev.pitlor.advent22

import org.junit.Test
import java.util.Stack
import kotlin.test.assertEquals

class Day5 : DayBase(5) {
    private val partition = input.indexOf("")
    private val crates = input
        .subList(0, partition)
        .map(String::toList)
        .rotate()
        .filterIndexed { i, _ -> i % 4 == 1 }
        .map { a -> Stack<Char>().also { s -> s.addAll(a.filter { c -> c.isLetter() }) } }
    private val instructions = input
        .subList(partition + 1, input.size)
        .map(Regex("move (\\d+) from (\\d+) to (\\d+)")::find)

    @Test
    fun part1() {
        val answer = "FWNSHLDNZ"

        instructions.forEach {
            val groups = it?.groupValues ?: throw Exception()
            val (count, from, to) = groups.subList(1, groups.size).map(String::toInt)
            for (i in 1..count) {
                val c = crates[from - 1].pop()
                crates[to - 1].push(c)
            }
        }
        val topCrates = crates
            .map { it[it.size - 1] }
            .joinToString("")

        assertEquals(answer, topCrates)
    }

    @Test
    fun part2() {
        val answer = "RNRGDNFQG"

        val crateLists = crates.map { it.toMutableList() }.toMutableList()
        instructions.forEach {
            val groups = it?.groupValues ?: throw Exception()
            val (count, from, to) = groups.subList(1, groups.size).map(String::toInt)
            val popped = crateLists[from - 1].subList(crateLists[from - 1].size - count, crateLists[from - 1].size)
            crateLists[from - 1] = crateLists[from - 1].dropLast(count).toMutableList()
            crateLists[to - 1].addAll(popped)
        }
        val topCrates = crateLists
            .map { it[it.size - 1] }
            .joinToString("")

        assertEquals(answer, topCrates)
    }
}
