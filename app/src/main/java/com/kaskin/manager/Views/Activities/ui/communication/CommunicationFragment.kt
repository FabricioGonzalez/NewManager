package com.kaskin.manager.Views.Activities.ui.communication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R

class CommunicationFragment : Fragment() {

    companion object {
        fun newInstance() = CommunicationFragment()
    }

    private lateinit var viewModel: CommunicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CommunicationViewModel::class.java]
        return inflater.inflate(R.layout.fragment_communication, container, false)
    }
}