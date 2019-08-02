package com.midas.demo.ui

fun main(args: Array<String>) {
    val a: Int = 10
    val b: Long = 10
    val c: Int = 20
    val d: String = "abc"
    val e: String = "abcd"
    val f: String = d

    val ooA: Operator = Operator("zhang", 10)
    val ooB: Operator = Operator("li", 20)
    val ooC: Operator = Operator("zhang", 10)
    val ooD: Operator = ooA
    //==
    println("-------- == -------- ")
    println("a == c: ${a == c}")//1
    println("ooA == ooC: ${ooA == ooC}")//0
    println("ooA == ooD: ${ooA == ooD}")//0
    println("d == e: ${d == e}")//1
    println("f == d: ${f == d}")//0

    // equals
    println("-------- equals -------- ")
    println("ooA.equals(ooC): ${ooA.equals(ooC)}")//true 0
    println("d.equals(e): ${d.equals(e)}")//false 1

    // ===
    println("-------- === -------- ")
    println("a === c: ${a === c}")//1
    println("ooA === ooC: ${ooA === ooC}")//1
    println("ooA === ooD: ${ooA === ooD}")//0
    println("d === e: ${d === e}")//1
}

data class Operator(var name: String, var age: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is Operator) {
            return this.age == other.age
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }

}