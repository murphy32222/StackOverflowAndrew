package com.andrew.stackoverflow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.stackoverflow.R
import com.andrew.stackoverflow.databinding.ListItemQuestionBinding
import com.andrew.stackoverflow.model.Question
import com.andrew.stackoverflow.viewmodel.ListItemQuestionViewModel
import java.util.*

class QuestionsAdapter : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    private var questions: List<Question> = Collections.emptyList()

    fun setQuestions(questions: List<Question>?) {
        questions?.let { this.questions = it }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepository(questions.get(position))
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemQuestionBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_question, parent, false
        )
        return ViewHolder(binding)
    }


    class ViewHolder(var binding: ListItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.container) {

        fun bindRepository(question: Question) {
            if (binding.vm == null) {
                binding.vm = ListItemQuestionViewModel(itemView.context, question)
            } else {
                ListItemQuestionViewModel.setQuestion(binding.vm!!, question)
            }
        }
    }
}