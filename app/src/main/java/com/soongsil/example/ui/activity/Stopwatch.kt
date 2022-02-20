package com.soongsil.example.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.soongsil.example.databinding.ActivityStopwatchBinding

class Stopwatch : AppCompatActivity() {
    private lateinit var binding: ActivityStopwatchBinding
    var initTime = 0L
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()
            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
        binding.stopButton.setOnClickListener{
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }
        binding.resetButton.setOnClickListener{
            initTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }
        binding.backbutton.setOnClickListener {
            finish()
        }
    }
}