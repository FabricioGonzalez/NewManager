import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.DataSources.LoginDataSource
import com.kaskin.manager.Repositories.LoginRepository
import com.kaskin.manager.presentation.login.viewmodels.LoginViewModel

class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}