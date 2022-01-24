package com.artem.passcodeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.artem.passcodeview.databinding.ActivityMainBinding
import com.artem.passcodeview.rounded_keyboard.RoundedKeyBoardAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val CORRECT_PASSWORD = "1234"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pinResult.setTextColor(resources.getColor(R.color.white,theme))
        binding.pinResult.text="Set PIN"
        initKeyboardListener()



        binding.passwordView.setListener(object : ActionListener {
            override fun onCompleteInput(inputText: String) {
                if (CORRECT_PASSWORD == inputText) {
                    binding.passwordView.correctAnimation()

                } else {

                    binding.passwordView.incorrectAnimation()
                    binding.pinResult.setTextColor(resources.getColor(R.color.red_timer,theme))
                    binding.pinResult.text="Pin doesn't match"


                }
            }

            override fun onEndJudgeAnimation() {
                binding.passwordView.reset()
            }
        })


    }

    private fun initKeyboardListener() {
        binding.keyBoardView.setKeyboardClickListener { action ->

            binding.passwordView.appendInputText(resources.getString(action.value))
            if (action == RoundedKeyBoardAction.BACKSPACE) {
                binding.passwordView.removeInputText()
            }
        }
    }


    /* text_0.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_1.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_2.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_3.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_4.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_5.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_6.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_7.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_8.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_9.setOnClickListener { password_view.appendInputText((it as TextView).text.toString()) }
     text_d.setOnClickListener { password_view.removeInputText() }*/


}