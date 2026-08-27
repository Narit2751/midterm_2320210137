package com.example.thirtydays.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.thirtydays.R

data class Ritual(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    val dayNumber: Int,
    @StringRes val description: Int
)

val rituals = listOf(
    Ritual(R.drawable.day_1, R.string.ritual_title_1, 1, R.string.ritual_description_1),
    Ritual(R.drawable.day_2, R.string.ritual_title_2, 2, R.string.ritual_description_2),
    Ritual(R.drawable.day_3, R.string.ritual_title_3, 3, R.string.ritual_description_3),
    Ritual(R.drawable.day_4, R.string.ritual_title_4, 4, R.string.ritual_description_4),
    Ritual(R.drawable.day_5, R.string.ritual_title_5, 5, R.string.ritual_description_5),
    Ritual(R.drawable.day_6, R.string.ritual_title_6, 6, R.string.ritual_description_6),
    Ritual(R.drawable.day_7, R.string.ritual_title_7, 7, R.string.ritual_description_7),
    Ritual(R.drawable.day_8, R.string.ritual_title_8, 8, R.string.ritual_description_8),
    Ritual(R.drawable.day_9, R.string.ritual_title_9, 9, R.string.ritual_description_9),
    Ritual(R.drawable.day_10, R.string.ritual_title_10, 10, R.string.ritual_description_10),
    Ritual(R.drawable.day_11, R.string.ritual_title_11, 11, R.string.ritual_description_11),
    Ritual(R.drawable.day_12, R.string.ritual_title_12, 12, R.string.ritual_description_12),
    Ritual(R.drawable.day_13, R.string.ritual_title_13, 13, R.string.ritual_description_13),
    Ritual(R.drawable.day_14, R.string.ritual_title_14, 14, R.string.ritual_description_14),
    Ritual(R.drawable.day_15, R.string.ritual_title_15, 15, R.string.ritual_description_15),
    Ritual(R.drawable.day_16, R.string.ritual_title_16, 16, R.string.ritual_description_16),
    Ritual(R.drawable.day_17, R.string.ritual_title_17, 17, R.string.ritual_description_17),
    Ritual(R.drawable.day_18, R.string.ritual_title_18, 18, R.string.ritual_description_18),
    Ritual(R.drawable.day_19, R.string.ritual_title_19, 19, R.string.ritual_description_19),
    Ritual(R.drawable.day_20, R.string.ritual_title_20, 20, R.string.ritual_description_20),
    Ritual(R.drawable.day_21, R.string.ritual_title_21, 21, R.string.ritual_description_21),
    Ritual(R.drawable.day_22, R.string.ritual_title_22, 22, R.string.ritual_description_22),
    Ritual(R.drawable.day_23, R.string.ritual_title_23, 23, R.string.ritual_description_23),
    Ritual(R.drawable.day_24, R.string.ritual_title_24, 24, R.string.ritual_description_24),
    Ritual(R.drawable.day_25, R.string.ritual_title_25, 25, R.string.ritual_description_25),
    Ritual(R.drawable.day_26, R.string.ritual_title_26, 26, R.string.ritual_description_26),
    Ritual(R.drawable.day_27, R.string.ritual_title_27, 27, R.string.ritual_description_27),
    Ritual(R.drawable.day_28, R.string.ritual_title_28, 28, R.string.ritual_description_28),
    Ritual(R.drawable.day_29, R.string.ritual_title_29, 29, R.string.ritual_description_29),
    Ritual(R.drawable.day_30, R.string.ritual_title_30, 30, R.string.ritual_description_30)
)