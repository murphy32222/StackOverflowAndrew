package com.andrew.stackoverflow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrew.stackoverflow.R
import com.andrew.stackoverflow.adapters.QuestionsAdapter
import com.andrew.stackoverflow.databinding.ActivityMainBinding
import com.andrew.stackoverflow.interfaces.RepoListener
import com.andrew.stackoverflow.model.Question
import com.andrew.stackoverflow.viewmodel.QuestionViewModel

class QuestionsActivity : AppCompatActivity(), RepoListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuestionViewModel
    private lateinit var adapter: QuestionsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //setup binding
        viewModel   = QuestionViewModel(this, this)
        binding.vm  = viewModel

        //setup recycler view
        adapter = QuestionsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //call rest api
        viewModel.fetchQuestions()
    }

    override fun onDataFetched(questions: List<Question>?) {
        adapter.setQuestions(questions)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }
}
