package dev.pitlor.advent21

import org.junit.Test

sealed class SnailfishNumber {
    abstract fun magnitude(): Int
}
data class Number(val value: Int) : SnailfishNumber() {
    override fun magnitude(): Int = value
}

data class SFPair(val left: SnailfishNumber, val right: SnailfishNumber) : SnailfishNumber() {
    private fun reduce(): SFPair {
        return this
    }

    operator fun plus(other: SFPair): SFPair {
        return SFPair(this, other).reduce()
    }

    override fun magnitude(): Int {
        TODO("Not yet implemented")
    }
}

class Day18 : DayBase(18) {
    @Test
    fun part1() {

    }
}
