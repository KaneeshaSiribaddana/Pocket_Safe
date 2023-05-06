package com.example.madproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isEmpty
import com.example.madproject.models.BillReminderModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar

class AddbillReminder : ComponentActivity() {
    private lateinit var title:EditText
    private lateinit var amount:EditText
    private lateinit var category:EditText
    private lateinit var note:EditText
    private lateinit var radioGroup:RadioGroup
    private lateinit var insertButton: Button
    public var userId=2232


    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_billreminder)
        title=findViewById(R.id.bill_AddTitleValue)
        amount=findViewById(R.id.bill_AddAmountValue)
        category=findViewById(R.id.bill_AddCategoryValue)
        note=findViewById(R.id.bill_AddNoteValue)
        radioGroup = findViewById<RadioGroup>(R.id.billTypeGroup)
        val datePicker = findViewById<DatePicker>(R.id.bill_AddDateValue)

        dbRef=FirebaseDatabase.getInstance().getReference("BillReminder")
            insertButton = findViewById<Button>(R.id.addButtonInsert)
            insertButton.setOnClickListener {
            val selectedType = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val billTypeValue = selectedType.text.toString()

            val titleValue=title.text.toString()
            val amountValue=amount.text.toString()
            val categoryValue=category.text.toString()
            val noteValue = note.text.toString()
            val userIdValue = userId.toString()
            if(titleValue.isEmpty()){
                title.error="Please Enter a Title"
            }else if(amountValue.isEmpty()){
                amount.error="Please Enter a Amount"
            }else if(categoryValue.isEmpty()){
                category.error="Please Enter a Amount"
            }else if(selectedType == null){
                Toast.makeText(this,"Please Select a Reminding Type",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else{
                if (datePicker.isEmpty()) {
                    Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                } else {


                    val year = datePicker.year
                    val month = datePicker.month
                    val dayOfMonth = datePicker.dayOfMonth

                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    val selectedDate = calendar.time
                    val dateInMillis = selectedDate.time

                    Log.d("AddbillReminder", "Selected date: $selectedDate")
                    val reminderId = dbRef.push().key!!
                    val reminder = BillReminderModel(
                        reminderId, titleValue, amountValue,
                        dateInMillis, categoryValue, noteValue, userIdValue, billTypeValue
                    )

                    dbRef.child(reminderId).setValue(reminder)
                        .addOnCompleteListener {
                            Toast.makeText(this, "Reminder Added Successfully", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, BillReminder::class.java)
                            startActivity(intent)
                        }.addOnFailureListener { err ->
                            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

                        }
                }
            }

        }
    }

}