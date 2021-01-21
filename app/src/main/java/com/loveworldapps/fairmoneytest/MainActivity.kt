package com.loveworldapps.fairmoneytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.loveworldapps.fairmoneytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val _binding  by  lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}