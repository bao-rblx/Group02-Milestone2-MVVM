package vn.edu.vlu.group02.shop.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.edu.vlu.group02.shop.data.MockData
import vn.edu.vlu.group02.shop.data.UserProfile

/**
 * ViewModel cho màn Profile. Cung cấp thông tin người dùng tĩnh qua LiveData.
 * Không tham chiếu View/Activity/Fragment/Context.
 */
class ProfileViewModel : ViewModel() {

    private val _profile = MutableLiveData<UserProfile>(MockData.profile)
    val profile: LiveData<UserProfile> = _profile
}
