package dev.pitlor.advent21

import java.io.File

open class DayBase(private val day: Int) {
    val input get() = File("src/main/resources/day$day.txt").readLines()

    fun String.splitChars(): List<String> {
        return toCharArray().map(Char::toString)
    }

    fun <T> List<List<T>>.crossForEach(action: (T) -> Unit) {
        forEach { it.forEach(action) }
    }

    fun <T, U> List<List<T>>.crossMapIndexed(mapper: (T, Int, Int, List<List<T>>) -> U): List<List<U>> {
        return mapIndexed { i, x -> x.mapIndexed { j, y -> mapper(y, i, j, this) } }
    }

    fun <T> List<List<T>>.crossAll(predicate: (T) -> Boolean): Boolean {
        return all { it.all(predicate) }
    }

    fun <T> List<List<T>>.crossCount(predicate: (T) -> Boolean): Int {
        return sumOf { it.count(predicate) }
    }

    fun <T, U> Iterable<T>.mutableFlatMap(mapper: (T) -> List<U>): MutableList<U> {
        return flatMap(mapper).toMutableList()
    }

    operator fun <T> List<T>.get(i: Int, default: T): T {
        return getOrElse(i) { default }
    }
}
