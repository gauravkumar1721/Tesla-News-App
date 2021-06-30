package com.example.completeapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.completeapp.R
import com.example.completeapp.fragments.FragmentA
import com.example.completeapp.fragments.FragmentB

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}