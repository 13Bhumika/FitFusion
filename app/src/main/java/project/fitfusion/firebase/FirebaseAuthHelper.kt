package project.fitfusion.firebase

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import project.fitfusion.activities.SignInActivity
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

    fun signInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    if(activity is SignInActivity) {
                        activity.signInComplete()
                    }

                } else {
                    val errorMessage = task.exception?.message
                    Log.e("FirebaseAuthHelper", "Sign In Failed: $errorMessage")
                    if(activity is SignInActivity) {
                        activity.signInFailed()
                    }
                }
            }
    }
}