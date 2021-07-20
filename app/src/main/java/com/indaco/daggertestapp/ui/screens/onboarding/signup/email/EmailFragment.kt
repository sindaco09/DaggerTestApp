package com.indaco.daggertestapp.ui.screens.onboarding.signup.email

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.databinding.FragmentEmailBinding
import com.indaco.daggertestapp.ui.base.BaseFragment
import com.indaco.daggertestapp.ui.screens.onboarding.signup.SignUpViewModel
import com.indaco.daggertestapp.util.Validator
import com.indaco.daggertestapp.util.Validator.inputIsValid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailFragment: BaseFragment<FragmentEmailBinding>(R.layout.fragment_email) {

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmailBinding.bind(view)

        init()
    }

    private fun init() {
        viewModel.emailInUse.observe(viewLifecycleOwner) {
            if (!it.first)
                goToPasswordScreen()
            else
                showError(it.second!!)
        }

        binding.email.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT && emailIsValid()) {
                viewModel.checkIfEmailInUse(binding.email.text.toString())
                true
            } else
                false
        }
    }

    private fun emailIsValid(): Boolean {
        return binding.emailLayout.inputIsValid(Validator.Type.EMAIL)
    }

    private fun showError(error: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_title)
            .setMessage(error)
            .show()
    }

    private fun goToPasswordScreen() {
        viewModel.changeState(SignUpViewModel.State.SHOW_PASSWORD)
    }
}