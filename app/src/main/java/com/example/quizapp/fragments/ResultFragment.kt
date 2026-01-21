package com.example.quizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.util.Util
import com.example.quizapp.R
import com.example.quizapp.db.CategoryDAO
import com.example.quizapp.db.LeaderBoardDAO
import com.example.quizapp.db.QuestionDAO
import com.example.quizapp.db.QuizDB
import com.example.quizapp.model.LeaderBoardModel
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.LeaderBoardVM
import com.example.quizapp.viewmodel.LeaderBoardVMFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.time.Duration.Companion.days


class ResultFragment : Fragment() {

    private  lateinit var score: TextView
    private  lateinit var homeButton: Button
    private  lateinit var leaderButton: Button
    private  lateinit var db: QuizDB
    private  lateinit var leaderBoardDAO: LeaderBoardDAO
    private  lateinit var questionDAO: QuestionDAO
    private  lateinit var categoryDAO: CategoryDAO
    private  lateinit var repository: QuizRepository
    private  lateinit var factory: LeaderBoardVMFactory
    private  lateinit var leaderBoardVM: LeaderBoardVM





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        score=view.findViewById<TextView>(R.id.tvScore)
        homeButton=view.findViewById(R.id.btnHome)
        leaderButton=view.findViewById(R.id.btnLeaderboard)

        db= QuizDB.getInstance(requireContext().applicationContext)
        leaderBoardDAO= db.leaderboardDao()
        questionDAO= db.questionDao()
        categoryDAO= db.categoryDao()
        repository= QuizRepository(categoryDAO,questionDAO,leaderBoardDAO)
        factory= LeaderBoardVMFactory(repository)
        leaderBoardVM= ViewModelProvider(this,factory).get(LeaderBoardVM::class.java)



        val finalScore=arguments?.getInt("final-score",0)
        val completeQuestions=arguments?.getInt("total-questions",0)

        val totalScore = completeQuestions
        val stringConcat: String = "$finalScore / $totalScore"

        score.text=stringConcat
        //db=score.text

        lifecycleScope.launch(Dispatchers.IO){
            val user= LeaderBoardModel(0,"usama",finalScore ?: 0, Date())

            leaderBoardVM.saveScore(user)
        }




        homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }

        leaderButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_leaderBoardFragment)
        }

    }


}