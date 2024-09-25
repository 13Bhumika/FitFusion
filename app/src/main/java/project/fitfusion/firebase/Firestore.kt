package project.fitfusion.firebase

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import project.fitfusion.activities.SurveyFormActivity
import project.fitfusion.models.User

open class Firestore {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun storeUserInfo(context: Context, user: User){
        val userId= user.uId
        val userRef= db.collection("users").document(userId)

        val userMap = hashMapOf(
            "uId" to user.uId,
            "name" to user.name,
            "email" to user.email,
            "gender" to user.gender,
            "height" to user.height,
            "weight" to user.weight,
            "age" to user.age
        )
        userRef.set(userMap)
            .addOnSuccessListener {
                if(context is SurveyFormActivity){
                    context.dataStorageSuccess()
                }
            }
            .addOnFailureListener { e ->
                Log.e("STORAGE_FAILURE", e.message.toString())
                if(context is SurveyFormActivity){
                    context.dataStorageFailure()
                }
            }
    }
}