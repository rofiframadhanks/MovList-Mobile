package com.ramadhan.movlist.model
import androidx.annotation.DrawableRes

data class Movie(
    val id: Int,
    val title: String,
    val year: String,
    val rating: Double,
    @DrawableRes val imageRes: Int
)
