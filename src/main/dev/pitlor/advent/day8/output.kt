package main.dev.pitlor.advent.day8

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import kotlin.test.assertEquals

class Day8 : DayBase(8) {
    private val displays = input.map { it.split(" | ").map { x -> x.split(" ") } }

    @Test
    fun part1() {
        val answer = 318
        val occurrencesOf1478 = displays.sumOf { it[1].count { l -> l.length != 5 && l.length != 6 } }
        assertEquals(answer, occurrencesOf1478)
    }

    @Test
    fun part2() {
        val answer = 996280
        val sum = displays.sumOf { (wires, digits) ->
            val patterns = wires.associate {
                it.toCharArray().sorted().joinToString("") to when(it.length) {
                    2 -> 1
                    3 -> 7
                    4 -> 4
                    5 -> { // 2 3 5
                        if (wires.find { x -> x.length == 2 }!!.all { c -> it.contains(c) }) 3
                        else if (wires.find { x -> x.length == 4 }!!.count { c -> it.contains(c) } == 2) 2
                        else 5
                    }
                    6 -> {
                        if (!wires.find { x -> x.length == 2 }!!.all { c -> it.contains(c) })  6
                        else if (!wires.find { x -> x.length == 4 }!!.all { c -> it.contains(c) }) 0
                        else 9
                    }
                    7 -> 8
                    else -> throw Error()
                }
            }
            digits
                .map { patterns[it.toCharArray().sorted().joinToString("")] }
                .joinToString("")
                .toInt()
        }
        assertEquals(answer, sum)
    }
}