package com.midas.eye.net.exception

/**
 * Created by midas on 2019/05/28.
 * desc:
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}