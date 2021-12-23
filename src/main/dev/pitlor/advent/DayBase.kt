package main.dev.pitlor.advent

import java.io.File

open class DayBase(private val day: Int) {
    val input get() = File("src/main/dev/pitlor/advent/day$day/input.txt").readLines()

    fun String.splitChars(): List<String> {
        return toCharArray().map(Char::toString)
    }

    fun <T> List<List<T>>.crossForEach(action: (T) -> Unit) {
        forEach { it.forEach(action) }
    }

    fun <T, U> List<List<T>>.crossMap(mapper: (T) -> U): List<List<U>> {
        return map { it.map { x -> mapper(x) } }
    }

    fun <T, U> List<List<T>>.crossMapIndexed(mapper: (T, Int, Int, List<List<T>>) -> U): List<List<U>> {
        return mapIndexed { i, x -> x.mapIndexed { j, y -> mapper(y, i, j, this) } }
    }

    fun <T, U> List<List<T>>.crossAssociateIndexed(mapper: (T, Int, Int) -> Pair<T, U>): Map<T, U> {
        return mapOf(*flatMapIndexed { x, l -> l.mapIndexed { y, item -> mapper(item, x, y) } }.toTypedArray())
    }

    fun <T> List<List<T>>.crossAll(predicate: (T) -> Boolean): Boolean {
        return all { it.all(predicate) }
    }

    fun <T> List<List<T>>.crossCount(predicate: (T) -> Boolean): Int {
        return sumOf { it.count(predicate) }
    }

    fun <T, U> MutableList<T>.mutableMap(mapper: (T) -> U): MutableList<U> {
        return map(mapper).toMutableList()
    }

    fun <T, U> Iterable<T>.mutableFlatMap(mapper: (T) -> List<U>): MutableList<U> {
        return flatMap(mapper).toMutableList()
    }

    fun <T> MutableList<T>.mutPlus(item: T): MutableList<T> {
        add(item)
        return this
    }

    operator fun <T> List<T>.get(i: Int, default: T): T {
        return getOrElse(i) { default }
    }
}