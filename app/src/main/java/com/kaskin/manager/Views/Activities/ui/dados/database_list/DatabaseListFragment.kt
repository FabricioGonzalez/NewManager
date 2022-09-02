package com.kaskin.manager.Views.Activities.ui.dados.database_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R

class DatabaseListFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseListFragment()
    }

    private lateinit var viewModel: DatabaseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_database_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}