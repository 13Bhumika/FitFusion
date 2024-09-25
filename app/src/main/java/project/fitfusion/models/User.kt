package project.fitfusion.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uId: String,
    val name: String,
    val email: String,
    val gender: String,
    val height: Double,
    val weight: Double,
    val age: Int
): Parcelable
