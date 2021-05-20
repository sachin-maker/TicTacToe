package com.sachin.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.sachin.tictactoe.databinding.ActivityMainBinding
import com.sachin.tictactoe.game.GameViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playNowButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, GameViewActivity::class.java))
        }

        binding.playerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.text = "Player One plays first!"
            } else {
                buttonView.text = "Player Two plays first!"
            }
        }
    }
}