package com.example.eval_d3_p3

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        // Simple shared message used as a sample.
        return "Hello, ${platform.name}!"
    }
}