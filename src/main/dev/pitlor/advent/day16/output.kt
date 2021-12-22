package main.dev.pitlor.advent.day16

import main.dev.pitlor.advent.DayBase
import main.dev.pitlor.advent.mutableFlatMap
import main.dev.pitlor.advent.splitChars
import org.junit.Test
import kotlin.test.assertEquals

enum class PacketType {
    Literal, Sum, Product, Minimum, Maximum, GreaterThan, LessThan, EqualTo;

    companion object {
        fun parse(value: Int): PacketType {
            return when (value) {
                0 -> Sum
                1 -> Product
                2 -> Minimum
                3 -> Maximum
                4 -> Literal
                5 -> GreaterThan
                6 -> LessThan
                7 -> EqualTo
                else -> throw Error()
            }
        }
    }
}
sealed class Packet(val type: PacketType, val version: Int)
class LiteralPacket(version: Int, val value: ULong) : Packet(PacketType.Literal, version)
class OperatorPacket(version: Int, type: PacketType, val operands: List<Packet>) : Packet(type, version)

class Day16 : DayBase(16) {
    private var bitsIndex = 0
    private val bits = input[0].splitChars().mutableFlatMap { it.toInt(16).toString(2).padStart(4, '0').splitChars() }

    private fun shiftBits(count: Int): Int {
        val result = bits.subList(bitsIndex, bitsIndex + count)
        bitsIndex += count
        return result.joinToString("").toInt(2)
    }

    private fun readPacket(): Packet {
        val version = shiftBits(3)
        when (val type = shiftBits(3)) {
            4 -> {
                var value = ""
                while (shiftBits(1) == 1) value += shiftBits(4).toString(2)
                value += shiftBits(4).toString(2) // there's one last number
                return LiteralPacket(version, value.toULong(2))
            }
            else -> {
                val operands = mutableListOf<Packet>()
                val packetType = PacketType.parse(type)
                when (shiftBits(1)) {
                    0 -> {
                        val subpacketsLength = shiftBits(15)
                        val currentIndex = bitsIndex
                        while (bitsIndex < currentIndex + subpacketsLength) operands += readPacket()
                    }
                    1 -> {
                        val subpacketCount = shiftBits(11)
                        for (i in 1..subpacketCount) operands += readPacket()
                    }
                }
                return OperatorPacket(version, packetType, operands)
            }
        }
    }

    @Test
    fun part1() {
        val answer = 977
        val packet = readPacket()
        fun getVersionSum(packet: Packet): Int {
            return when (packet) {
                is LiteralPacket -> packet.version
                is OperatorPacket -> packet.version + packet.operands.sumOf { getVersionSum(it) }
            }
        }

        assertEquals(answer, getVersionSum(packet))
    }

    @Test
    fun part2() {
        val packet = readPacket()
        fun getValue(packet: Packet): ULong {
            return when (packet) {
                is LiteralPacket -> packet.value
                is OperatorPacket -> {
                    when (packet.type) {
                        PacketType.Sum -> packet.operands.sumOf { getValue(it) }
                        PacketType.Product -> packet.operands.fold(1UL) { acc, p -> acc * getValue(p) }
                        PacketType.Minimum -> packet.operands.minOf { getValue(it) }
                        PacketType.Maximum -> packet.operands.maxOf { getValue(it) }
                        PacketType.GreaterThan -> { if (getValue(packet.operands[0]) > getValue(packet.operands[1])) 1UL else 0UL }
                        PacketType.LessThan -> { if (getValue(packet.operands[0]) < getValue(packet.operands[1])) 1UL else 0UL }
                        PacketType.EqualTo -> { if (getValue(packet.operands[0]) == getValue(packet.operands[1])) 1UL else 0UL }
                        else -> throw Error()
                    }
                }
            }
        }
        println(getValue(packet))
    }
}