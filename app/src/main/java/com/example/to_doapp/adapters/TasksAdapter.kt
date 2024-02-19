package com.example.to_doapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.to_doapp.database.Task
import com.example.to_doapp.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(var tasks: List<Task>?) : Adapter<TasksAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = tasks?.get(position) ?: return
        holder.bind(item)
    }

    fun updateData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.title
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            if (task.date != null)
                binding.time.text = simpleDateFormat.format(task.date)
        }

    }

}
