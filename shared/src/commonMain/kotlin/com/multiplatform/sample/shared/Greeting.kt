package com.multiplatform.sample.shared


class Greeting {
    fun greeting(): String {
        return "Hello, blabla ${Platform().platform}!"
    }
}
