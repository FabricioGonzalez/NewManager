package com.kaskin.manager.Views.Activities.ui.dados.database_backup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R

class DatabaseBackupFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseBackupFragment()
    }

    private lateinit var viewModel: DatabaseBackupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_database_backup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseBackupViewModel::class.java)
        // TODO: Use the ViewModel
    }

}