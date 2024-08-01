package project.fitfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.fitfusion.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private var binding: ActivityIntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flSignUp?.setOnClickListener {

        }
        binding?.flSignIn?.setOnClickListener {

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}