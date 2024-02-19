package com.example.to_doapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.to_doapp.databinding.ActivityHomeBinding
import com.example.to_doapp.fragments.AddTaskBottomSheet
import com.example.to_doapp.fragments.SettingsFragment
import com.example.to_doapp.fragments.TasksListFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tasks -> {
                    pushFragment(TasksListFragment())
                }

                R.id.settings -> {
                    pushFragment(SettingsFragment())

                }
            }

            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.tasks
        binding.fabAddTask.setOnClickListener {
            val bottomSheetFragment = AddTaskBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, null)
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.fragmentContainer.id, fragment)
            .commit()
    }


}