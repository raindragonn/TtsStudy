package com.raindragonn.ttsstudy

interface MyTts {
    fun setPitch(pitch: Float)
    fun speak(speakText: String, doneListener: (() -> Unit)? = null)
    fun release()
}