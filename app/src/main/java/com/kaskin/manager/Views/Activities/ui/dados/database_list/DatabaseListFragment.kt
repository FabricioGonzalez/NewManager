package com.kaskin.manager.Views.Activities.ui.dados.database_list

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaskin.manager.Views.Adapters.ExternalFileDbAdapter
import com.kaskin.manager.databinding.FragmentDatabaseListBinding

class DatabaseListFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseListFragment()
    }

    private lateinit var externalFileDbAdapter: ExternalFileDbAdapter

    private lateinit var contentResolver: ContentResolver

    private lateinit var viewModel: DatabaseListViewModel

    private var _binding: FragmentDatabaseListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = ViewModelProvider(this)[DatabaseListViewModel::class.java]

        _binding = FragmentDatabaseListBinding.inflate(inflater, container, false)

        externalFileDbAdapter = ExternalFileDbAdapter()

        contentResolver = requireContext().contentResolver

        checkPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            PackageManager.PERMISSION_GRANTED
        )
        checkPermission(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            PackageManager.PERMISSION_GRANTED
        )

        setupExternalStorageFiles()

        loadFiles()
        setupExternalStorageFiles()

        return binding.root
    }

    private fun loadFiles() {

        val files = GetFilesFromExternalStorage()
        externalFileDbAdapter.SetDataSet(files.toTypedArray())


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
        } else {
            Toast.makeText(requireActivity(), "Permission already granted", Toast.LENGTH_SHORT)
                .show();
        }
    }

    private fun GetFilesFromExternalStorage(): List<String> {
        var items: List<String> = emptyList()
/*
        val collection = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)


        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
        )

        contentResolver.query(
            collection,
            projection,
            null,
            null,
            "${MediaStore.Files.FileColumns.DISPLAY_NAME} ASC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val displayName = cursor.getString(nameColumn)

                println(id)
                items.plus(displayName)
            }
        }
*/

        val directory = this.context?.applicationContext?.getExternalFilesDir("/Manager")
        val result = directory?.listFiles()
//
//        var file = File("${directory?.absolutePath}/manager.db")
//        file.createNewFile()
//        file.writeText("")

        result?.forEach {
            items = items.plus(it?.absolutePath ?: "not found")
        }
        return items

    }


}