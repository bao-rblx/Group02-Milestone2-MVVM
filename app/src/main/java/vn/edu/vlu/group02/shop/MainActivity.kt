package vn.edu.vlu.group02.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import vn.edu.vlu.group02.shop.databinding.ActivityHomeBinding
import vn.edu.vlu.group02.shop.ui.cart.CartFragment
import vn.edu.vlu.group02.shop.ui.home.HomeFragment
import vn.edu.vlu.group02.shop.ui.profile.ProfileFragment

/**
 * Single-activity host. BottomNavigationView chuyển giữa 3 fragment.
 * ViewBinding — KHÔNG findViewById. onDestroy xoá binding.
 */
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            switchTo(HomeFragment())
            binding.bottomNav.selectedItemId = R.id.dest_home
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dest_home -> { switchTo(HomeFragment()); true }
                R.id.dest_cart -> { switchTo(CartFragment()); true }
                R.id.dest_profile -> { switchTo(ProfileFragment()); true }
                else -> false
            }
        }
    }

    private fun switchTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
