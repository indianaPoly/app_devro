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

class UpdateDialog(context: Context, updateDialogInterface: UpdateDialogInterface) : Dialog(context) {

    private var updateDialogInterface:UpdateDialogInterface = updateDialogInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var trainingEditView : EditText = findViewById(R.id.trainingEditView)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            val content = trainingEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(content)){
                Toast.makeText(context, "수정할 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            else{
                // 메모를 수정해줌
                updateDialogInterface.onOkButtonClicked(content)
                dismiss()
            }
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}