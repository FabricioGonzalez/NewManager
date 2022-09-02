package com.kaskin.manager.Views.Activities.ui.dados.database_clean_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R

class DatabaseCleanUpFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseCleanUpFragment()
    }

    private lateinit var viewModel: DatabaseCleanUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_database_clean_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseCleanUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}