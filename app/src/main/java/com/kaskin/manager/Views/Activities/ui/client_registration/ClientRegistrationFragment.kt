package com.kaskin.manager.Views.Activities.ui.client_registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.databinding.FragmentClientRegistrationBinding

class ClientRegistrationFragment : Fragment() {

    private lateinit var viewModel: ClientRegistrationViewModel
    private var _binding: FragmentClientRegistrationBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ClientRegistrationViewModel::class.java]

        _binding = FragmentClientRegistrationBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}