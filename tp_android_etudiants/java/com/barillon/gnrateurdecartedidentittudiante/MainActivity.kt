package com.barillon.gnrateurdecartedidentittudiante

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var studentLevel: Spinner
    private lateinit var logoView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setSpinner()
    }

    private fun initializeViews() {
        logoView = findViewById(R.id.uni_logo)
        studentLevel = findViewById(R.id.student_level)

    }

    private fun setSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.student_level_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            studentLevel.adapter = adapter
        }
    }
}