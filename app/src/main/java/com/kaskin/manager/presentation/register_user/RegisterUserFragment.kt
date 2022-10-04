package com.kaskin.manager.presentation.register_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.presentation.login.viewmodels.LoginViewModel
import com.kaskin.manager.databinding.FragmentRegisterUserBinding

class RegisterUserFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentRegisterUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterUserViewModel::class.java]

        _binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}