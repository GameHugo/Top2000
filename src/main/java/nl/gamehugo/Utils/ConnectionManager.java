package nl.gamehugo.Utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManager {
    private Song currentSong;

    public ConnectionManager() {
        update();
    }

    /**
     * Updates the api response
     */
    public boolean update() {
        try {
            System.out.println("Updating api response");
            URL url = new URL("https://www.nporadio2.nl/api/miniplayer/info?channel=npo-radio-2");
            URLConnection connection = url.openConnection();
            connection.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent()));
            JsonObject apiResponse = root.getAsJsonObject();
            currentSong = new Song(apiResponse);
            return true;
        } catch (IOException e) {
            System.out.println("Could not connect to URL");
            throw new RuntimeException(e);
        }
    }

    public Song getCurrentSong() {
        if (currentSong == null) {
            if(update()) {
                if(currentSong == null) {
                    System.out.println("Could not get current song");
                    return null;
                }
            }
        }
        if(currentSong.until() < System.currentTimeMillis()) {
            if(update()) {
                if(currentSong.until() < System.currentTimeMillis()) {
                    return null;
                }
            }
        }
        return currentSong;
    }
}
