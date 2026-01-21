package com.example.quizapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.LeaderBoardAdapter
import com.example.quizapp.db.CategoryDAO
import com.example.quizapp.db.QuestionDAO
import com.example.quizapp.db.LeaderBoardDAO
import com.example.quizapp.db.QuizDB
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.LeaderBoardVM
import com.example.quizapp.viewmodel.LeaderBoardVMFactory


class LeaderBoardFragment : Fragment() {
    private  lateinit var leaderBoardVM: LeaderBoardVM
    private  lateinit var factory: LeaderBoardVMFactory
    private  lateinit var repository: QuizRepository
    private  lateinit var leaderBoardDAO: LeaderBoardDAO
    private  lateinit var questionDAO: QuestionDAO
    private  lateinit var categoryDAO: CategoryDAO
    private  lateinit var recyclerview: RecyclerView
    private  lateinit var adapter: LeaderBoardAdapter

    private lateinit var db: QuizDB


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         db= QuizDB.getInstance(requireContext().applicationContext)
        leaderBoardDAO= db.leaderboardDao()
        questionDAO= db.questionDao()
        categoryDAO= db.categoryDao()
        repository= QuizRepository(db.categoryDao(),db.questionDao(),db.leaderboardDao())
        factory= LeaderBoardVMFactory(repository)
        leaderBoardVM= ViewModelProvider(this,factory ).get(LeaderBoardVM::class.java)



        recyclerview=view.findViewById(R.id.leaderboardRecyclerView)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter= LeaderBoardAdapter(requireContext(),mutableListOf())
        recyclerview.adapter=adapter

        leaderBoardVM.topScore.observe(viewLifecycleOwner, Observer{ scoreList->

            adapter.updateList(scoreList)
           

            Toast.makeText(requireContext(), "$scoreList", Toast.LENGTH_SHORT).show()
            Log.d("TAG","$scoreList")

        })

    }


}