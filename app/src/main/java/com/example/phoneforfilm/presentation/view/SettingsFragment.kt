package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.data.preferences.PreferencesHelper
import com.example.phoneforfilm.databinding.FragmentSettingsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Theme spinner
        val themes = resources.getStringArray(R.array.theme_entries)
        binding.spinnerTheme.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themes)

        lifecycleScope.launch {
            PreferencesHelper.getTheme(requireContext()).collectLatest { index ->
                binding.spinnerTheme.setSelection(index)
            }
        }

        binding.spinnerTheme.setOnItemSelectedListener { _, _, position, _ ->
            lifecycleScope.launch {
                PreferencesHelper.setTheme(requireContext(), position)
            }
            AppCompatDelegate.setDefaultNightMode(position)
        }

        // Locale spinner
        val locales = resources.getStringArray(R.array.locale_entries)
        binding.spinnerLocale.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locales)

        lifecycleScope.launch {
            PreferencesHelper.getLocale(requireContext()).collectLatest { loc ->
                val idx = locales.indexOf(loc).takeIf { it >= 0 } ?: 0
                binding.spinnerLocale.setSelection(idx)
            }
        }

        binding.spinnerLocale.setOnItemSelectedListener { _, _, position, _ ->
            val locale = locales[position]
            lifecycleScope.launch {
                PreferencesHelper.setLocale(requireContext(), locale)
            }
            val appLocale = Locale(locale)
            AppCompatDelegate.setApplicationLocales(androidx.collection.ArraySet(listOf(appLocale)))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
