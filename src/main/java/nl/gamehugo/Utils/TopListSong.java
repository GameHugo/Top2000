package nl.gamehugo.Utils;

public class TopListSong {

    private final int position;
    private final String song;
    private final String artist;
    private final int year;

    public TopListSong(int position, String song, String artist, int year) {
        this.position = position;
        this.song = song;
        this.artist = artist;
        this.year = year;
    }

    public int getPosition() {
        return position;
    }
    public String getSong() {
        return song;
    }
    public String getArtist() {
        return artist;
    }
    public int getYear() {
        return year;
    }

}
