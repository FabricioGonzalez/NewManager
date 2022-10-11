package com.kaskin.manager.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private var _binding: FragmentSettingsBinding? = null

    private var isNightModeOn =
        AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO

    // This property is only valid between onCreateView and
    // onDestroyView.
    private
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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