package com.example.to_doapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.to_doapp.adapters.TasksAdapter
import com.example.to_doapp.database.TaskDatabase
import com.example.to_doapp.databinding.FragmentTasksBinding
import java.util.Calendar

class TasksListFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    lateinit var adapter: TasksAdapter
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TasksAdapter(null)
        binding.rvTasks.adapter = adapter
        calendar = Calendar.getInstance()


        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month - 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            calendar.set(Calendar.DAY_OF_MONTH, date.day)
            val tasks = TaskDatabase
                .getInstance(requireContext())
                .getTasksDao()
                .getTasksByDate(calendar.time)
            adapter.updateData(tasks)
        }

    }
}