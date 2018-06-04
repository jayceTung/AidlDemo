package com.asuper.aidldemo.kotiln

/**
 *
 * @author super
 * @date 2018/6/4
 */
fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
    println("Hello World!") // 分号可以省略
    Generate("lining").vars(1, 2, 3, 5)
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