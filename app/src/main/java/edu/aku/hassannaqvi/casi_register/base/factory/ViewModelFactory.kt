package edu.aku.hassannaqvi.casi_register.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.aku.hassannaqvi.casi_register.base.repository.GeneralRepository
import edu.aku.hassannaqvi.casi_register.base.viewmodel.*

/*
* @author Ali Azaz Alam dt. 01.07.21
* */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: GeneralRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            modelClass.isAssignableFrom(FollowupViewModel::class.java) -> FollowupViewModel(repository) as T
            modelClass.isAssignableFrom(SplashscreenViewModel::class.java) -> SplashscreenViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    }

}