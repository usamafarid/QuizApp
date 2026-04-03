package com.example.quizapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.activity.MainActivity
import com.example.quizapp.adapter.CardAdapter
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.db.QuizDB
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.HomeVM
import com.example.quizapp.viewmodel.HomeVMFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding ? =null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CardAdapter
    lateinit var homeVM: HomeVM
    lateinit var fab: FloatingActionButton

    // Fragment ban raha hai. Abhi screen par nazar nahi aa raha. Yahan par initial data setup hota hai.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quizDB = QuizDB.getInstance(requireContext().applicationContext)
        val quizrepo = QuizRepository(quizDB.categoryDao(), quizDB.questionDao(), quizDB.leaderboardDao())
        val homeVMFact = HomeVMFactory(requireContext(),quizrepo)
        homeVM = ViewModelProvider(this@HomeFragment, homeVMFact)[HomeVM::class.java]

    }

    //Fragment apni UI (layout file) ko load karta hai. Actor apna costume pehan raha hai.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    // Fragment ki UI ban chuki hai aur ab aap uske Views (Buttons, TextViews, etc.) ko access kar saktay hain.
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fab setup
        (activity as MainActivity).toolbar.title = "Home";

        //fab.show()
        binding.fab.setOnClickListener {

            val popupMenu = PopupMenu(requireContext(), view)
            popupMenu.inflate(R.menu.popup_menu_item)

            popupMenu.setOnMenuItemClickListener { it
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
                }
            }
            popupMenu.show()
        }
        binding.homerecyclerview.layoutManager = GridLayoutManager(context, 2)
        adapter = CardAdapter(requireContext(), mutableListOf())
        recyclerView.adapter = adapter

        homeVM.cardCategory.observe(viewLifecycleOwner, Observer { it ->

           // val category= CategoryModel()
           // homeVM.insertCategory(listOf(category))
               // adapter.getCategories(it)
                adapter.updateList(it)
              //  Log.d("","$it")
                adapter.notifyDataSetChanged()

        })




    }
    // Fragment screen par nazar aa raha hai aur user uske saath interact kar sakta hai (e.g., button click, text type). Actor stage par perform kar raha hai.
    override fun onResume() {
        super.onResume()
    }
//    // Fragment abhi bhi screen par hai, lekin user ab is se interact nahi kar sakta. Yeh tab hota hai jab koi choti si cheez (jaise alarm ka popup ya call notification) screen par aati hai. Aap ke case mein, jab alarm baja ya call aayi, toh sab se pehle onPause() method call hua
    override fun onPause() {
        super.onPause()
    }
////Fragment ab screen par nazar nahi aa raha. Yeh tab hota hai jab aap doosri app khol letay hain ya phone call ki full screen aa jaati hai. onPause() ke foran baad onStop() call hota hai.
    override fun onStop() {
        super.onStop()
    }
//// Fragment ki UI (layout) khatam kar di jaati hai.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
//// Poora Fragment object memory se khatam kar diya jaata hai.
    override fun onDestroy() {
        super.onDestroy()
    }
}
