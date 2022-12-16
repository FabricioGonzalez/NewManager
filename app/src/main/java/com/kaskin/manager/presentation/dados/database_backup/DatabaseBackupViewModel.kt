package com.kaskin.manager.presentation.dados.database_backup

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DatabaseBackupViewModel @Inject constructor() : ViewModel() {

    private val _currentPath: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)
    val currentPath: StateFlow<Uri> = _currentPath

    fun setCurrentPath(path:Uri){
        _currentPath.tryEmit(path)
    }
}