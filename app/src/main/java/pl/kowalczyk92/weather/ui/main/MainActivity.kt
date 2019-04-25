package pl.kowalczyk92.weather.ui.main

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import pl.kowalczyk92.weather.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
