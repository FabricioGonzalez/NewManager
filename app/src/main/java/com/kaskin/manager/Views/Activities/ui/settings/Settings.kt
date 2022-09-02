package com.kaskin.manager.Views.Activities.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    private var isNightModeOn =
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) false
        else true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SettingsViewModel::class.java].also { settingsViewModel = it }

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val theme =
            if (isNightModeOn) resources.getString(R.string.theme_dark)
            else resources.getString(R.string.theme_light)

        binding.appThemeSwitch.text = theme
        binding.appThemeSwitch.isChecked = isNightModeOn

        binding.appThemeSwitch.setOnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.appThemeSwitch.text = resources.getString(R.string.theme_light)
                isNightModeOn = false

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.appThemeSwitch.text = resources.getString(R.string.theme_dark)
                isNightModeOn = true
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}