package com.example.quizapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.quizapp.R
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.db.QuizDB
import com.example.quizapp.fragments.HomeFragment
import com.example.quizapp.fragments.QuizFragment
import com.example.quizapp.model.CategoryModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    lateinit var database: QuizDB
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navController: NavController
//    lateinit var fragmentManager: FragmentManager
    lateinit var navHostFragment: NavHostFragment
    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      // navHostFragment= NavHostFragment()
        toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.navController
      //  navController=findNavController(R.id.fragmentContainerView)

        bottomNavigationView= findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        appBarConfiguration= AppBarConfiguration(setOf( R.id.homeFragment,R.id.quizFragment,R.id.resultFragment,
            R.id.leaderBoardFragment,R.id.addQuestionFragment, R.id.addCategoryFragment))


        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }

}