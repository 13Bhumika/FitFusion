package project.fitfusion.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import project.fitfusion.R
import project.fitfusion.databinding.ActivitySignInBinding
import project.fitfusion.databinding.ActivitySignUpBinding
import project.fitfusion.firebase.FirebaseAuthHelper

class SignInActivity: AppCompatActivity() {

    private var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupToolbar()

        binding?.flSignIn?.setOnClickListener{
            if(!validateForm()) return@setOnClickListener

            val email =binding?.etEmailSignIn?.text.toString()
            val password= binding?.etPasswordSignIn?.text.toString()

            FirebaseAuthHelper(this@SignInActivity).signInUser(email!!, password!!)

        }
    }
    private fun validateForm(): Boolean{
        val emailIsEmpty = binding?.etEmailSignIn?.text?.isEmpty() == true
        val passwordIsEmpty = binding?.etPasswordSignIn?.text?.isEmpty() == true

        if(emailIsEmpty || passwordIsEmpty){
            Toast.makeText(this@SignInActivity,
                "Field cannot be left empty",
                Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupToolbar(){
        setSupportActionBar(binding?.tbSignIn)
        binding?.tbSignIn?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    fun signInComplete(){
        Toast.makeText(this@SignInActivity,
            "Sign In successful!",
            Toast.LENGTH_SHORT).show()

        startActivity(Intent(this@SignInActivity, SurveyFormActivity::class.java))
        finish()
    }
    fun signInFailed(){
        Toast.makeText(this@SignInActivity,
            "Sign In Failed!",
            Toast.LENGTH_SHORT).show()
    }
}