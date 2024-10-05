package com.devnabeel.testapplication.domain

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateUtils {

    fun formatDateString(inputDate: String): String {
        // Define the input and output date formats
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Original format
        val outputFormat = SimpleDateFormat("MMM dd yyyy", Locale.getDefault()) // Desired format

        // Parse the input date string
        val date = inputFormat.parse(inputDate)

        // Return the formatted date string
        return if (date != null) {
            outputFormat.format(date)
        } else {
            "Invalid date"
        }
    }

    fun daysLeftFromToday(targetDate: String): Long {
        // Define the input date format (from the API)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Parse the target date string into a Date object
        val parsedDate = dateFormat.parse(targetDate) ?: return -1L // Return -1 if parsing fails

        // Get the current date
        val today = Calendar.getInstance().time

        // Calculate the difference in milliseconds
        val diffInMillis = parsedDate.time - today.time

        // Convert milliseconds to days
        return TimeUnit.MILLISECONDS.toDays(diffInMillis)
    }
}