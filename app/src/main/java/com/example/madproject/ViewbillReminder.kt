package com.example.madproject

import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.icu.util.CurrencyAmount
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.AlertDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ViewbillReminder : ComponentActivity() {
    private lateinit var dateText:TextView
    private lateinit var titleViewHeading:TextView
    private lateinit var amountText:TextView
    private lateinit var categoryText:TextView
    private lateinit var noteText:TextView
    private lateinit var typeText:TextView
    private lateinit var billId:String

    private lateinit var btnEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_bill)
        initView()
        setValuesView()
        btnEdit.setOnClickListener{
            openEdit(
                intent.getStringExtra("billId").toString(),
                     intent.getStringExtra("billTitle").toString()

                )
        }
    }
    private fun initView() {
         dateText = findViewById(R.id.dateValueView)
        titleViewHeading = findViewById(R.id.titleView)
        amountText = findViewById(R.id.amountViewValue)
        categoryText = findViewById(R.id.categoryViewValue)
        noteText = findViewById(R.id.noteViewValue)
        typeText = findViewById(R.id.typeViewValue)

        btnEdit = findViewById(R.id.btnEdit)
    }
    private fun setValuesView(){
        val timeInMillis = intent.getLongExtra("billDate", 0)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis

        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        billId= intent.getStringExtra("billId").toString()
        titleViewHeading.text=intent.getStringExtra("billTitle")
        dateText.text = dateFormat.format(calendar.time)
        amountText.text=intent.getStringExtra("billAmount")
        categoryText.text=intent.getStringExtra("billCategory")
        noteText.text=intent.getStringExtra("billNote")
        typeText.text=intent.getStringExtra("billType")

    }
private fun openEdit(
    billId:String,
    billTitle:String
) {
    val mData= AlertDialog.Builder(this)
    val inflater=layoutInflater
    val mDataView=inflater.inflate(R.layout.edit_reminder,null)

    mData.setView(mDataView)

    val setBillTitle=mDataView.findViewById<EditText>(R.id.updateBillTitleValue)
    val setBillDate=mDataView.findViewById<EditText>(R.id.updateBillDateValue)
    val setBillAmount=mDataView.findViewById<EditText>(R.id.updateBillAmountValue)
    val setBillCategory=mDataView.findViewById<EditText>(R.id.updateBillCategoryValue)
    val setBillType=mDataView.findViewById<EditText>(R.id.updateBillTypeValue)
    val setBillNote=mDataView.findViewById<EditText>(R.id.updateBillNoteValue)
    val setEditBtn=mDataView.findViewById<Button>(R.id.btnEdit)

    mData.setTitle("Updating $billTitle")
    val alertDialog=mData.create()
    alertDialog.show()

    setEditBtn.setOnClickListener {
//        updateData(
//            billId,
//            billTitle.toString(),
//            set
//        )
    }
Toast.makeText(applicationContext,"Data Updated",Toast.LENGTH_LONG).show()
//    setBillTitle.text= billTitle.text.toString()





}

    fun redirectAddBill(view: View) {
        val intent = Intent(this, AddbillReminder::class.java)
        startActivity(intent)
    }
    fun redirectUpdateBill(view: View) {
        val intent = Intent(this, UpdateBillReminder::class.java)
        startActivity(intent)
    }
}