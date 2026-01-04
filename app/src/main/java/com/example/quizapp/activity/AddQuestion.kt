//package com.example.quizapp.activity
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.example.quizapp.R
//import com.example.quizapp.db.QuizDB
//import com.example.quizapp.model.CategoryModel
//import com.example.quizapp.model.QuestionModel
//import com.example.quizapp.repository.QuizRepository
//import com.example.quizapp.viewmodel.AddQuestionVM
//import com.example.quizapp.viewmodel.AddQuestionVMFactory
//
//class AddQuestion : AppCompatActivity(), AdapterView.OnItemSelectedListener {
//
//    private lateinit var questionText: EditText
//    private lateinit var optionA: EditText
//    private lateinit var optionB: EditText
//    private lateinit var optionC: EditText
//    private lateinit var optionD: EditText
//    private lateinit var correctans : EditText
//    private lateinit var spinner: Spinner
//    private lateinit var button: Button
//
//    private lateinit var addQuestionVM: AddQuestionVM
//    //gemini code
//    private var list: List<CategoryModel> = emptyList()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_quiz)
//
//        // --- Initialize Views ---
//        questionText = findViewById(R.id.etquestiontext)
//        optionA = findViewById(R.id.etoptionA)
//        optionB = findViewById(R.id.etoptionB)
//        optionC = findViewById(R.id.etoptionC)
//        optionD = findViewById(R.id.etoptionD)
//        correctans = findViewById(R.id.etcorrect)
//        spinner = findViewById(R.id.spinner)
//        button = findViewById(R.id.btnADD)
//
//        // --- Setup ViewModel ---
//        val database = QuizDB.getInstance(applicationContext)
//        val repository = QuizRepository(database.categoryDao(), database.questionDao(), database.leaderboardDao())
//        val factory = AddQuestionVMFactory(repository)
//        addQuestionVM = ViewModelProvider(this, factory)[AddQuestionVM::class.java]
//
//        // --- Setup Listeners and Observers ---
//        //gemini code
//       // button.isEnabled=false
//        spinner.onItemSelectedListener
//        getListCategory()
//        questionInsert()
//    }
//
//    private fun getListCategory() {
//        addQuestionVM.categories.observe(this, Observer { categoryModels ->
//            if (categoryModels.isNotEmpty()) {
//                // 1. Save the full list of CategoryModel objects for later use.
//                this.list = categoryModels
//
//                // 2. Create a list of just the names for the spinner display.
//                val categoryNames = categoryModels.map {
//                    it.name
//                }
//              //  categoryNames.size
//
//                // 3. Create an ArrayAdapter with the list of names and set it to the spinner.
////                ArrayAdapter
////                    .createFromResource(
////                        this,
////                        R.array.category,
////                        android.R.layout.simple_spinner_dropdown_item)
////                    .run { it
////                    }
//
//                //gemini code
//                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                //adapter.notifyDataSetChanged()
//                spinner.adapter = adapter
//                button.isEnabled=true
//               // spinner.isShown
//            }
//            else{
//                Log.d("TAG","list is empty")
//            }
//        })
//    }
//
//    /**
//     * Sets up the click listener for the "Add Question" button.
//     */
//
//    private fun questionInsert() {
//
//       button.setOnClickListener {
//            // --- Input Validation (Simple) ---
////            val questionStr = questionText.text.toString()
////            if (questionStr.isBlank()) {
////                Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show()
////                return@setOnClickListener
////            }
////
////            // Check if any category is selected.
//           val categoryList=list
//            if (categoryList.isEmpty() || spinner.selectedItemPosition < 0) {
//                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            // 1. Get the position of the selected item from the spinner.
//            val selectedPosition = spinner.selectedItemPosition
//           // spinner.showContextMenu()
//
//            // 2. Use the position to get the correct CategoryModel object from our saved list.
//            val selectedCategory = list[selectedPosition]
//
//            // 3. Get the correct category ID (cid) from that object.
//            val correctCid = selectedCategory.cid
//
//            // 4. Create the QuestionModel with the correct cid.
//           addQuestionVM.insertQuestion(
//               QuestionModel(
//                 0, // Auto-generated
//                 questionText.text.toString(),
//                 optionA.text.toString(),
//                 optionB.text.toString(),
//                 optionC.text.toString(),
//                 optionD.text.toString(),
//                correctans.text.toString(),
//                 null, // Initially null
//                 correctCid
//                 // Pass the correct ID here
//            ))
//
//
//            // 5. Insert the question via the ViewModel.
////            addQuestionVM.insertQuestion(newQuestion)
//
//            Toast.makeText(this, "Question added to '${selectedCategory.name}' successfully!", Toast.LENGTH_SHORT).show()
//
//            // Optional: Clear fields after adding
//            questionText.text.clear()
//            optionA.text.clear()
//            optionB.text.clear()
//            optionC.text.clear()
//            optionD.text.clear()
//            correctans.text.clear()
//        }
//
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val selectedItem = parent?.getItemAtPosition(position).toString()
//        // You can use this for debugging or other purposes
//        Log.d("AddQuiz", "Selected: $selectedItem")
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>?) {
//        // Optional: Handle case where nothing is selected
//    }
//}
