package com.example.mistery.spotifylayer;

import android.app.Activity;
import android.util.Log;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

/**
 * Created by MisterY on 30.08.2015.
 */
public class PlayerHandler implements
        PlayerNotificationCallback, ConnectionStateCallback
{
    private Player  mPlayer;

        public void startPlayer(Activity activity, String accesToken)
        {
            Config playerConfig = new Config(activity, accesToken, AuthData.getDeveloperId());

            mPlayer = Spotify.getPlayer(playerConfig, this, playerInit());
        }

    private Player.InitializationObserver playerInit()
    {
        return new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                mPlayer.addConnectionStateCallback(PlayerHandler.this);
                mPlayer.addPlayerNotificationCallback(PlayerHandler.this);

            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("PlayerHandler", "Could not initialize player: " + throwable.getMessage());
            }
        };
    }
    public void playSong(String songID)
    {
        mPlayer.play(songID);
    }

    @Override
    public void onLoggedIn() {
        Log.d("PlayerHandler", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("PlayerHandler", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("PlayerHandler", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("PlayerHandler", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("PlayerHandler", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        Log.d("PlayerHandler", "Playback event received: " + eventType.name());
    }

    @Override
    public void onPlaybackError(ErrorType errorType, String errorDetails) {
        Log.d("PlayerHandler", "Playback error received: " + errorType.name());
    }

}
