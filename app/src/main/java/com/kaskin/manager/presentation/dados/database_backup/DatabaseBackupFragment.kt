package com.kaskin.manager.presentation.dados.database_backup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.databinding.FragmentDatabaseBackupBinding
import java.io.FileOutputStream


class DatabaseBackupFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseBackupFragment()
    }

    private var _binding: FragmentDatabaseBackupBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DatabaseBackupViewModel by activityViewModels()


    @SuppressLint("NewApi")
    var launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result?.data?.data?.let { viewModel.setCurrentPath(it) }

            val dataDir = "${requireContext().dataDir.absolutePath}/databases/${com.kaskin.manager.utils.Constants.DBName}"

            val list = requireContext().databaseList()

            list.forEach { db ->
                requireContext().deleteDatabase(db)
            }

            viewModel.currentPath.value?.let { res ->
                requireContext()
                    .contentResolver
                    .openInputStream(res)
            }
                .use { fis ->
                    FileOutputStream(dataDir).use { os ->

                        val buffer = ByteArray(1024)
                        var len: Int
                        while (fis!!.read(buffer).also { len = it } != -1) {
                            os.write(buffer, 0, len)
                        }
                    }
                }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentDatabaseBackupBinding.inflate(inflater, container, false)

        binding.importDb?.apply {
            setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "application/*"
                }
                launcher.launch(intent)
            }
        }

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}