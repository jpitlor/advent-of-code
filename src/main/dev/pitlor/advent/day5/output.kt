package main.dev.pitlor.advent.day5

import main.dev.pitlor.advent.DayBase
import org.junit.Test
import java.awt.geom.Line2D
import java.awt.geom.Point2D
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs
import kotlin.math.sign
import kotlin.test.assertEquals

fun Line2D.Float.points(): List<Point2D.Float> {
    return if (x1 == x2) {
        IntRange(min(y1, y2).toInt(), max(y1, y2).toInt()).map { Point2D.Float(x1, it.toFloat()) }
    } else if (y1 == y2) {
        IntRange(min(x1, x2).toInt(), max(x1, x2).toInt()).map { Point2D.Float(it.toFloat(), y1) }
    } else {
        val dX = sign(x2 - x1)
        val dY = sign(y2 - y1)
        IntRange(0, abs(x2 - x1).toInt()).map { Point2D.Float(x1 + dX * it, y1 + dY * it) }
    }
}

fun <T> HashMap<T, Int>.increment(k: T) {
    set(k, getOrDefault(k, 0) + 1)
}

class Day5 : DayBase(5) {
    private val lines = input.map {
        val (x1, y1, x2, y2) = it.split(" -> ", ",")
        Line2D.Float(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat())
    }

    @Test
    fun part1() {
        val answer = 7436

        val map = HashMap<Point2D.Float, Int>()
        val straightLines = lines.filter { it.x1 == it.x2 || it.y1 == it.y2 }
        straightLines.forEach { it.points().forEach(map::increment) }

        assertEquals(answer, map.filter { it.value > 1 }.keys.size)
    }

    @Test
    fun part2() {
        val answer = 21104

        val map = HashMap<Point2D.Float, Int>()
        lines.forEach { it.points().forEach(map::increment) }

        assertEquals(answer, map.filter { it.value > 1 }.keys.size)
    }
}