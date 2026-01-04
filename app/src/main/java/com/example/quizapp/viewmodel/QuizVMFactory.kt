package com.example.quizapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuizRepository

class QuizVMFactory(  val repository: QuizRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizVM(repository) as T
    }
}