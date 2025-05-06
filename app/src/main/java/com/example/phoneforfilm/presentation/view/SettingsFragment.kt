package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.phoneforfilm.R
import com.example.phoneforfilm.data.preferences.PreferencesHelper
import com.example.phoneforfilm.databinding.FragmentSettingsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themes = resources.getStringArray(R.array.theme_entries)
        binding.spinnerTheme.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themes)

        lifecycleScope.launch {
            PreferencesHelper.getTheme(requireContext()).collectLatest { index ->
                binding.spinnerTheme.setSelection(index)
            }
        }

        binding.spinnerTheme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?, position: Int, id: Long) {
                lifecycleScope.launch {
                    PreferencesHelper.setTheme(requireContext(), position)
                }
                AppCompatDelegate.setDefaultNightMode(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val locales = resources.getStringArray(R.array.locale_entries)
        binding.spinnerLocale.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, locales)

        lifecycleScope.launch {
            PreferencesHelper.getLocale(requireContext()).collectLatest { loc ->
                val idx = locales.indexOf(loc).takeIf { it >= 0 } ?: 0
                binding.spinnerLocale.setSelection(idx)
            }
        }
        binding.spinnerLocale.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val locale = locales[position]
                lifecycleScope.launch {
                    PreferencesHelper.setLocale(requireContext(), locale)
                }
                val appLocale = LocaleListCompat.forLanguageTags(locale)
                AppCompatDelegate.setApplicationLocales(appLocale)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
