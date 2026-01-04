package com.example.quizapp.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.core.graphics.drawable.toIcon
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.example.quizapp.R
import com.example.quizapp.activity.MainActivity
import com.example.quizapp.db.QuizDB
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.AddCategoryVM
import com.example.quizapp.viewmodel.AddCategoryVMFact
import com.example.quizapp.viewmodel.AddQuestionVMFactory
import kotlin.contracts.contract


class AddCategoryFragment : Fragment() {

    private lateinit var imageURI: Uri
    private  lateinit var imageView: ImageView
    lateinit var categoryET: EditText
    lateinit var questionET: EditText

    lateinit var button: Button
    lateinit var addCategoryVM: AddCategoryVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).toolbar.title="Add Category";

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView=view.findViewById(R.id.imagePicker)
        categoryET=view.findViewById(R.id.categoryText)
        questionET=view.findViewById(R.id.questionText)
        button=view.findViewById(R.id.botton)

        val db= QuizDB.getInstance(requireContext().applicationContext)
//        val categoryDAO=db.categoryDao()
//        val questionDAO=db.questionDao()
//        val leaderBoardDAO=db.leaderboardDao()
        val repository= QuizRepository(db.categoryDao(),db.questionDao(),db.leaderboardDao())
        val factory= AddCategoryVMFact(repository)
         addCategoryVM= ViewModelProvider(this@AddCategoryFragment,factory).get(AddCategoryVM::class.java)

        addCategory()
    }

    private fun addCategory() {
        val  pickVisualMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("ImagePicker", "$uri")
                 imageView.setImageURI(uri).toString()
            } else {
                Log.d("ImagePicker", "Not Found")
            }

            button.setOnClickListener {

//            val pickVisualMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                if (uri!=null){
//                    Log.d("ImagePicker","$uri")
//                    imageView.setImageURI(uri)
//                }
//                else{
//                    Log.d("ImagePicker","Not Found")
//                }
//            }
//            val image = pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

            addCategoryVM.insertCategory(
              addCategoryVM.category(
                  0,
                  categoryET.text.toString(),
                  questionET.text.toString(),
                   uri.toString()
              )
          )


                imageView.setImageURI(uri).toString()
            }
            val insertCategory= CategoryModel(0, categoryET.text.toString(),questionET.text.toString(),uri.toString())
            addCategoryVM.insertCategory(insertCategory)
           // addCategoryVM.getCategory()

            Toast.makeText(requireContext(), "Category Add Successfully", Toast.LENGTH_SHORT).show()
        }
        pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

    }

}