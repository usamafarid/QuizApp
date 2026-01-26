package com.example.quizapp.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.model.LeaderBoardModel
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timer
class QuizVM(var repository: QuizRepository): ViewModel() {

    private  lateinit var list: List<QuestionModel>

    private lateinit var timer: CountDownTimer
    fun startTimer() {
         timer = object : CountDownTimer(60000L, 1000L) {

            override fun onTick(remainningTime: Long) {
               val remain= remainningTime.div(1000)
//                val time= (remain)
                _remainingTime.postValue(remain.toInt())
            }
            override fun onFinish() {
                _quizFinish.postValue(true)

            }
        }.start()
    }
    fun cancelTimer(){
        timer.cancel()
    }
    fun  loadQuestions(categoryID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getQuestion(categoryID)
                _questionList.postValue(result)
            } catch (e: Exception) {
                Log.d("TAG", e.message.toString())
            }
        }
    }
    fun incrementScore(){

        if (selectOption.value == currentQuestion.value?.correct){

            val update=(_score.value ?: 0) + 1
            _score.postValue(update)
        }

    }
    private var _questionList = MutableLiveData< List<QuestionModel>>()

    //observe UI but not modify behind return mutable livedata
    val questionList: LiveData<List<QuestionModel>>
        get() = _questionList

//    private var _categories= MutableLiveData< List<CategoryModel>>()
//
//    //observe UI but not modify behind return mutable livedata
//    val categories: LiveData<List<CategoryModel>>
//        get() = _categories
    private var _currentQuestion= MutableLiveData<QuestionModel>()

    val   currentQuestion: LiveData<QuestionModel>
        get() = _currentQuestion

    private var _currentIndex= MutableLiveData(0)
    val   currentIndex: LiveData<Int>
        get() = _currentIndex

    private var _selectOption= MutableLiveData<String>()
    val   selectOption: LiveData<String>
        get() = _selectOption

    private var _score= MutableLiveData(0)
    val   score: LiveData<Int>
        get() = _score

    private var _remainingTime= MutableLiveData(60)
    val   remainingTime: LiveData<Int>
        get() = _remainingTime

    private var _isPaused= MutableLiveData(false)
    val   isPaused: LiveData<Boolean>
        get() = _isPaused

    private var _quizFinish= MutableLiveData<Boolean>(false)
    val   quizFinish: LiveData<Boolean>
        get() = _quizFinish
    }
