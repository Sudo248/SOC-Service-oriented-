package com.sudo248.soc.ui.activity.main.fragment.promotion

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc.databinding.FragmentPromotionBinding
import com.sudo248.soc.ui.activity.main.adapter.PromotionAdapter
import com.sudo248.soc.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding, PromotionViewModel>() {
    override val viewModel: PromotionViewModel by viewModels()

    private val adapter: PromotionAdapter by lazy {
        PromotionAdapter{
            viewModel.selectedPromotion = it
        }
    }

    override fun initView() {
        binding.rcvPromotion.adapter = adapter
        binding.txtAgree.setOnClickListener {
            viewModel.applyPromotion()
        }
        binding.refresh.setOnRefreshListener {
            viewModel.getAllPromotion()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.promotions.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = false
            adapter.submitList(it)
        }
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = requireContext(),
                    message = message
                )
            }
        }
    }
}