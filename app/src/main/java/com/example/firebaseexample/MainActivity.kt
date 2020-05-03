package com.example.firebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        customKeysButton.setOnClickListener {
            crashlytics.setCustomKey("current_level", 3)
            crashlytics.setCustomKey("last_UI_action", "logged_in")
        }

        customMessageButton.setOnClickListener {
            crashlytics.log("E/TAG: my message")
        }

        setUserIdButton.setOnClickListener {
            crashlytics.setUserId("user123456789")
        }

        nonFatalExceptionButton.setOnClickListener {
            try {
                throw NullPointerException("exception example")
            } catch (e: Exception) {
                crashlytics.recordException(e)
                // handle your exception here
            }
        }
    }

}