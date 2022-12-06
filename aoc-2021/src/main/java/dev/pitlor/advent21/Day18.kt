package dev.pitlor.advent21

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.sun.source.tree.Tree
import org.junit.Test
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.test.assertEquals

class TreeNode(
    var value: Int? = null,
    var leftChild: TreeNode? = null,
    var rightChild: TreeNode? = null,
    var parent: TreeNode? = null
) {
    override fun toString(): String {
        if (value != null) return "$value"
        return "[$leftChild,$rightChild]"
    }

    private fun tryExplode(level: Int = 1): Boolean {
        val left = leftChild
        val right = rightChild
        if (level > 4 && left?.value != null && right?.value != null) {
            var node = this
            var p = parent
            while (node == p?.leftChild) {
                node = p
                p = p.parent
            }
            p = p?.leftChild
            if (p != null) {
                node = p
                while (node.rightChild != null) node = node.rightChild!!
                node.value = node.value!! + left.value!!
            }

            node = this
            p = parent
            while (node == p?.rightChild) {
                node = p
                p = p.parent
            }
            p = p?.rightChild
            if (p != null) {
                node = p
                while (node.leftChild != null) node = node.leftChild!!
                node.value = node.value!! + right.value!!
            }

            value = 0
            leftChild = null
            rightChild = null
            return true
        }

        if (left != null && left.tryExplode(level + 1)) return true
        if (right != null && right.tryExplode(level + 1)) return true
        return false
    }

    private fun trySplit(): Boolean {
        val v = value
        if (v != null && v >= 10) {
            leftChild = TreeNode(value = floor(v.toDouble() / 2.0).toInt(), parent = this)
            rightChild = TreeNode(value = ceil(v.toDouble() / 2.0).toInt(), parent = this)
            value = null
            return true
        }

        val l = leftChild
        if (l != null && l.trySplit()) {
            return true
        }

        val r = rightChild
        if (r != null && r.trySplit()) {
            return true
        }

        return false
    }

    private fun reduce(): TreeNode {
        while (true) {
            if (tryExplode()) continue
            if (trySplit())  continue
            break
        }

        return this
    }

    operator fun plus(other: TreeNode): TreeNode {
        val node = TreeNode(null, this, other)
        parent = node
        other.parent = node

        return node.reduce()
    }

    fun magnitude(): ULong {
        val v = value
        if (v != null) return v.toULong()

        val l = leftChild
        val r = rightChild
        if (l == null || r == null) throw Exception()
        return 3UL * l.magnitude() + 2UL * r.magnitude()
    }

    companion object {
        fun fromString(string: String): TreeNode {
            fun parsePair(pair: JsonArray, parent: TreeNode? = null): TreeNode {
                val node = TreeNode(parent = parent)
                node.leftChild =
                    if (pair[0].isJsonPrimitive) TreeNode(value = pair[0].asInt, parent = node)
                    else parsePair(pair[0].asJsonArray, node)
                node.rightChild =
                    if (pair[1].isJsonPrimitive) TreeNode(value = pair[1].asInt, parent = node)
                    else parsePair(pair[1].asJsonArray, node)

                return node
            }

            return parsePair(JsonParser.parseString(string).asJsonArray)
        }
    }
}

class Day18 : DayBase(18) {
    @Test
    fun part1() {
        val answer = 4480UL
        val magnitude = input
            .map(TreeNode::fromString)
            .reduce { acc, treeNode -> acc + treeNode }
            .magnitude()

        assertEquals(answer, magnitude)
    }

    @Test
    fun part2() {
        val answer = 4676UL
        val largestMagnitude = (1 until input.size)
            .flatMap { offset ->
                input.mapIndexed { i, s ->
                    Pair(TreeNode.fromString(s), TreeNode.fromString(input[(i + offset) % input.size]))
                }
            }
            .maxOf { (it.first + it.second).magnitude() }

        assertEquals(answer, largestMagnitude)
    }
}
