package project.fitfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.fitfusion.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}