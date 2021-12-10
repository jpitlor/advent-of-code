package main.dev.pitlor.advent.day10

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

enum class Token(val points: Int = 0, val opener: Token? = null) {
    LP(1), RP(3, LP),
    LB(2), RB(57, LB),
    LCB(3), RCB(1197, LCB),
    LAB(4), RAB(25137, LAB);

    companion object {
        fun parse(char: Char): Token {
            return when (char) {
                '<' -> LAB
                '>' -> RAB
                '{' -> LCB
                '}' -> RCB
                '[' -> LB
                ']' -> RB
                '(' -> LP
                ')' -> RP
                else -> throw Error()
            }
        }

        fun closer(token: Token): Token {
            return when (token) {
                LP -> RP
                LB -> RB
                LCB -> RCB
                LAB -> RAB
                else -> throw Error()
            }
        }
    }
}

class Day10 : DayBase(10) {
    @Test
    fun part1() {
        val answer = 442131
        fun score(line: String): Int {
            val chars = line.toCharArray().map { Token.parse(it) }.toMutableList()
            val stack = Stack<Token>()
            while (chars.isNotEmpty()) {
                val t = chars.removeAt(0)
                if (t.opener != null && stack.pop() != t.opener) {
                    return t.points
                } else if (t.opener == null) {
                    stack.push(t)
                }
            }
            return 0
        }

        val score = input.sumOf { score(it) }
        assertEquals(answer, score)
    }

    @Test
    fun part2() {
        val answer = 3646451424UL

        fun score(line: String): ULong {
            val chars = line.toCharArray().map { Token.parse(it) }.toMutableList()
            val stack = Stack<Token>()
            while (chars.isNotEmpty()) {
                val t = chars.removeAt(0)
                if (t.opener != null && stack.pop() != t.opener) {
                    return 0UL
                } else if (t.opener == null) {
                    stack.push(t)
                }
            }

            if (stack.empty()) return 0UL
            return stack.foldRight(0UL) { t, acc -> acc * 5UL + t.points.toULong() }
        }

        fun List<ULong>.median(): ULong {
            return sorted()[size / 2]
        }

        val score = input.map { score(it) }.filter { it != 0UL }.median()
        assertEquals(answer, score)
    }
}