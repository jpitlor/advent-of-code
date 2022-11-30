package dev.pitlor.advent21

import org.junit.Test
import java.lang.Integer.min
import kotlin.collections.set
import kotlin.test.assertEquals

typealias Coord = Pair<Int, Int>
class Day15 : DayBase(15) {
    private val map = input.map { it.splitChars().map(String::toInt) }

    private fun <T> List<List<T>>.neighbours(x: Int, y: Int): List<Coord> {
        return listOfNotNull(
            if (x > 0) Pair(x - 1, y) else null,
            if (y > 0) Pair(x, y - 1) else null,
            if (x < size - 1) Pair(x + 1, y) else null,
            if (y < this[0].size - 1) Pair(x, y + 1) else null
        )
    }

    @Test
    fun part1() {
        val answer = 621

        val shortestPaths = mutableMapOf<Coord, Int>()
        val knownPaths =  mutableMapOf(*map.crossMapIndexed { _, x, y, _ -> Pair(x, y) to Int.MAX_VALUE }.flatten().toTypedArray(), Pair(0, 0) to 0)
        while (shortestPaths.keys.size < knownPaths.keys.size) {
            val nextPoint = knownPaths.minus(shortestPaths.keys).minByOrNull { it.value }!!
            shortestPaths[nextPoint.key] = nextPoint.value
            map.neighbours(nextPoint.key.first, nextPoint.key.second).forEach {
                knownPaths[it] = min(knownPaths[it]!!, nextPoint.value + map[it.first][it.second])
            }
        }

        val distanceToEnd = shortestPaths[Pair(map.size - 1, map[0].size - 1)]!!
        assertEquals(answer, distanceToEnd)
    }

    @Test
    fun part2() {
        val bigMap = List(map.size * 5) { x ->
            List(map.size * 5) { y ->
                val i = map[x % map.size][y % map.size] + (x / map.size + y / map.size)
                if (i > 9) i - 9 else i
            }
        }
        val end = Pair(bigMap.size - 1, bigMap.size - 1)

//        fun search(currentPath: List<Coord>, currentRisk: Int, maxEstimatedRisk: Int): Pair<List<Coord>, Int> {
//            val currentNode = currentPath.last()
//            if (currentNode == end) {
//                return Pair(currentPath, currentRisk)
//            }
//
//            val underestimatedRisk = currentRisk + (end.first - currentNode.first) + (end.second - currentNode.second)
//            if (underestimatedRisk > maxEstimatedRisk) {
//                return Pair(currentPath, underestimatedRisk)
//            }
//
//            var resultWithMinNextRisk = Pair(currentPath, currentRisk)
//            bigMap.neighbours(currentNode.first, currentNode.second).forEach {
////                println("Testing $it")
//                val result = search(
//                    listOf(*currentPath.toTypedArray(), it),
//                    currentRisk + bigMap[it.first][it.second],
//                    maxEstimatedRisk
//                )
//
//                if (result.first.last() == end) {
//                    return result
//                } else if (result.second < resultWithMinNextRisk.second) {
//                    resultWithMinNextRisk = result
//                }
//            }
//            return resultWithMinNextRisk
//        }
//
//        var path = listOf(Pair(0, 0))
//        var maxRisk = 0
//        while (true) {
//            val (newPath, newRisk) = search(path, 0, maxRisk)
//            if (newPath.last() == end) {
//                break
//            }
//
//            path = newPath
//            maxRisk = newRisk
//            println("$newPath ($newRisk)")
//        }

//        println(maxRisk)
        val result = iterativeDeepeningAStar(bigMap, { n -> 4 * ((end.first - n.first) + (end.second - n.second)) }, Pair(0, 0), end)
    }


    /**
     * Performs iterative deepening A Star (A*).
     * Can be modified to handle graphs by keeping track of already visited nodes.
     *
     * @param tree      An adjacency-matrix-representation of the tree where (x,y) is the weight of the edge or 0 if there is no edge.
     * @param heuristic An estimation of distance from node x to y that is guaranteed to be lower than the actual distance. E.g. straight-line distance.
     * @param start      The node to start from.
     * @param goal      The node we're searching for.
     * @return The shortest distance to the goal node. Can be easily modified to return the path.
     */
    fun iterativeDeepeningAStar(tree: List<List<Int>>, heuristic: (Coord) -> Int, start: Coord, goal: Coord): Int {
        var threshold = heuristic(start)
        while (true) {
            println("Iteration with threshold: $threshold")
            val distance = iterativeDeepeningAStar(tree, heuristic, start, goal, 0, threshold)
            threshold = if (distance == Int.MAX_VALUE) {
                // Node not found and no more nodes to visit
                return -1
            } else if (distance < 0) {
                // if we found the node, the function returns the negative distance
                println("Found the node we're looking for!")
                return -distance
            } else {
                // if it hasn't found the node, it returns the (positive) next-bigger threshold
                distance
            }
        }
    }

    /**
     * Performs DFS up to a depth where a threshold is reached (as opposed to interative-deepening DFS which stops at a fixed depth).
     * Can be modified to handle graphs by keeping track of already visited nodes.
     *
     * @param tree      An adjacency-matrix-representation of the tree where (x,y) is the weight of the edge or 0 if there is no edge.
     * @param heuristic An estimation of distance from node x to y that is guaranteed to be lower than the actual distance. E.g. straight-line distance.
     * @param node      The node to continue from.
     * @param goal      The node we're searching for.
     * @param distance  Distance from start node to current node.
     * @param threshold Until which distance to search in this iteration.
     * @return The shortest distance to the goal node. Can be easily modified to return the path.
     */
    private fun iterativeDeepeningAStar(
        tree: List<List<Int>>,
        heuristic: (Coord) -> Int,
        node: Coord,
        goal: Coord,
        distance: Int,
        threshold: Int
    ): Int {
        println("Visiting Node $node")
        if (node == goal) {
            // We have found the goal node we we're searching for
            return -distance
        }
        val estimate = distance + heuristic(node)
        if (estimate > threshold) {
            println("Breached threshold with heuristic: $estimate")
            return estimate
        }

        //...then, for all neighboring nodes....
        var min = Int.MAX_VALUE
        for (nextNode in tree.neighbours(node.first, node.second)) {
            val t = iterativeDeepeningAStar(tree, heuristic, nextNode, goal, distance + tree[nextNode.first][nextNode.second], threshold)
            if (t < 0) {
                // Node found
                return t
            } else if (t < min) {
                min = t
            }
        }
        return min
    }
}
