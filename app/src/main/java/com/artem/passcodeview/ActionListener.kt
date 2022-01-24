package com.artem.passcodeview

interface ActionListener {
    fun onCompleteInput(inputText: String)
    fun onEndJudgeAnimation()
}