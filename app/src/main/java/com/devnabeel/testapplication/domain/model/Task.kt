package com.devnabeel.testapplication.domain.model

data class Task(
    var id:String?,
    var title:String?,
    var description:String?,
    val duedate: String?,
    val nodayleft:String?
)
