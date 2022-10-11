package com.kaskin.manager.presentation.dados.database_list

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaskin.manager.databinding.FragmentDatabaseListBinding
import com.kaskin.manager.presentation.adapters.ExternalFileDbAdapter

const val PICK_PDF_FILE = 2

class DatabaseListFragment : Fragment() {
    private lateinit var externalFileDbAdapter: ExternalFileDbAdapter

    private lateinit var contentResolver: ContentResolver

    private var _binding: FragmentDatabaseListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DatabaseListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatabaseListBinding.inflate(inflater, container, false)

        val root: View = binding.root

        configureLoading()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureLoading() {
        externalFileDbAdapter = ExternalFileDbAdapter()
        contentResolver = requireContext().contentResolver

        checkPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            PackageManager.PERMISSION_GRANTED
        )
        checkPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            PackageManager.PERMISSION_GRANTED
        )

        setupExternalStorageFiles()

        LoadFiles()
    }

    private fun LoadFiles() {

        /*val files = GetFilesFromExternalStorage()*/
        /*externalFileDbAdapter.SetDataSet(files.toTypedArray())*/
    }

    private fun setupExternalStorageFiles() = binding.dbList?.apply {
        adapter = externalFileDbAdapter
        layoutManager =
            LinearLayoutManager(this.context)
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (context?.applicationContext!!.checkPermission(
                permission, requestCode, 0
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                emptyArray(),
                requestCode
            );
        }
    }

}