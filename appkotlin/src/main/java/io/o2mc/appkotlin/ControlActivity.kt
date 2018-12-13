package io.o2mc.appkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_control.*

class ControlActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_control)

    //  Click listeners
    buttonResumeTracking.setOnClickListener { onResumeTracking() }
    buttonStopTracking.setOnClickListener { onStopTracking() }
  }

  /**
   * Called on 'Stop Tracking' button click
   */
  private fun onStopTracking() {
    App.o2mc.stop()
  }

  /**
   * Called on 'Resume Tracking' button click
   */
  private fun onResumeTracking() {
    App.o2mc.resume()
  }
}
