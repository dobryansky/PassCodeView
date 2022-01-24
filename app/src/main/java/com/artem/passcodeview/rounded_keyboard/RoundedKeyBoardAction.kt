package com.artem.passcodeview.rounded_keyboard
import com.artem.passcodeview.R

sealed class RoundedKeyBoardAction(val value: Int) {
    object ONE : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_one)
    object TWO : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_two)
    object THREE : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_three)
    object FOUR : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_four)
    object FIVE : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_five)
    object SIX : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_six)
    object SEVEN : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_seven)
    object EIGHT : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_eight)
    object NINE : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_nine)
    object ZERO : RoundedKeyBoardAction(R.string.custom_keyboard_view_btn_zero)
    object BACKSPACE : RoundedKeyBoardAction(R.string.custom_keyboard_view_empty_string)
    object TOUCHID : RoundedKeyBoardAction(R.drawable.selector_ic_touch_id)
    object FACEID : RoundedKeyBoardAction(R.drawable.selector_ic_face_id)
}
