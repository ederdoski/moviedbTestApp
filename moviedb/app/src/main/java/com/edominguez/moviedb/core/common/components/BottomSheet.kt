package com.edominguez.moviedb.core.common.components

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheet {
    companion object {
        fun createBottomSheet(context: Context, layout:Int): BottomSheetDialog {
            val forgetMenuSheetDialog = BottomSheetDialog(context)
            forgetMenuSheetDialog.setContentView(layout)
            forgetMenuSheetDialog.setCancelable(true)
            forgetMenuSheetDialog.show()
            return forgetMenuSheetDialog
        }

    }

}
