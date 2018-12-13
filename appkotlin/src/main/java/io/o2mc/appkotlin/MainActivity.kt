package io.o2mc.appkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    App.o2mc.track("MainActivityCreated") // access o2mc by property syntax

    // Click listeners
    buttonCreateEvent.setOnClickListener { onCreateEventHandler() }
    buttonOpenControls.setOnClickListener { onOpenControls() }
    buttonOnSetEndpoint.setOnClickListener { onSetEndpointHandler() }
  }

  /**
   * Called on 'Create Track Event' button click
   */
  private fun onCreateEventHandler() {
    val editText: EditText = findViewById(R.id.editText)
    val text: String = editText.text.toString() // access text by property syntax

    App.o2mc.trackWithProperties("Clicked button: 'Create Track Event'", text)
  }

  /**
   * Called on 'Set Endpoint' button click
   */
  private fun onSetEndpointHandler() {
    val editText: EditText = findViewById(R.id.editTextEndpointIp)
    val text: String = editText.text.toString() // access text by property syntax

    App.o2mc.setEndpoint(text)
  }

  /**
   * Called on 'Open' button click
   */
  private fun onOpenControls() {
    startActivity(Intent(this, ControlActivity::class.java))
  }
}
