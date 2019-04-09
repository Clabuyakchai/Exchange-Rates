package com.example.exchangerates.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<V : BaseViewModel>: Fragment(), HasSupportFragmentInjector {
    @Inject
    protected lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: V
    protected open val isViewModelShared
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        initViewModel()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    protected abstract fun getViewModelClass(): KClass<V>

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private fun performDI() = AndroidSupportInjection.inject(this)

    private fun initViewModel() {
        viewModel = if (isViewModelShared) {
            ViewModelProviders.of(requireActivity(), viewModelFactory).get(getViewModelClass().java)
        } else {
            ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass().java)
        }
    }
}