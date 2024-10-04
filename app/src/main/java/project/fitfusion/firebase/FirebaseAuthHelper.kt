package project.fitfusion.firebase

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.oAuthProvider
import project.fitfusion.activities.IntroActivity
import project.fitfusion.activities.MainActivity
import project.fitfusion.activities.SignInActivity
import project.fitfusion.activities.SignUpActivity

class FirebaseAuthHelper(private val activity: Activity) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpUser(context: Context, name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileUpdateTask ->
                        if (profileUpdateTask.isSuccessful) {
                            Log.d(TAG, "User profile updated with display name: $name")
                        } else {
                            Log.e(TAG, "Profile update failed: ${profileUpdateTask.exception?.message}")
                        }
                    }

                    if (activity is SignUpActivity) {
                        activity.signUpComplete()
                    }
                } else {
                    val errorMessage = task.exception?.message
                    Log.e("FirebaseAuthHelper", "Sign Up Failed: $errorMessage")
                    if (activity is SignUpActivity) {
                        activity.signUpFailure()
                    }
                }
            }
    }

    fun signInUser(context: Context, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    if (activity is SignInActivity) {
                        activity.signInComplete()
                    }

                } else {
                    val errorMessage = task.exception?.message
                    Log.e("FirebaseAuthHelper", "Sign In Failed: $errorMessage")
                    if (activity is SignInActivity) {
                        activity.signInFailed()
                    }
                }
            }
    }
    fun autoSignIn(context: Context) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if (context is IntroActivity) {
                context.signInSuccess()
            }
        }


        fun signOutUser(context: Context) {
            auth.signOut()
            if (context is MainActivity) {
                context.signOutSuccess()
            }
        }

        fun currentUserUid(): String {
            return auth.currentUser?.uid.toString()
        }

        fun currentUserName(): String {
            return auth.currentUser?.displayName.toString()
        }

        fun currentUserEmail(): String {
            return auth.currentUser?.email.toString()
        }
    }
}
