package com.tasks.myshoppingapp.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasks.myshoppingapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var context: Context = this
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            startActivity(Intent(context,DashboardActivity::class.java))
        }

    }
}