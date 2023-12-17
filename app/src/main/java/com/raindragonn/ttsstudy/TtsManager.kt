package com.raindragonn.ttsstudy

interface TtsManager {
    fun setPitch(pitch: Float)
    fun speak(speakText: String, doneListener: (() -> Unit)? = null)
    fun release()
}