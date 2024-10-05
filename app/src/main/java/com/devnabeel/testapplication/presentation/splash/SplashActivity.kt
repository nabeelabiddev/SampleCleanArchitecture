package com.devnabeel.testapplication.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devnabeel.testapplication.R
import com.devnabeel.testapplication.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main)
    private var splashJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startSplashDelay()
    }

    private fun startSplashDelay() {
        splashJob = scope.launch {
            delay(3000) // 3 seconds delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish the splash activity
        }
    }

    override fun onPause() {
        super.onPause()
        // Cancel the coroutine when the user presses the home button or app goes into the background
        splashJob?.cancel()
    }

    override fun onResume() {
        super.onResume()
        // If the job is not active (i.e., canceled), restart the delay when coming back
        if (splashJob?.isActive == false || splashJob == null) {
            startSplashDelay()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel any running coroutine when the activity is destroyed
        scope.cancel()
    }
}