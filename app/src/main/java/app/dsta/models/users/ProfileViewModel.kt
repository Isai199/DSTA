package app.dsta.models.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.dsta.room.DataBaseManager
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    fun saveUser(user: User){
        viewModelScope.launch{
            val userDao = DataBaseManager.instance.database.userDao()
            MyCoroutinesUser(userDao).save(user)
        }
    }


    fun deleteUser(user: User){
        viewModelScope.launch{
            val userDao = DataBaseManager.instance.database.userDao()
            MyCoroutinesUser(userDao).delete(user)
        }
    }


    val savedUsers = MutableLiveData<List<User>>()

    fun getUsers(){
        viewModelScope.launch{
            val userDao = DataBaseManager.instance.database.userDao()
            savedUsers.value = MyCoroutinesUser(userDao).getUser().value
        }
    }
}