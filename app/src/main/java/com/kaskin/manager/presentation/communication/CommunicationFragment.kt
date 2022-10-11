package com.kaskin.manager.presentation.communication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.databinding.FragmentCommunicationBinding

class CommunicationFragment : Fragment() {
    private var _binding: FragmentCommunicationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CommunicationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommunicationBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}