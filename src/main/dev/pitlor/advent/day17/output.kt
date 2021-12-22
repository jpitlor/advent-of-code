package main.dev.pitlor.advent.day17

import main.dev.pitlor.advent.DayBase
import org.junit.Test

class Day17 : DayBase(17) {
    @Test
    fun part1() {
        // target area: x=265..287, y=-103..-58
        val (minX, maxX, minY, maxY) = Regex("target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)")
            .find(input.first())!!
            .destructured
    }

    @Test
    fun part2() {

    }
}