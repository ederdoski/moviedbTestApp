package com.edominguez.moviedb.core.common.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edominguez.moviedb.R
import com.edominguez.moviedb.databinding.SimpleBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.edominguez.moviedb.core.common.utils.Functions

class SimpleBottomSheetDialog(
    private val icon: Int? = null,
    private val title: String? = null,
    private val subTitle: String? = null,
    private val description: String? = null,
    private val txtBtn: String? = null,
    private val onClicklistener: OnCLickListener
) :
    BottomSheetDialogFragment() {
    private var _binding: SimpleBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SimpleBottomSheetBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.imageMainIcon.setImageResource(icon ?: R.drawable.ic_launcher_foreground)
        binding.txtTitle.text = title
        binding.txtSubTitle.text = subTitle
        Functions.setHtmlText( binding.txtDescription, description)
        binding.btn.text = txtBtn
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btn.setOnClickListener {
            onClicklistener.onConfirm()
            dismiss()
        }
    }


    interface OnCLickListener {
        fun onConfirm()
    }

}