package com.example.routino.ui.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.routino.R
import com.example.routino.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var _binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}