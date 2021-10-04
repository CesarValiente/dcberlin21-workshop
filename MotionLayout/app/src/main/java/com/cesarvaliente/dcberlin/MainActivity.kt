package com.cesarvaliente.dcberlin

import android.os.Bundle
import android.view.WindowInsets
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.cesarvaliente.dcberlin.databinding.ActivityMainBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Exoplayer
    private val VIDEO_URL =
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    private lateinit var player: SimpleExoPlayer
    private var keyboardToggle: Boolean = false
    private var chatToggle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize ExoPlayer
        player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player
        binding.playerControlView.player = player

    }

    override fun onStart() {
        super.onStart()

        // Start exoplayer
        prepareToPlayVideo()

        // Callback for chat toggle button
        setupChatButton()

        // Callback for keyboard opening
        watchKeyboardOpening()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        chatToggle = savedInstanceState.getBoolean(STATE_CHAT)
        player.playWhenReady = savedInstanceState.getBoolean(STATE_PLAY_WHEN_READY)
        player.seekTo(
            savedInstanceState.getInt(STATE_CURRENT_WINDOW_INDEX),
            savedInstanceState.getLong(STATE_CURRENT_POSITION)
        )
        player.prepare()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean(STATE_CHAT, chatToggle)
            putBoolean(STATE_PLAY_WHEN_READY, player.playWhenReady)
            putLong(STATE_CURRENT_POSITION, player.currentPosition)
            putInt(STATE_CURRENT_WINDOW_INDEX, player.currentWindowIndex)
        }

        super.onSaveInstanceState(outState)
    }


    private fun prepareToPlayVideo() {
        val mediaItem = MediaItem.fromUri(VIDEO_URL)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    private fun watchKeyboardOpening() {
        with(binding.mainContainer) {
            viewTreeObserver.addOnGlobalLayoutListener {
                val tempKeyboardToggle = if (Util.SDK_INT >= 30) {
                    rootWindowInsets.isVisible(WindowInsets.Type.ime())
                } else {
                    rootWindowInsets.systemWindowInsetBottom > 200
                }

                if (tempKeyboardToggle != keyboardToggle) {
                    keyboardToggle = tempKeyboardToggle
//                    changeLayout()
                }
            }
        }
    }

    private fun setupChatButton() {
        binding.chatEnableButton.setOnClickListener {
            chatToggle = !chatToggle
//            changeLayout()
        }
    }

    companion object {
        const val STATE_CHAT = "chatToggle"
        const val STATE_SPAN = "spanToggle"
        const val STATE_PLAY_WHEN_READY = "playerPlayWhenReady"
        const val STATE_CURRENT_POSITION = "playerPlaybackPosition"
        const val STATE_CURRENT_WINDOW_INDEX = "playerCurrentWindowIndex"
    }

}