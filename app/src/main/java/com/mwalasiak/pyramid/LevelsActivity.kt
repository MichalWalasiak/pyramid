package com.mwalasiak.pyramid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mwalasiak.pyramid.databinding.ActivityLevelsViewBinding

class LevelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLevelsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}
