package main.dev.pitlor.advent.day3

import main.dev.pitlor.advent.Utils
import kotlin.math.ceil

private val answer = 5736383

fun main() {
    fun List<Char>.mean(): Char {
        val half = ceil(size / 2.0).toInt()
        return when (count { it == '1' }) {
            in half..size -> '1'
            else -> '0'
        }
    }

    fun List<Char>.antimean(): Char {
        val half = ceil(size / 2.0).toInt()
        return when (count { it == '1' }) {
            in half..size -> '0'
            else -> '1'
        }
    }

    val numbers = Utils.getInput(3).readLines()

    var o2Ratings = numbers
    for (i in 0..11) {
        val mean = o2Ratings.map { it[i] }.mean()
        o2Ratings = o2Ratings.filter { it[i] == mean }
        if (o2Ratings.size == 1) break
    }

    var co2Ratings = numbers
    for (i in 0..11) {
        val antimean = co2Ratings.map { it[i] }.antimean()
        co2Ratings = co2Ratings.filter { it[i] == antimean }
        if (co2Ratings.size == 1) break
    }

    val o2Rating = o2Ratings.first().toInt(2)
    val co2Rating = co2Ratings.first().toInt(2)
    println(o2Rating * co2Rating)
}