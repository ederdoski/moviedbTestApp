package com.edominguez.moviedb.core.common.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.edominguez.moviedb.R

class Dialogs(private val title: String, private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok_btn) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}