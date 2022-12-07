package dev.pitlor.advent22

import org.junit.Test
import kotlin.test.assertEquals

class Day7 : DayBase(7) {
    private val cdRegex = Regex("\\$ cd (.*)")
    private val fileRegex = Regex("(\\d+) (.*)")
    private val folderRegex = Regex("dir (.*)")

    sealed class Node(val name: String, val parent: Folder?) {
        abstract fun size(): Int
        abstract fun folders(): List<Node>
    }
    class Folder(val children: MutableList<Node>, name: String, parent: Folder?) : Node(name, parent) {
        override fun size(): Int = children.sumOf(Node::size)
        override fun folders(): List<Node> = listOf(this, *children.flatMap(Node::folders).toTypedArray())
    }

    class File(val size: Int, name: String, parent: Folder?): Node(name, parent) {
        override fun size(): Int = size
        override fun folders(): List<Node> = listOf()
    }

    private val fileTree get(): Folder {
        val result = Folder(mutableListOf(), "", null)
        var currentFolder = result
        input.forEach {
            if (it == "$ ls") {
                // Do nothing
            } else if (it.matches(cdRegex)) {
                val (_, newFolder) = cdRegex.find(it)!!.groupValues
                currentFolder = when (newFolder) {
                    ".." -> currentFolder.parent!!
                    "/" -> result
                    else -> currentFolder.children.first { f -> f.name == newFolder } as Folder
                }
            }
            else if (it.matches(fileRegex)) {
                val (_, size, name) = fileRegex.find(it)!!.groupValues
                currentFolder.children.add(File(size = size.toInt(), name, currentFolder))
            }
            else if (it.matches(folderRegex)) {
                val (_, name) = folderRegex.find(it)!!.groupValues
                currentFolder.children.add(Folder(mutableListOf(), name, currentFolder))
            } else throw Exception()
        }
        return result
    }

    @Test
    fun part1() {
        val answer = 1118405
        val totalSizes = fileTree
            .folders()
            .map(Node::size)
            .filter { it <= 100_000 }
            .sum()

        assertEquals(answer, totalSizes)
    }

    @Test
    fun part2() {
        val answer = 12545514
        val spaceNeeded = fileTree.size() - 40_000_000
        val smallestFolderSize = fileTree
            .folders()
            .map(Node::size)
            .filter { it >= spaceNeeded }
            .min()

        assertEquals(answer, smallestFolderSize)
    }
}
