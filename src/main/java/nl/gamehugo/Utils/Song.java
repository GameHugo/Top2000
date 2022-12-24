package nl.gamehugo.Utils;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Song {
    private final JsonObject apiResponse;
    int position = 0;

    /**
     * Constructor for Song
     * @param apiResponse JsonObject containing the API response
     */
    public Song(JsonObject apiResponse) {
        this.apiResponse = apiResponse;
    }

    /**
     * Constructor for Song
     * @param apiResponse JsonObject containing the API response
     * @param position Position of the song. 0 = current song, 1 = previous song, etc. (max 3)
     */
    public Song(JsonObject apiResponse, int position) {
        this.apiResponse = apiResponse;
        this.position = position;
    }

    /**
     * Gets the artist of the song
     * @return The artist of the song
     */
    public String getArtist() {
        return apiResponse.getAsJsonObject("data")
                .getAsJsonObject("radioTrackPlays")
                .getAsJsonArray("data")
                .get(0).getAsJsonObject().get("artist").getAsString();
    }

    /**
     * Gets the title of the song
     * @return The title of the song
     */
    public String getTitle() {
        return apiResponse.getAsJsonObject("data")
                .getAsJsonObject("radioTrackPlays")
                .getAsJsonArray("data")
                .get(0).getAsJsonObject().get("song").getAsString();
    }

    /**
     * Gets the cover of the song
     * @return The cover of the song
     */
    public String getCoverURL() {
        return apiResponse.getAsJsonObject("data")
                .getAsJsonObject("radioTrackPlays")
                .getAsJsonArray("data")
                .get(0).getAsJsonObject()
                .getAsJsonObject("radioTracks")
                .get("coverUrl").getAsString();
    }

    /**
     * Get the timestamp till the song is playing
     * @return timestamp in milliseconds
     */
    public long until() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = dateFormat.parse(apiResponse.getAsJsonObject("data")
                    .getAsJsonObject("radioTrackPlays")
                    .getAsJsonArray("data")
                    .get(0).getAsJsonObject().get("until").getAsString());
        } catch (ParseException e) {
            System.out.println("Could not parse date");
            return 0;
        }
        return date.getTime();
    }
}
