package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM(val repository: QuizRepository): ViewModel() {

      private var _cardCategory= MutableLiveData<CategoryModel>()
      val   cardCategory : LiveData<CategoryModel> get() = _cardCategory


    fun insertCategory(category: CategoryModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertCategory(category)
        }

    }
    fun getCategory(){
        repository.getCategory()
    }
    
}