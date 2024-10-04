package project.fitfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import project.fitfusion.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun signOutSuccess() {
        Toast.makeText(this, "You have signed out.", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}