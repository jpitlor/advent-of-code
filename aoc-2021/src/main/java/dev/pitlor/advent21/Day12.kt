package dev.pitlor.advent21

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

        val routes = mutableMapOf(mutableListOf(START) to adjacents[START]!!)
//        while (routes.any { it.value.isNotEmpty() }) {
//            val current = routes.entries.first { it.value.isNotEmpty() }
//            routes.putAll(current.value.map { it.associate { v ->
//                val k = current.key
//                k.add()
//            } })
//        }
    }

    @Test
    fun part2() {

    }
}
