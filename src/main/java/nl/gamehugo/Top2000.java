package nl.gamehugo;

import nl.gamehugo.Utils.ConnectionManager;
import nl.gamehugo.Utils.Song;

public class Top2000 {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        new Thread(() -> {
            while (true) {
                Song currentSong = connectionManager.getCurrentSong();
                if(currentSong == null) {
                    System.out.println("Noting playing");
                } else {
                    System.out.println(currentSong.getArtist() + " - " + currentSong.getTitle());
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /*
        Song currentSong = connectionManager.getCurrentSong();
        System.out.println("Current song: " + currentSong.getArtist() + " - " + currentSong.getTitle());
        System.out.println("Cover: " + currentSong.getCoverURL());
        System.out.println("Until: " + currentSong.until());
         */
    }



}
