package com.kaskin.manager.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentLoginBinding
import com.kaskin.manager.presentation.login.states.LoggedInUserView
import com.kaskin.manager.presentation.login.viewmodels.LoginViewModel
import com.kaskin.manager.utils.Resource


class LoginFragment : Fragment() {

    private val loginViewModel by activityViewModels<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loadingProgressbar
        val loadingBox = binding.loading
        loadingProgressBar.visibility = View.GONE
        val signUpButton = binding.signUpButton

        signUpButton.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment_content_manager_mobile)
                .navigate(R.id.action_nav_login_to_registerUserFragment)
        }

        loginViewModel.loginFormState.observe(viewLifecycleOwner)
        { loginFormState ->
            if (loginFormState == null) {
                return@observe
            }
            loginButton.isEnabled = loginFormState.isDataValid
            loginFormState.usernameError?.let {
                usernameEditText.error = getString(it)
            }
            loginFormState.passwordError?.let {
                passwordEditText.error = getString(it)
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner)
        { loginResult ->

            when (loginResult) {
                is Resource.Error -> {
                    loadingProgressBar.visibility = View.GONE
                    loadingBox?.visibility = View.GONE

                    loginResult.message?.let { showLoginFailed(it) }
                }
                is Resource.Loading -> {
                    loadingProgressBar.visibility = View.VISIBLE
                    loadingBox?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    loadingProgressBar.visibility = View.GONE
                    loadingBox?.visibility = View.GONE

                    loginResult.data?.let { updateUiWithUser(it) }
                }
            }
        }

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }

        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)

        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (loginButton.isEnabled && actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            if (loginButton.isEnabled)
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = "${getString(R.string.welcome)}  ${model.displayName}"
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
        val navController =
            activity?.findNavController(R.id.nav_host_fragment_content_manager_mobile)

        val bundle = Bundle()
        bundle.putSerializable("USER", model) // Serializable Object

        navController?.navigate(R.id.nav_home, bundle)
    }

    private fun showLoginFailed(errorMessage: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}