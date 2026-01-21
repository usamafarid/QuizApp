package com.example.quizapp.fragments


import android.content.ContentResolver
import android.content.Intent
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
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.bumptech.glide.Glide
import com.example.quizapp.R
import com.example.quizapp.activity.MainActivity
import com.example.quizapp.db.QuizDB
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.AddCategoryVM
import com.example.quizapp.viewmodel.AddCategoryVMFact
import com.example.quizapp.viewmodel.AddQuestionVMFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.attribute.FileAttributeView
import kotlin.contracts.contract


class AddCategoryFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var categoryET: EditText
    private lateinit var questionET: EditText

    private lateinit var button: Button
    private lateinit var addCategoryVM: AddCategoryVM

    private lateinit var db: QuizDB

    //empty box
    private  var setImageUri: Uri? = null
    lateinit var contentResolver: ContentResolver

    val pickVisualMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

            try {
                if (uri != null) {
                    //using glide library
                    Glide.with(this).load(uri).into(imageView)
//                    contentResolver = requireContext().contentResolver
//                    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
//                    contentResolver.takePersistableUriPermission(uri, takeFlags)

//                    imageView.setImageURI(uri)
                    setImageUri = uri
                }
            }
            catch (securityException: SecurityException) {
                Toast.makeText(requireContext(), "${securityException.printStackTrace()}", Toast.LENGTH_SHORT).show()

            }

                //    context?.contentResolver?.openInputStream(uri)


                //    val imageUri= File(context?.filesDir,"${System.currentTimeMillis()}.Png")

//             val file=   FileOutputStream(imageUri)
//                imageUri.inputStream().copyTo(file)
//                imageUri.inputStream().close()
//
//                imageUri.outputStream().close()
//               val absolutePath= imageUri.absoluteFile.toString()

//                val categoryModel= CategoryModel(
//                    0,
//                    categoryET.text.toString(),
//                    questionET.text.toString(),
//                    absolutePath
//                )
//                addCategoryVM.insertCategory(
//                    categoryModel
//                )
//

//                Log.d("ImagePicker", "$uri")
//                 imageView.setImageURI(uri).toString()
                //1:Book in Library


                //2:Personal Dairy
//                File(context?.filesDir, "${imageUri}.Png")

                //3:Photocopy
//            } else {
//                Log.d("ImagePicker", "Not Found")
//            }
//
//        }

            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).toolbar.title = "Add Category"


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imagePicker)
        categoryET = view.findViewById(R.id.categoryText)
        questionET = view.findViewById(R.id.questionText)
        button = view.findViewById(R.id.botton)

        db = QuizDB.getInstance(requireContext().applicationContext)
//        val categoryDAO=db.categoryDao()
//        val questionDAO=db.questionDao()
//        val leaderBoardDAO=db.leaderboardDao()
        val repository = QuizRepository(db.categoryDao(), db.questionDao(), db.leaderboardDao())
        val factory = AddCategoryVMFact(repository)
        addCategoryVM =
            ViewModelProvider(this@AddCategoryFragment, factory).get(AddCategoryVM::class.java)



        addCategory()
    }

    fun addCategory() {

        imageView.setOnClickListener {
            pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        // pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//        val pickVisualMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//
//            if (uri != null) {
//                    Log.d("ImagePicker", "$uri")
//
//                    contentResolver = requireContext().contentResolver
//
//                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
//
//                contentResolver.takePersistableUriPermission(uri,takeFlags)
//
//
//                    imageView.setImageURI(uri)
//                }
//            else {
//                    Log.d("ImagePicker", "Not Found")
//                }

//                pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        button.setOnClickListener {

            //delete
//            addCategoryVM.insertCategory(
//                addCategoryVM.category(
//                    0,
//                    categoryET.text.toString(),
//                    questionET.text.toString(),
////                    uri.toString()
//                    it.toString()
//                )
//            )
            if (setImageUri != null) {
                val imagePath = setImageUri.toString()

                val categoryModel = CategoryModel(
                    0,
                    categoryET.text.toString(),
                    questionET.text.toString(),
                    imagePath
                )
                addCategoryVM.insertCategory(categoryModel)
            }

            Toast.makeText(
                requireContext(),
                "Category Add Successfully",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack(R.id.homeFragment, true)
        }
//                imageView.setImageURI(uri).toString()

//        pickVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))


    }

//            val insertCategory= CategoryModel(0, categoryET.text.toString(),questionET.text.toString(),uri.toString())
//            addCategoryVM.insertCategory(insertCategory)
        // addCategoryVM.getCategory()

}