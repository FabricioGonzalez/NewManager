package com.kaskin.manager.presentation.dados.database_list

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaskin.manager.Contracts.Updatable
import com.kaskin.manager.Enums.DbState
import com.kaskin.manager.Models.DbModel
import com.kaskin.manager.Views.Adapters.ExternalFileDbAdapter
import com.kaskin.manager.databinding.FragmentDatabaseListBinding
import java.time.LocalDate

const val PICK_PDF_FILE = 2

class DatabaseListFragment : Fragment(), Updatable {
    private lateinit var externalFileDbAdapter: ExternalFileDbAdapter

    private lateinit var contentResolver: ContentResolver

    private var _binding: FragmentDatabaseListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: DatabaseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[DatabaseListViewModel::class.java]

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

    private fun GetFilesFromExternalStorage(): List<DbModel> {
        var items: List<DbModel> = emptyList()
        val collection = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATE_ADDED)

        contentResolver.query(
            collection,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED)
            val sizeColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val displayName = cursor.getString(nameColumn)
                val date = cursor.getString(dateColumn)
                val size = cursor.getString(sizeColumn)

                println(id)
                items.plus(DbModel(displayName, LocalDate.now(), size.toFloat(), DbState.Unbound))
            }
        }

        /*  val directory = this.context?.applicationContext?.getExternalFilesDir("/Manager")
          val result = directory?.listFiles()

          result?.forEach {
              items = items.plus(it?.absolutePath ?: "not found")
          }*/
        return items

    }

    fun openFile(pickerInitialUri: Uri) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/vnd.sqlite3"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
        }

        startActivityForResult(intent, PICK_PDF_FILE)
    }

    override fun Update() {
        val result = GetFilesFromExternalStorage()

//       openFile(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY))
//        externalFileDbAdapter.SetDataSet(result.toTypedArray())
//        externalFileDbAdapter.notifyDataSetChanged()
    }
}