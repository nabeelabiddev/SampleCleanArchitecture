package com.devnabeel.testapplication.presentation.taskhome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnabeel.testapplication.databinding.ItemTaskBinding
import com.devnabeel.testapplication.domain.DateUtils
import com.devnabeel.testapplication.domain.model.Task

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(task:Task){
            binding.apply {
                title.text = task.title
                date.text= task.duedate?.let {
                    DateUtils.formatDateString(it)
                } ?: "N/A"
                days.text= task.nodayleft?.let { DateUtils.daysLeftFromToday(it).toString() }?: "N/A"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = tasks.size
}