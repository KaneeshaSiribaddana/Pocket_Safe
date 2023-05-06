package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.madp.expensesModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ptexpenses : AppCompatActivity() {

    private lateinit var exAmount: EditText
    private lateinit var exDate: EditText
    private lateinit var exCategory: EditText
    private lateinit var exTitle: EditText
    private lateinit var btnadd: Button

    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ptexpenses)


        exAmount = findViewById(R.id.exAmount)
        exDate = findViewById(R.id.exDate)
        exCategory = findViewById(R.id.exCategory)
        exTitle = findViewById(R.id.extTitle)
        btnadd = findViewById(R.id.button5)

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        btnadd.setOnClickListener {

            saveAddData()
        }

//        val bcarrow: ImageButton = findViewById<ImageButton>(R.id.backhome)
//        bcarrow.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

    }
    private fun saveAddData() {

        val eAmount = exAmount.text.toString()
        val eDate = exDate.text.toString()
        val eCategory = exCategory.text.toString()
        val eTitle = exTitle.text.toString()

        if (eAmount.isEmpty()) {
            exAmount.error = "please enter Amount"
        } else if (eDate.isEmpty()) {
            exDate.error = "please enter Date"
        } else if (eCategory.isEmpty()) {
            exCategory.error = "please enter "
        } else if (eTitle.isEmpty()) {
            exTitle.error = "please enter Amount"
        } else {
            val exsId = dbRef.push().key!!

            val expenses = expensesModel(exsId, eAmount, eDate, eCategory, eTitle)

            dbRef.child(exsId).setValue(expenses)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "error ${err.message}", Toast.LENGTH_LONG).show()

                }
        }

    }


}