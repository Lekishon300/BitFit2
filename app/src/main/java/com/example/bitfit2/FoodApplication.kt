package com.example.bitfit2

import android.app.Application

class FoodApplication :Application(){
    val db by lazy {AppDatabase.getInstance(this)}
}