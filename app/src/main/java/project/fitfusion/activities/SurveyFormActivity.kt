package project.fitfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.auth.User
import project.fitfusion.R
import project.fitfusion.databinding.ActivitySurveyFormBinding
import project.fitfusion.firebase.FirebaseAuthHelper
import project.fitfusion.firebase.Firestore

class SurveyFormActivity : AppCompatActivity() {
    private var binding: ActivitySurveyFormBinding? =null
    private var mUId = ""
    private var mEmail = ""
    private var mName= ""
    private var mGender= ""
    private var mHeight: Double= 0.0
    private var mWeight: Double= 0.0
    private var mAge: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySurveyFormBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupToolbar()

        binding?.flSubmit?.setOnClickListener {
            if(validateForm()) {
                setValues()
                val user = User(
                    mUId,
                    mName,
                    mEmail,
                    mGender,
                    mHeight,
                    mWeight,
                    mAge
                )
                val firestore= Firestore()
                firestore.storeUserInfo(this@SurveyFormActivity, user)
            }
        }
    }

    private fun setValues() {
        mWeight= binding?.etWeight?.text.toString().toDoubleOrNull()?: 0.0
        mHeight= binding?.etHeight?.text.toString().toDoubleOrNull()?: 0.0
        mAge= binding?.etAge?.text.toString().toIntOrNull()?: 0

        if(binding?.rbMale?.isChecked == true) mGender= "Male"
        else if(binding?.rbFemale?.isChecked == true) mGender= "Female"
        else mGender= "Other"

        mName= FirebaseAuthHelper(this@SurveyFormActivity).currentUserName()
        mEmail= FirebaseAuthHelper(this@SurveyFormActivity).currentUserEmail()
        mUId= FirebaseAuthHelper(this@SurveyFormActivity).currentUserUid()
    }

    private fun validateForm(): Boolean{
        val isAgeEmpty = binding?.etAge?.text?.isEmpty() == true
        val isHeightEmpty = binding?.etHeight?.text?.isEmpty() == true
        val isWeightEmpty = binding?.etWeight?.text?.isEmpty() == true
        val isGenderEmpty = binding?.rgGender?.checkedRadioButtonId==-1

        if( isAgeEmpty || isHeightEmpty || isWeightEmpty || isGenderEmpty){
            Toast.makeText(this@SurveyFormActivity,
                "Field cannot be left empty",
                Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun setupToolbar() {
        setSupportActionBar(binding?.tbPersonalDetails)
        binding?.tbPersonalDetails?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun dataStorageSuccess() {
        Toast.makeText(this@SurveyFormActivity, "Data stored successfully!",
            Toast.LENGTH_SHORT).show()
        val intent= Intent(this@SurveyFormActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun dataStorageFailure() {
        Toast.makeText(this@SurveyFormActivity, "Failed to store data!",
            Toast.LENGTH_SHORT).show()

    }
}