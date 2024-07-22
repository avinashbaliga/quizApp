package com.avinash.navigationpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.avinash.navigationpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Name from saved instance: "+(savedInstanceState?.getString("name")?:"no name detected yet"))
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onPause() {
        super.onPause()
        println("On pause called")
    }

    override fun onRestart() {
        super.onRestart()
        println("On restart called")
    }

    override fun onResume() {
        super.onResume()
        println("On resume called")
        println("Name from saved instance ")
    }

    override fun onStop() {
        super.onStop()
        println("On stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("On destroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name", "Avinash")
        println("On save instance state called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println("name on resume: "+savedInstanceState.getString("name"))
    }
}

