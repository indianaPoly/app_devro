package com.soongsil.example.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soongsil.example.adapter.ViewPagerAdapter
import com.soongsil.example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = ViewPagerAdapter(this)
    }
}
