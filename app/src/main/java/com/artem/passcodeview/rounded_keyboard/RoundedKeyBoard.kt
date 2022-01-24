package com.artem.passcodeview.rounded_keyboard
import android.content.Context
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.artem.passcodeview.databinding.LoyoutKeyboardPincodeBinding

class RoundedKeyBoard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LoyoutKeyboardPincodeBinding =
        LoyoutKeyboardPincodeBinding.inflate(LayoutInflater.from(context), this, true)

    private var onValueChangeListener: ((RoundedKeyBoardAction) -> Unit)? = null

    init {
        chooseBiometricImage()
        initViews()
        setupListeners()
    }

    private fun chooseBiometricImage(): Int {
        return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) RoundedKeyBoardAction.TOUCHID.value
        else 0
    }

    private fun initViews() = with(binding) {
        btnOne.text = resources.getString(RoundedKeyBoardAction.ONE.value)
        btnTwo.text = resources.getString(RoundedKeyBoardAction.TWO.value)
        btnThree.text = resources.getString(RoundedKeyBoardAction.THREE.value)
        btnFour.text = resources.getString(RoundedKeyBoardAction.FOUR.value)
        btnFive.text = resources.getString(RoundedKeyBoardAction.FIVE.value)
        btnSix.text = resources.getString(RoundedKeyBoardAction.SIX.value)
        btnSeven.text = resources.getString(RoundedKeyBoardAction.SEVEN.value)
        btnEight.text = resources.getString(RoundedKeyBoardAction.EIGHT.value)
        btnNine.text = resources.getString(RoundedKeyBoardAction.NINE.value)
        btnZero.text = resources.getString(RoundedKeyBoardAction.ZERO.value)
        btnPoint.setImageResource(chooseBiometricImage())
    }

    private fun setupListeners() = with(binding) {
        btnOne.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.ONE) }
        btnTwo.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.TWO) }
        btnThree.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.THREE) }
        btnFour.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.FOUR) }
        btnFive.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.FIVE) }
        btnSix.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.SIX) }
        btnSeven.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.SEVEN) }
        btnEight.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.EIGHT) }
        btnNine.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.NINE) }
        btnZero.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.ZERO) }
        btnPoint.setOnClickListener {
            if (chooseBiometricImage() == RoundedKeyBoardAction.TOUCHID.value) {
                sendKeyboardAction(RoundedKeyBoardAction.TOUCHID)
            } else RoundedKeyBoardAction.FACEID
        }
        btnBackspace.setOnClickListener { sendKeyboardAction(RoundedKeyBoardAction.BACKSPACE) }
    }

    private fun sendKeyboardAction(keyboardForPinCodeAction: RoundedKeyBoardAction) {
        onValueChangeListener?.invoke(keyboardForPinCodeAction)
    }

    fun setKeyboardClickListener(value: ((RoundedKeyBoardAction) -> Unit)?) {
        onValueChangeListener = value
    }

}