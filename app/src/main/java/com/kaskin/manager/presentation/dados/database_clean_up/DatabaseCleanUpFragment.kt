package com.kaskin.manager.presentation.dados.database_clean_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.databinding.FragmentDatabaseCleanUpBinding

class DatabaseCleanUpFragment : Fragment() {
    private var _binding: FragmentDatabaseCleanUpBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: DatabaseCleanUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = ViewModelProvider(this)[DatabaseCleanUpViewModel::class.java]

        _binding = FragmentDatabaseCleanUpBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}