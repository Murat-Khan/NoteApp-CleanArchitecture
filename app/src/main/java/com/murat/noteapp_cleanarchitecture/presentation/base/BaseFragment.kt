package com.murat.noteapp_cleanarchitecture.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.murat.noteapp_cleanarchitecture.presentation.utils.UIState
import com.murat.noteapp_cleanarchitecture.presentation.utils.showToast

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupRequest()
        setupSubscribers()
        initClickListeners()
    }

    protected open fun initClickListeners() {}

    protected open fun setupSubscribers() {}

    protected open fun setupRequest() {}

    protected open fun initialize() {}


    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        uiState: ((UIState<T>) -> Unit)? = null,
        //onLoading: () -> Unit,
        onSuccess: (data: T) -> Unit

    ) {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectUIState.collect { state ->
                    uiState?.invoke(state)
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            requireActivity().showToast(state.message)

                        }
                        is UIState.Loading -> {

                        }
                        is UIState.Success -> {
                            onSuccess(state.data)

                        }
                    }
                }
            }
        }

    }

}