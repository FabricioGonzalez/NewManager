package com.kaskin.manager.presentation.dados.database_backup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.databinding.FragmentDatabaseBackupBinding

class DatabaseBackupFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseBackupFragment()
    }

    private var _binding: FragmentDatabaseBackupBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DatabaseBackupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentDatabaseBackupBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}