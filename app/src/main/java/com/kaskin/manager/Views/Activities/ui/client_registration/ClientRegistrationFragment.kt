package com.kaskin.manager.Views.Activities.ui.client_registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R

class ClientRegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = ClientRegistrationFragment()
    }

    private lateinit var viewModel: ClientRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ClientRegistrationViewModel::class.java]
        return inflater.inflate(R.layout.fragment_client_registration, container, false)
    }
}