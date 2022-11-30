package dev.pitlor.advent21

import org.junit.Test
import java.lang.Exception
import java.util.*
import kotlin.test.assertEquals

class Day12 : DayBase(12) {
    private val START = "start"
    private val END = "end"

    @Test
    fun part1() {
        val answer = 4749

        val map = mutableMapOf<String, MutableList<String>>()
        input.forEach {
            val (n1, n2) = it.split("-")
            map[n1]?.add(n2) ?: map.put(n1, mutableListOf(n2))
            map[n2]?.add(n1) ?: map.put(n2, mutableListOf(n1))
        }

        val routes = mutableListOf(listOf(START))
        val toRemove = mutableListOf<List<String>>()
        val toAdd = mutableListOf<List<String>>()
        while (routes.any { it.last() != END }) {
            routes.forEach { route ->
                if (route.last() == END) {
                    // We reached the end! This is a valid route
                    return@forEach
                }

                val neighbors = (map[route.last()] ?: throw Exception("This shouldn't have happened"))
                    .filter { it[0].isUpperCase() || !route.contains(it) }
                if (neighbors.isEmpty()) {
                    // We reached a dead end
                    toRemove.add(route)
                    return@forEach
                }

                toRemove.add(route)
                toAdd.addAll(neighbors.map { listOf(*route.toTypedArray(), it) })
            }

            routes.removeAll(toRemove)
            toRemove.clear()
            routes.addAll(toAdd)
            toAdd.clear()
        }

        assertEquals(answer, routes.size)
    }

    @Test
    fun part2() {
        val answer = 123054

        val map = mutableMapOf<String, MutableList<String>>()
        input.forEach {
            val (n1, n2) = it.split("-")
            map[n1]?.add(n2) ?: map.put(n1, mutableListOf(n2))
            map[n2]?.add(n1) ?: map.put(n2, mutableListOf(n1))
        }

        val routes = mutableListOf(listOf(START))
        val toRemove = mutableListOf<List<String>>()
        val toAdd = mutableListOf<List<String>>()
        while (routes.any { it.last() != END }) {
            routes.forEach { route ->
                if (route.last() == END) {
                    // We reached the end! This is a valid route
                    return@forEach
                }

                val hasDoubleVisitedASmallCave = route.any { s -> s[0].isLowerCase() && route.count { it == s } == 2 }
                val neighbors = (map[route.last()] ?: throw Exception("This shouldn't have happened"))
                    .filter { n -> n != START && (n[0].isUpperCase() || !hasDoubleVisitedASmallCave || !route.contains(n)) }
                if (neighbors.isEmpty()) {
                    // We reached a dead end
                    toRemove.add(route)
                    return@forEach
                }

                toRemove.add(route)
                toAdd.addAll(neighbors.map { listOf(*route.toTypedArray(), it) })
            }

            routes.removeAll(toRemove)
            toRemove.clear()
            routes.addAll(toAdd)
            toAdd.clear()
        }

        assertEquals(answer, routes.size)
    }
}
