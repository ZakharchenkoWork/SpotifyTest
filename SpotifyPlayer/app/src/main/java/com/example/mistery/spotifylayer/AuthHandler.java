package com.example.mistery.spotifylayer;

import android.app.Activity;
import android.content.Intent;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Created by MisterY on 29.08.2015.
 */
public class AuthHandler {
    private static AuthHandler instance = null;



    private AuthHandler(Activity mainActivity)
    {
        //Some magic builder,
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(AuthData.getDeveloperId(), // Data is always the same, so it is in AuthData Class
                AuthenticationResponse.Type.TOKEN,
                AuthData.getRedirectUri());
        builder.setScopes(new String[]{"user-read-private", "streaming"}); // setting for... streaming
        AuthenticationRequest request = builder.build(); // request for....


        //we will open the Login activity only on our MainActivity so...
        AuthenticationClient.openLoginActivity(mainActivity, AuthData.getRequestCode(), request); // ...opening login activity
    }

    //so we go throug the auth onse, and then, if we need it, we will use prepared instanse
    public static AuthHandler initialize(Activity mainActivity)
    {
        if(instance == null)
        {
            return instance = new AuthHandler(mainActivity);

        }
        else
        {
            return instance;
        }
    }

    public String handleRequestResults(int requestCode, int resultCode, Intent intent)
    {
        //checks that this is our response
        if (requestCode == AuthData.getRequestCode()) {

            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                //returns acces token once we are connected to Spotify
                return response.getAccessToken();
            }
        }
        //if something wrong - returns null
        return null;
    }
}
