package vn.edu.vlu.group02.shop.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import vn.edu.vlu.group02.shop.databinding.FragmentProfileBinding

/**
 * Màn Profile: thông tin người dùng (tĩnh qua ViewModel).
 * ViewBinding; observe(viewLifecycleOwner); _binding = null trong onDestroyView.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            binding.tvProfileName.text = profile.name
            binding.tvProfileEmail.text = profile.email
            binding.tvAddressSub.text = "${profile.addressCount} địa chỉ đã lưu"
            binding.tvOrdersSub.text = "Theo dõi ${profile.orderCount} đơn và lịch sử mua hàng"
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Đã đăng xuất (demo)", Toast.LENGTH_SHORT).show()
        }
        binding.btnEdit.setOnClickListener {
            Toast.makeText(requireContext(), "Chỉnh sửa hồ sơ (demo)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
