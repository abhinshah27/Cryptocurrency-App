package my.demo.myapplication.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import my.demo.myapplication.R
import my.demo.myapplication.utils.setStatusBarColorAndAppearance


/**
 * MainActivity - The main entry point of the Android application.
 *
 * This class represents the main activity of the Android app, serving as the entry point
 * for the user interface and application logic.
 **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set status bar color and appearance
        setStatusBarColorAndAppearance()
        // Set the content view to the layout defined in activity_main.xml
        setContentView(R.layout.activity_main)
    }
}
