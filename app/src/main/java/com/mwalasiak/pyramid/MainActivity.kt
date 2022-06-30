package com.mwalasiak.pyramid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mwalasiak.pyramid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addSetButton.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }
    }
}
