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

//          database= Room.databaseBuilder(applicationContext, QuizDB::class.java,"Quiz").build()
        //access of database
//        database= QuizDB.getInstance(this)


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




//        lifecycleScope.launch(Dispatchers.IO) {
//        database.questionDao().insertQuestions(
//            listOf(
//                QuestionModel(0,"What does HTML stand for?","cpp","oop","java","python","HyperText Markup Language","HTML"),
//                QuestionModel(0,"Choose correct HTML element for largest heading.","cpp","oop","java","python","<h1>","HTML"),
//                QuestionModel(0,"Which tag is used to create a hyperlink?","cpp","oop","java","python","<a>","HTML"),
//                QuestionModel(0,"Which HTML tag is used to insert an image?","cpp","oop","java","python","<img>","HTML"),
//                QuestionModel(0,"Which attribute specifies an alternate text for an image?","cpp","oop","java","python","alt","HTML"),
//                QuestionModel(0,"Which tag is used to insert a line break?","cpp","oop","java","python","<br>","HTML"),
//                QuestionModel(0,"Which HTML element defines important text?","cpp","oop","java","python","<strong>","HTML"),
//                QuestionModel(0,"What is the correct HTML for creating a checkbox?","cpp","oop","java","python","<input type=checkbox>","HTML"),
//                QuestionModel(0,"How can you make a numbered list?","cpp","oop","java","python","<ol>","HTML"),
//                QuestionModel(0,"What tag is used for table rows?","cpp","oop","java","python","<tr>","HTML"))
//           )
//        }

//        database.questionDao().getQuestions("").observe(this, Observer{
//
//            Log.d("TAG","$database")
//        })


    }

}