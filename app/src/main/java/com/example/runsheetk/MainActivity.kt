package com.example.runsheetk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.runsheetk.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var firebaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseRef = FirebaseDatabase.getInstance().getReference("test")


        binding.tvSendData.setOnClickListener {

            firebaseRef.setValue("what hath God wrought").
            addOnCompleteListener{
                Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}