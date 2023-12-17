package com.raindragonn.ttsstudy

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale

class MyTtsImpl(
    private val _context: Context
) : UtteranceProgressListener(), MyTts,
    TextToSpeech.OnInitListener {

    private var _tts: TextToSpeech? = null
    private var _onSpeakDoneListener: (() -> Unit)? = null
    private var _lastSpeakText: String? = null

    private val tts
        get() = _tts ?: TextToSpeech(_context, this)
            .also { _tts = it }

    override fun onStart(utteranceId: String?) {
        _lastSpeakText = null
    }

    override fun onDone(utteranceId: String?) {
        _onSpeakDoneListener?.invoke()
        _onSpeakDoneListener = null
        _lastSpeakText = null
    }

    override fun onError(utteranceId: String?) {
        Log.d("DEV_LOG", "MyTtsImpl.kt.onError: ")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            _tts?.language = Locale.getDefault()
            _lastSpeakText?.let { speakText ->
                tts.speak(speakText, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        } else {
            release()
        }
    }

    override fun setPitch(pitch: Float) {
        tts.setPitch(pitch)
    }

    override fun speak(speakText: String, doneListener: (() -> Unit)?) {
        doneListener?.let {
            _onSpeakDoneListener = it
            tts.setOnUtteranceProgressListener(this)
        }
        tts.speak(speakText, TextToSpeech.QUEUE_FLUSH, null, "").let { result ->
            if (result == TextToSpeech.ERROR) _lastSpeakText = speakText
        }
    }

    override fun release() {
        _tts?.apply {
            stop()
            shutdown()
        }
        _onSpeakDoneListener = null
        _tts = null
    }
}