package com.example.eval_d3_p3

class JVMPlatform: Platform {
    // JVM platform name is based on the running Java version.
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()