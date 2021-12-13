package main.dev.pitlor.advent

fun String.splitChars(): List<String> {
    return toCharArray().map(Char::toString)
}

fun <T> List<List<T>>.crossForEach(action: (T) -> Unit) {
    forEach { it.forEach(action) }
}

fun <T, U> List<List<T>>.crossMap(mapper: (T) -> U): List<List<U>> {
    return map { it.map { x -> mapper(x) }}
}

fun <T, U> List<List<T>>.crossMapIndexed(mapper: (T, Int, Int, List<List<T>>) -> U): List<List<U>> {
    return mapIndexed { i, x -> x.mapIndexed { j, y -> mapper(y, i, j, this) }}
}

fun <T> List<List<T>>.crossAll(predicate: (T) -> Boolean): Boolean {
    return all { it.all(predicate) }
}

fun <T> List<List<T>>.crossCount(predicate: (T) -> Boolean): Int {
    return sumOf { it.count(predicate) }
}