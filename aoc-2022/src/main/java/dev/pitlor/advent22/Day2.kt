package dev.pitlor.advent22

import org.junit.Test
import java.lang.Exception
import kotlin.test.assertEquals

class Day2 : DayBase(2) {
    private val _rock = 0
    private val _paper = 1
    private val _scissors = 2
    private val _loss = 0
    private val _tie = 3
    private val _win = 6

    private fun scoreRoundForMove(moves: String): Int {
        val theirMove = when (moves[0]) {
            'A' -> _rock
            'B' -> _paper
            'C' -> _scissors
            else -> throw Exception("This shouldn't have happened")
        }
        val myMove = when (moves[2]) {
            'X' -> _rock
            'Y' -> _paper
            'Z' -> _scissors
            else -> throw Exception("This shouldn't have happened")
        }

        return when (myMove) {
            (theirMove + 1) % 3 -> myMove + _win + 1
            theirMove -> myMove + _tie + 1
            (theirMove + 2) % 3 -> myMove + _loss + 1
            else -> throw Exception("This shouldn't have happened")
        }
    }

    private fun scoreRoundForResult(round: String): Int {
        val theirMove = when (round[0]) {
            'A' -> _rock
            'B' -> _paper
            'C' -> _scissors
            else -> throw Exception("This shouldn't have happened")
        }
        val result = when (round[2]) {
            'X' -> _loss
            'Y' -> _tie
            'Z' -> _win
            else -> throw Exception("This shouldn't have happened")
        }

        return result + 1 + when (result) {
            _loss -> (theirMove + 2) % 3
            _tie -> theirMove
            _win -> (theirMove + 1) % 3
            else -> throw Exception("This shouldn't have happened")
        }
    }

    @Test
    fun part1() {
        val answer = 11873
        val points = input.sumOf(::scoreRoundForMove)

        assertEquals(answer, points)
    }

    @Test
    fun part2() {
        val answer = 12014
        val points = input.sumOf(::scoreRoundForResult)

        assertEquals(answer, points)
    }
}
