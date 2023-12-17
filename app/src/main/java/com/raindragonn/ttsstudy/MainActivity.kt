package com.raindragonn.ttsstudy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val _ttsManager: TtsManager by lazy { TtsManagerImpl(baseContext) }

    private lateinit var _btnSpeech: Button
    private lateinit var _etText: EditText
    private lateinit var _tvPitch: TextView
    private lateinit var _sbPitch: SeekBar

    private val _text
        get() = _etText.text.toString()

    companion object {
        const val MAX_PITCH = 1
        const val PITCH_STEP = 0.1f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun onDestroy() {
        _ttsManager.release()
        super.onDestroy()
    }

    private fun initViews() {
        _etText = findViewById(R.id.et_text)
        _tvPitch = findViewById(R.id.tv_pitch)
        _sbPitch = findViewById(R.id.sb_pitch)
        _btnSpeech = findViewById(R.id.btn_speech)
        _btnSpeech.setOnClickListener(::onClickBtn)

        _sbPitch.max = (MAX_PITCH / PITCH_STEP).toInt()
        _sbPitch.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar ?: return
                val pitch = progress * PITCH_STEP
                val format = "%.1f".format(pitch)
                _tvPitch.text = "피치 조절 : $format"
                _ttsManager.setPitch(pitch)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun onClickBtn(view: View) {
        _ttsManager.speak(_text) {
            Log.d("DEV_LOG", "done")
        }
    }
}