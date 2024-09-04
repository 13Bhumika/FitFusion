package project.fitfusion.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import project.fitfusion.R
import project.fitfusion.databinding.ActivitySignUpBinding
import project.fitfusion.firebase.FirebaseAuthHelper

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupToolBar()

        binding?.flSignUp?.setOnClickListener {
            if(!validateForm()) return@setOnClickListener

            val email = binding?.etEmail?.text?.toString()
            val password = binding?.etPassword?.text?.toString()

            FirebaseAuthHelper(this@SignUpActivity).signUpUser(email!!, password!!)
        }
    }

    private fun validateForm(): Boolean {
        val nameIsEmpty = binding?.etName?.text?.isEmpty() == true
        val emailIsEmpty = binding?.etEmail?.text?.isEmpty() == true
        val passwordIsEmpty = binding?.etPassword?.text?.isEmpty() == true

        if (nameIsEmpty || emailIsEmpty || passwordIsEmpty) {
            Toast.makeText(
                this@SignUpActivity,
                getString(R.string.field_cannot_be_left_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupToolBar() {
        setSupportActionBar(binding?.tbSignUp)
        binding?.tbSignUp?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun signUpComplete() {
        Toast.makeText(
            this@SignUpActivity,
            "Sign up successful!",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this@SignUpActivity, IntroActivity::class.java))
        finish()
    }

    fun signUpFailure() {
        Toast.makeText(
            this@SignUpActivity,
            "Sign up failed!",
            Toast.LENGTH_SHORT
        ).show()
    }
}