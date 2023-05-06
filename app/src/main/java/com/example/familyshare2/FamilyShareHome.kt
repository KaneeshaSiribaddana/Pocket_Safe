package com.example.familyshare2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.familyshare2.databinding.ActivityFamilyShareHomeBinding

class FamilyShareHome : AppCompatActivity() {

    private lateinit var binding: ActivityFamilyShareHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyShareHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMenberCard.setOnClickListener {
            val intent = Intent(this, FamilySharingAddMember::class.java)
            startActivity(intent)

        }
    }
}