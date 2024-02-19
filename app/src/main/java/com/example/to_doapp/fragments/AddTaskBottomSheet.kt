package com.example.to_doapp.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.to_doapp.database.Task
import com.example.to_doapp.database.TaskDatabase
import com.example.to_doapp.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addTaskBtn.setOnClickListener {
            if (validateFields()) {
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                TaskDatabase
                    .getInstance(requireContext())
                    .getTasksDao()
                    .insertTask(
                        Task(
                            title = binding.title.text.toString(),
                            description = binding.description.text.toString(),
                            date = calendar.time,
                            isDone = false
                        )
                    )
                dismiss()
            }
        }
        calendar = Calendar.getInstance()
        binding.selectTimeTv.setOnClickListener {
            val picker = TimePickerDialog(requireContext(), object : OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    binding.selectTimeTv.text = "$hourOfDay:$minute"
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
            picker.show()
        }
        binding.selectDateTv.setOnClickListener {
            val picker = DatePickerDialog(
                requireContext(),
                object : OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        binding.selectDateTv.text = "$dayOfMonth/${month + 1}/$year"
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            picker.show()
        }

    }

    private fun validateFields(): Boolean {
        if (binding.title.text?.isEmpty() == true || binding.title.text?.isBlank() == true) {
            binding.title.error = "Required"
            return false
        } else
            binding.title.error = null
        if (binding.description.text?.isEmpty() == true || binding.description.text?.isBlank() == true) {
            binding.description.error = "Required"
            return false
        } else
            binding.description.error = null

        return true
    }
}