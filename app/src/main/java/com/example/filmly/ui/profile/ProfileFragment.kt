package com.example.filmly.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.filmly.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewModel.showChangesToast.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, "Alterações salvas", Toast.LENGTH_SHORT).show()
                viewModel.doneShowingToast()
            }
        }

        view.btn_account_configuration.setOnClickListener {
            viewModel.navigateToAccountDialog()
        }

        viewModel.navigateToAccountDialog.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(R.id.action_profileFragment_to_accountDialogFragment)
                viewModel.doneNavigating()
            }
        }

        view.btn_search_configuration.setOnClickListener {
            val DAY_SELECTED = 0
            val WEEK_SELECTED = 1

            var positionSelected = 0

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Frequência de busca")
                .setNeutralButton("Cancelar") { dialog, which ->
                    // Respond to neutral button press
                }
                .setPositiveButton("Ok") { dialog, which ->
                    // Respond to positive button press
                    viewModel.updateSearchItem(positionSelected)
                    viewModel.showChangesToast()
                }
                // Single-choice items (initialized with checked item)
                .setSingleChoiceItems(viewModel.searchItems, viewModel.searchItemSelected.value!!) { dialog, which ->
                    // Respond to item chosen
                    positionSelected = which
                }
                .show()
        }

        view.btn_home_configuration.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeListsDialogFragment)
        }

        view.btn_yourLists_configuration.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_yourListsDialogFragment)
        }

        return view
    }
}