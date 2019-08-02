package com.midas.eye.net

/**
 * Created by midas on 2019/05/28.
 * 封装返回的数据
 */
class BaseResponse<T>(
    val code: Int,
    val msg: String,
    val data: T
)