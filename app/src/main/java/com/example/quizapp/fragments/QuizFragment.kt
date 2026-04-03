package com.example.quizapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.db.QuizDB
import com.example.quizapp.model.LeaderBoardModel
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.QuizVM
import com.example.quizapp.viewmodel.QuizVMFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? =null
    private val binding get() = _binding!!
    private lateinit var quizVM: QuizVM
    private lateinit var list: List<QuestionModel>
    private var currentQuestion = 0
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        //create objects of DB,repo and VM
        val quizDB = QuizDB.getInstance(requireContext())
        val quizRepo = QuizRepository(quizDB.categoryDao(),quizDB.questionDao(),quizDB.leaderboardDao())
        val quizVMFactory = QuizVMFactory(quizRepo)
        quizVM = ViewModelProvider(this@QuizFragment, quizVMFactory)[QuizVM::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding= FragmentQuizBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonsSetup()

        binding.radiogroup.setOnCheckedChangeListener {
            group,  // zada tar iski zaroorat ni parti
            checkedID // magar iski zaroorat lazmi parti ha ya wo option ha jisa user na click kia ha uski ID data ha
            ->

            if (checkedID !=-1 ){
                val selectedRadioButton= view.findViewById<RadioButton>(checkedID)
                val selectedAnswer= selectedRadioButton.text

                if (selectedAnswer == list[currentQuestion].correct) {
              //  Toast.makeText(context, "green border", Toast.LENGTH_SHORT).show()
                    quizVM.incrementScore()
                }

//                else{
//                    Toast.makeText(context, "red border", Toast.LENGTH_SHORT).show()
//                }

            }
        }

        val catId=arguments?.getInt("categoryID",-1)
        if (catId!=-1 && catId!=0){
            quizVM.loadQuestions(catId!!)
        }
        questionObserver()
//        loadQuestions(cid = 1)

         timerObserver()
    }

    private fun timerObserver() {
        quizVM.remainingTime.observe(viewLifecycleOwner, Observer{

            binding.tvTimer.text=it.toString()
        })

        quizVM.quizFinish.observe(viewLifecycleOwner, Observer{
            if (it==true){
                val score= quizVM.score.value
                val questionList= list.size
                val bundle= Bundle()
                if (score != null) {
                    bundle.putInt("final-score",score)
                }
                bundle.putInt("total-questions",questionList)
                Toast.makeText(requireContext(), "Your's Time UP", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_quizFragment_to_resultFragment,bundle)}
        })
    }

    private fun buttonsSetup() {
        //1
        binding.btnNext.setOnClickListener{

            currentQuestion ++
            if (currentQuestion==list.size){
                Toast.makeText(requireContext(), "Quiz Finish", Toast.LENGTH_SHORT).show()
                //2
            }
            else {
                displayData()
            }

            //3
        }
        binding.btnPrevious.setOnClickListener{
            //4
            currentQuestion--
            if (currentQuestion==1){
                Toast.makeText(requireContext(), "This is 1 Question of Your Quiz", Toast.LENGTH_SHORT).show()
            }
            else{
                displayData()
            }
        }
        binding.btnSubmit.setOnClickListener {
            //alertdialogbox
            val alertDialog= AlertDialog.Builder(requireContext())
            //inflater
            val inflater=layoutInflater
            val inflate=inflater.inflate(R.layout.dialog_score_save,null)
            //find views
            editText=inflate.findViewById(R.id.etSaveName)
            //use builder object
            alertDialog.setView(inflate)
            val score= quizVM.score.value
            val bundle= Bundle()
            if (score != null) {
                bundle.putInt("final-score",score)

            alertDialog.setPositiveButton("Save"){ dialog,score->
                GlobalScope.launch(Dispatchers.Main) {
                    val name = editText.text.toString()
                    val score = score

                    quizVM.repository.leaderBoardDAO.insertResult(
                        LeaderBoardModel(0, name, score, Date())
                    )
                 //   quizVM.insertScore()
                    findNavController().navigate(R.id.action_quizFragment_to_resultFragment, bundle)
                }
                }


            }
            alertDialog.show()

        //    val score= quizVM.score.value
            val questionList= list.size
        //    val bundle= Bundle()
            if (score != null) {
                bundle.putInt("final-score",score)
            }
            bundle.putInt("total-questions",questionList)
            quizVM.cancelTimer()


        }
    }

    //changing in this obser question in now
    private fun questionObserver() {
        quizVM.questionList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
            //    adapter.updateList(it.toMutableList())
                Log.d("TAG", it.toString())
                list=it
                displayData()
                quizVM.startTimer()
//                if (timer.equals(0)){
//                    Toast.makeText(requireContext(), "Time Up", Toast.LENGTH_SHORT).show()
//                    quizVM.quizFinish
//                }
            }
            else {
                Toast.makeText(context, "No questions found for this category", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun displayData(){
     val question = list[currentQuestion]
        binding.tvQuestionText.text=question.questionText
        val questionCounter: String= "${currentQuestion +1} / ${list.size}"
        binding.tvQuestionCount.text=questionCounter
        binding.button1.text=question.optionA
        binding.button2.text=question.optionB
        binding.button3.text=question.optionC
        binding.button4.text=question.optionD

         binding.radiogroup.clearCheck()
        if (currentQuestion==0){
            binding.btnPrevious.visibility= View.GONE
            binding.btnNext.visibility= View.VISIBLE
            binding.btnSubmit.visibility= View.GONE
        }
        else if (currentQuestion==list.size-1){
            binding.btnPrevious.visibility= View.VISIBLE
            binding.btnNext.visibility= View.GONE
            binding.btnSubmit.visibility= View.VISIBLE
        }
        else  {
            binding.btnPrevious.visibility= View.VISIBLE
            binding.btnNext.visibility= View.VISIBLE
            binding.btnSubmit.visibility= View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}