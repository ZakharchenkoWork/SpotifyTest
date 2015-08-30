package com.example.mistery.spotifylayer;

/**
 * Created by MisterY on 29.08.2015.
 */
public class AuthData
{
    //Contains date for Authentification on the Spotify server.
    private static final String DEVELOPER_ID = "d409c8f9e267488d9ad7a32fe82b9dde";

    private static final int REQUEST_CODE = 1337;

    private static final String REDIRECT_URI = "spotify-player://callback";

    public static String getDeveloperId() {
        return DEVELOPER_ID;
    }


    public static int getRequestCode() {
        return REQUEST_CODE;

    }

    public static String getRedirectUri() {
        return REDIRECT_URI;
    }

}

