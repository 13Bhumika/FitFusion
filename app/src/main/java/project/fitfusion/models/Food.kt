package project.fitfusion.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val loggedBy: String,
    val date: String,
    val calories: Double,
    val carbohydrate: Double,
    val protein: Double,
    val fat: Double,
    val quantity: Double,
    val unit: String
):Parcelable
