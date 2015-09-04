package com.example.mistery.spotifylayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static AuthHandler authHandler = null;
    private ArrayList<String> songsList = new ArrayList<String>();
    private Player mPlayer;
    private PlayerHandler playerHandler;
    // Request code that will be used to verify if the result comes from correct activity
// Can be any integer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding a song from Spotify
        songsList.add("spotify:track:2TpxZ7JUBn3uw46aR7qd6V");

        authHandler = AuthHandler.initialize(this);
        playerHandler = new PlayerHandler();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        String accesToken = authHandler.handleRequestResults(requestCode, resultCode, intent);

        if (accesToken != null) {

            playerHandler.startPlayer(this, accesToken); //preparing the player
            playerHandler.playSong(songsList.get(0)); //playing song, it's in the array list in case we will want to make some playlist

        } else {
            Log.d("MainActivity", "Wrong acces token");
        }


    }


    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

}