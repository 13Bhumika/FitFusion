package project.fitfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import project.fitfusion.R
import project.fitfusion.databinding.ActivitySurveyFormBinding
import project.fitfusion.firebase.FirebaseAuthHelper

class SurveyFormActivity : AppCompatActivity() {
    private var binding: ActivitySurveyFormBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySurveyFormBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupToolbar()

        binding?.flSubmit?.setOnClickListener {
            if(!validateForm()) return@setOnClickListener

            //val gender = binding?.etGender?.text?.toString()
            val age = binding?.etAge?.text?.toString()
            val height = binding?.etHeight?.text?.toString()
            val weight = binding?.etWeight?.text?.toString()

            FirebaseAuthHelper(this@SurveyFormActivity)
        }

    }
    private fun validateForm(): Boolean{
        //val isGenderEmpty = binding?.etGender?.text?.isEmpty() == true
        val isAgeEmpty = binding?.etAge?.text?.isEmpty() == true
        val isHeightEmpty = binding?.etHeight?.text?.isEmpty() == true
        val isWeightEmpty = binding?.etWeight?.text?.isEmpty() == true

        if( isAgeEmpty || isHeightEmpty || isWeightEmpty){
            Toast.makeText(this@SurveyFormActivity,
                "Field cannot be left empty",
                Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
    private fun setupToolbar() {
        setSupportActionBar(binding?.tbPersonalDetails)
        binding?.tbPersonalDetails?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}