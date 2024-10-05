package com.devnabeel.testapplication.presentation.taskhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devnabeel.testapplication.R
import com.devnabeel.testapplication.databinding.FragmentTaskHomeBinding
import com.devnabeel.testapplication.domain.model.Task
import com.devnabeel.testapplication.presentation.taskhome.adapter.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskHomeFragment : Fragment() {

     private var binding : FragmentTaskHomeBinding? = null
     val mViewModel : TaskViewModel by  viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTaskHomeBinding.inflate(inflater,container,false)

        binding?.title?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { _, insets ->
                binding?.title?.setMarginTop(insets.systemWindowInsetTop)
                insets.consumeSystemWindowInsets()
            }
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            if (tasks.isEmpty()) {
                showAllDoneMessage()
            } else {
                showTasks(tasks)
            }
        }

        mViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        mViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun showTasks(tasks: List<Task>) {
        binding?.taskList?.hasFixedSize()
        binding?.taskList?.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding?.taskList?.adapter = TaskAdapter(tasks)
        binding?.taskList?.visibility = View.VISIBLE
        binding?.allDoneMessage?.visibility = View.GONE
    }

    private fun showAllDoneMessage() {
        binding?.taskList?.visibility = View.GONE
        binding?.allDoneMessage?.visibility = View.VISIBLE
    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: "An error occurred", Toast.LENGTH_LONG).show()
    }


    fun View.setMarginTop(marginTop: Int) {
        val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        menuLayoutParams.setMargins(0, marginTop, 0, 0)
        this.layoutParams = menuLayoutParams
    }

}