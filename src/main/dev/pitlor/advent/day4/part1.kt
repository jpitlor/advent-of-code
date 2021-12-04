package main.dev.pitlor.advent.day4

import main.dev.pitlor.advent.Utils
import kotlin.test.assertEquals

private val answer = 38913

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

    val boards = mutableListOf<Board>()
    for (i in 2..input.size - 5 step 6) {
        boards += Board(input.subList(i, i + 5).map { it.trim().split(Regex("[ ]+")).map(String::toInt) })
    }

    var winningBoard: Board? = null
    var winningNumber = 0
    for (number in calledNumbers) {
        boards.forEach { it.call(number) }

        winningNumber = number
        winningBoard = boards.firstOrNull { it.hasBingo() }

        if (winningBoard != null) break
    }

    val uncalledNumberSum = winningBoard!!.uncalledNumbers().sum()
    assertEquals(answer, uncalledNumberSum * winningNumber)
}