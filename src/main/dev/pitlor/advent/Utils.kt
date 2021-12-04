package main.dev.pitlor.advent

import java.io.File

object Utils {
    fun getInput(day: Int): File {
        return File("src/main/dev/pitlor/advent/day$day/input.txt")
    }
}