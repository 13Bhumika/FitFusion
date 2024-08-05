package project.fitfusion.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import project.fitfusion.activities.SignUpActivity

class FirebaseAuthHelper(private val activity: Activity) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) {
                task ->
                if(task.isSuccessful) {
                    if(activity is SignUpActivity) {
                        activity.signUpComplete()
                    }
                }else {
                    val errorMessage = task.exception?.message
                    Log.e("FirebaseAuthHelper", "Sign Up Failed: $errorMessage")
                    if(activity is SignUpActivity) {
                        activity.signUpFailure()
                    }
                }
            }
    }
}