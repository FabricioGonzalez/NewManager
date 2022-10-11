package com.kaskin.manager.presentation.supervisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.databinding.FragmentSupervisorBinding

class SupervisorFragment : Fragment() {
    private var _binding: FragmentSupervisorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SupervisorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSupervisorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}