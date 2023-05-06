package com.example.madproject.models

data class BillReminderModel(
    var billId: String? = null,
    var billTitle: String? = null,
    var billAmount: String? = null,
    var billDate: Long? = null,
    var billCategory: String? = null,
    var billNote: String? = null,
    var userId: String? = null,
    var billType: String? = null
)