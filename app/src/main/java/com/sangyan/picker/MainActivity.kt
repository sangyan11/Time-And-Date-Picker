package com.sangyan.picker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity()  , DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener{
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val c = Calendar.getInstance()
        tvTime.text = c.time.toString()
     pickDate()
        reset.setOnClickListener{
           tvTime.text = "0:0"
        }
    }

    private fun pickDate() {
        setTimeAndDate.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
    }

    fun getDateTimeCalendar() {
        val calender = Calendar.getInstance()
        day = calender.get(Calendar.DAY_OF_MONTH)
        month = calender.get(Calendar.MONTH)
        year = calender.get(Calendar.YEAR)
        minute = calender.get(Calendar.MINUTE)
        hour = calender.get(Calendar.HOUR)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
       savedDay = dayOfMonth
        savedMonth = month
        savedYear = year
        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,true).show()

    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
       savedHour = p1
        savedMinute = p2
        tvTime.text = "Date is : $savedDay-${savedMonth+1}-$savedYear \n Time is : $savedHour : $savedMinute"
    }
}