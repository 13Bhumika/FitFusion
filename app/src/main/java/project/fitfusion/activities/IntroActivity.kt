package project.fitfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import project.fitfusion.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private var binding: ActivityIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flSignUp?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))

        }
        binding?.flSignIn?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun signInSuccess() {
        Toast.makeText(this,"You have signed in.",Toast.LENGTH_SHORT).show()

        val intent = Intent(this ,MainActivity::class.java)
        startActivity(intent)
    }
}