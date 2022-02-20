package com.soongsil.example.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.soongsil.example.R

class MyCustomDialog(context: Context, myInterface:MyCustomDialogInterface) : Dialog(context) {

    private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var trainingEditView : EditText = findViewById(R.id.trainingEditView)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            val content = trainingEditView.text.toString()

            if(TextUtils.isEmpty(content)){
                Toast.makeText(context, "운동을 적어주세요.", Toast.LENGTH_SHORT).show()
            }else{
                myCustomDialogInterface.onOkButtonClicked(content)
                dismiss()
            }
        }

        cancelButton.setOnClickListener { dismiss() }
    }
}