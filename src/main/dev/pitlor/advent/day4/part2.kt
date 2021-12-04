package main.dev.pitlor.advent.day4

import main.dev.pitlor.advent.Utils
import kotlin.test.assertEquals

private val answer = 16836

fun main() {
    class Board(val rows: List<List<Int>>) {
        val columns = IntRange(0, 4).map { i -> rows.map { it[i] } }
        val marked = mutableListOf<Int>()

        fun hasBingo(): Boolean {
            return rows.any { it.all { i -> marked.contains(i) } }
                    || columns.any { it.all { i -> marked.contains(i) } }
        }

        fun call(number: Int) {
            marked += number
        }

        fun uncalledNumbers(): List<Int> {
            return rows.flatten().filter { !marked.contains(it) }
        }
    }

    val input = Utils.getInput(4).readLines()

    val calledNumbers = input.first().split(",").map(String::toInt)

    var boards = mutableListOf<Board>()
    for (i in 2..input.size - 5 step 6) {
        boards += Board(input.subList(i, i + 5).map { it.trim().split(Regex("[ ]+")).map(String::toInt) })
    }

    var winningNumber = 0
    for (number in calledNumbers) {
        boards.forEach { it.call(number) }

        winningNumber = number

        if (boards.size > 1) {
            boards = boards.filter { !it.hasBingo() }.toMutableList()
        } else if (boards.first().hasBingo()) {
            break
        }
    }

    val uncalledNumberSum = boards.first().uncalledNumbers().sum()
    assertEquals(answer, uncalledNumberSum * winningNumber)
}