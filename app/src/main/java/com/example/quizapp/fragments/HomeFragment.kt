package com.example.quizapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R

import com.example.quizapp.activity.MainActivity
import com.example.quizapp.adapter.CardAdapter
import com.example.quizapp.db.QuizDB
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.QuizVM
import com.example.quizapp.viewmodel.QuizVMFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CardAdapter
//    lateinit var quizDB: QuizDB
//    lateinit var quizrepo: QuizRepository
     lateinit var quizVM: QuizVM
//    lateinit var quizVMFactory: QuizVMFactory
    lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val quizDB = QuizDB.getInstance(requireContext())
        val quizrepo = QuizRepository(quizDB.categoryDao(), quizDB.questionDao(), quizDB.leaderboardDao())
        val quizVMFactory = QuizVMFactory(quizrepo)
        quizVM = ViewModelProvider(
                this@HomeFragment,
                quizVMFactory)[QuizVM::class.java]
//

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//




//        lifecycleScope.launch(Dispatchers.IO) {
//                val category = quizDB.categoryDao().getCategory()
//                if (category == emptyList<CategoryModel>()) {
//                    val allCategories = listOf(
//                        CategoryModel(1, R.drawable.imghtml, "HTML", "10 Question"),
//                        CategoryModel(2, R.drawable.imgcss, "CSS", "10 Question"),
//                        CategoryModel(3, R.drawable.imgjs, "JAVASCRIPT", "10 Question"),
//                        CategoryModel(4, R.drawable.imgpython, "PYTHON", "10 Question"),
//                        CategoryModel(5, R.drawable.php, "PHP", "10 Question")
//                    )
//                    quizDB.categoryDao().insertCategory(allCategories)
//                    quizVM.insertCategory(category)
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(requireContext(), "insert category", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Log.d("", "$category")
//                }
//            }
        //   listObserver()
        //  1 fab setup
        (activity as MainActivity).toolbar.title ="Home";
        fab = view.findViewById(R.id.fab)
        //fab.show()
        fab.setOnClickListener {

            val popupMenu = PopupMenu(requireContext(), view)
            popupMenu.inflate(R.menu.popup_menu_item)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {

                    R.id.Categories -> {
                        findNavController().navigate(R.id.action_homeFragment_to_addCategoryFragment)
                        true
                    }

                    R.id.Questions -> {
                        findNavController().navigate(R.id.action_homeFragment_to_addQuestionFragment)
                        true
                    }

                    else -> false



//            val bundle = Bundle()
//            bundle.putString("$this","")
                    //    findNavController().navigate(R.id.action_homeFragment_to_addQuestionFragment)
                    //bundle

                }
            }
            popupMenu.show()
        }



        2
        recyclerView = view.findViewById(R.id.homerecyclerview)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        //  val list = listOf(CategoryModel(1,R.drawable.imghtml,"HTML",  "10 Questions"), CategoryModel(2,R.drawable.imgcss,"CSS",  "10 Questions"), CategoryModel(3,R.drawable.imgjs,"JAVASCRIPT",  "10 Questions"), CategoryModel(4,R.drawable.imgpython,"PYTHON",  "10 Questions"), CategoryModel(5,R.drawable.php,"PHP",  "10 Questions"))

        //3
//        val categoryModel = listOf(
//            CategoryModel(1, R.drawable.imghtml, "HTML", "10 Question"),
//            CategoryModel(2, R.drawable.imgcss, "CSS", "10 Question"),
//            CategoryModel(3, R.drawable.imgjs, "JAVASCRIPT", "10 Question"),
//            CategoryModel(4, R.drawable.imgpython, "PYTHON", "10 Question"),
//            CategoryModel(5, R.drawable.php, "PHP", "10 Question")
//        )
        adapter = CardAdapter(requireContext(), mutableListOf())
        recyclerView.adapter = adapter


//        val bundle = Bundle()
//        bundle.putInt("categoryID",2)
//        findNavController().navigate(R.id.action_homeFragment_to_quizFragment, bundle)
//         NavController(requireContext()).navigate(R.id.action_homeFragment_to_addQuiz)

//        fun addQuizquestion() {
//            findNavController().navigate(R.id.action_homeFragment_to_addQuiz)
//        }
        quizVM.categories.observe(viewLifecycleOwner, Observer { categoryModels ->
            if (categoryModels.isNotEmpty()) {
                adapter.updateList(categoryModels)

            }
        })

//        fun insertCategory(categoryModel: List<CategoryModel>) {
//           lifecycleScope.launch(Dispatchers.Main) {
//               quizVM.Categories.observe(viewLifecycleOwner, Observer {
//                   val db = quizDB.categoryDao().getCategory()
//                   if (it.isEmpty()) {
//                       val allCategories = listOf(
//                           CategoryModel(1, R.drawable.imghtml, "HTML", "10 Question"),
//                           CategoryModel(2, R.drawable.imgcss, "CSS", "10 Question"),
//                           CategoryModel(3, R.drawable.imgjs, "JAVASCRIPT", "10 Question"),
//                           CategoryModel(4, R.drawable.imgpython, "PYTHON", "10 Question"),
//                           CategoryModel(5, R.drawable.php, "PHP", "10 Question")
//                       )
//                       quizVM.insertCategory(categoryModel)
//                       Log.d("", "$allCategories")
//                       Toast.makeText(requireContext(), "$allCategories", Toast.LENGTH_SHORT).show()
//                   }
//                   else {
//                       val result = quizDB.categoryDao().getCategory()
//                       Log.d("Tag", "$result")
//                   }
//
//               })
//
//           }
//
//
//        }

    }


//    private fun insertCategories() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            if (quizDB == null) {
//                quizDB.categoryDao().insertCategory(
//                    listOf(
//                        CategoryModel(0, R.drawable.imghtml, "HTML", "10 Question"),
//                        CategoryModel(0, R.drawable.imgcss, "CSS", "10 Question"),
//                        CategoryModel(0, R.drawable.imgjs, "JAVASCRIPT", "10 Question"),
//                        CategoryModel(0, R.drawable.imgpython, "PYTHON", "10 Question"),
//                        CategoryModel(0, R.drawable.php, "PHP", "10 Question")
//                    )
//                )
//            }
//        }
//    }


//    private fun listObserver() {
//        lifecycleScope.launch((Dispatchers.Main)){
//            quizVM.questionList.observe(viewLifecycleOwner, Observer{
//                // val result=  quizDB.questionDao().getQuestions("HTML")
//                if (it.isNotEmpty()){
//                    Log.d("TAG",it.toString())
//                    Toast.makeText(requireContext(),"$it", Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    Toast.makeText(requireContext(),"data not fetch", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//        }
//    }
}
