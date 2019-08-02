package com.midas.demo.mvp.model.bean

import java.io.Serializable

/**
 * Created by midas on 2019/5/27
 * desc:分类 Bean
 */
data class CategoryBean(
    val id: Long,
    val name: String,
    val description: String,
    val bgPicture: String,
    val bgColor: String,
    val headerImage: String
) : Serializable
