package com.asuper.aidldemo.kotiln

import java.io.File
import java.util.concurrent.locks.Lock

/**
 *
 * @author super
 * @date 2018/6/4
 */
fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
    println("Hello World!") // 分号可以省略
    Generate("lining").vars(1, 2, 3, 5)
    val user = User("Runoob")
    user.print()
    user.length()
    user.toString()
    println("-------------")
    val str = arrayOf("1", "2")
    val array: Array<String?> = arrayOfNulls(2)
    val arr = Array<Int>(10, {i: Int -> i*i})
    val generics = Person("bob").fail()
    println("generate = " + generics.toString())

}

class Generate(val name: String) {
    fun greet() {
        println(name)
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }
    }
}

class User(var name:String)

/**扩展函数**/
fun User.print(){
    println("用户名 $name")
}

fun User.length(){
    println("length")
}

data class Animal(var ani: String)

class Person constructor(name: String) {
//    var name = name
//        set(value) {
//            field = if (value.isEmpty()) "" else value[0].toUpperCase() + value.substring(1)
//        }

    val isValidName
        get() = !name.isEmpty()

    var name = name
        set(value) {
            field = if (value.isNotEmpty()) "" else value[0].toUpperCase().toString()
        }
    companion object {
        @JvmStatic val ass = Person("name")
    }

    inline fun <T> getParent(i: Lock, body: () -> T): T {
        i.lock()
        return body()
    }

    fun getFiles(): Unit {
        val files = File("D://dd").listFiles()
        files?.let {
            for (file in files) {
                println(file.name)
            }
        }
    }

    fun fail() {
        val mapA: Map<Int, Number> = mapOf(2 to 3, 4 to 5)
        println(mapA.toList())
    }

    operator fun unaryPlus() {
        return
    }

}