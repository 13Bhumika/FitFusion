package project.fitfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import project.fitfusion.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToIntroActivity()
    }

    private fun goToIntroActivity() {
        val timer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                finish()
            }
        }
        timer.start()
    }
}