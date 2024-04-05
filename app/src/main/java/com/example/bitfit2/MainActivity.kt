package com.example.bitfit2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager:FragmentManager = supportFragmentManager
        val userInputFragment:Fragment=UserInputFragment()
        val foodsListFragment:FoodsListFragment= FoodsListFragment()
        val dashboardFragment:DashboardFragment=DashboardFragment()

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation)
        val addItemButton=findViewById<Button>(R.id.add_button)
        bottomNavigationView.setOnItemSelectedListener{ item ->
            lateinit var fragment:Fragment
            when(item.itemId){
                R.id.nav_login -> fragment=foodsListFragment
                R.id.nav_dashboard ->fragment=dashboardFragment
            }
            fragmentManager.beginTransaction().replace(R.id.place_holder_frame,fragment).commit()
            true
        }
        bottomNavigationView.selectedItemId= R.id.nav_login

        addItemButton.setOnClickListener{
            fragmentManager.beginTransaction().replace(R.id.place_holder_frame,userInputFragment).commit()

        }



    }

//    override fun onResume(){
//        super.onResume()
//
//        if (!isButtonVisible){
//         findViewById<Button>(R.id.add_button)?.visibility =View.VISIBLE
//         isButtonVisible=true
//        }
//
//    }
}