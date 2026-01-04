package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuizRepository

class AddQuestionVMFactory(val repository: QuizRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddQuestionVM(repository) as T
    }
}