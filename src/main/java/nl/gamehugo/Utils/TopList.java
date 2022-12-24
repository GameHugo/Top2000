package nl.gamehugo.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TopList {

    private final Map<Integer, TopListSong> topList = new HashMap<>();

    public TopList() {
        File cvsFile;
        URL resource = getClass().getClassLoader().getResource("2022.csv");
        if (resource == null) {
            throw new IllegalArgumentException("csv file not found!");
        } else {
            try {
                cvsFile = new File(resource.toURI());
            } catch (URISyntaxException e) {
                System.out.println("Could not find csv file");
                throw new RuntimeException(e);
            }
        }
        Scanner sc;
        try {
            sc = new Scanner(cvsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Source https://stksgarcia.github.io/posts/split-a-comma-separated-string/
            if(values.length != 4) {
                System.out.println("Invalid line: " + line);
                continue;
            }
            int position = Integer.parseInt(values[0]);
            topList.put(position, new TopListSong(position, values[1], values[2], Integer.parseInt(values[3])));
        }
        sc.close();
    }

    public TopListSong getSong(int position) {
        return topList.get(position);
    }

    public Map<Integer, TopListSong> getTopList() {
        return topList;
    }

    public Map<Integer, TopListSong> getTopList(int start, int end) {
        Map<Integer, TopListSong> topList = new HashMap<>();
        for(int i = start; i <= end; i++) {
            topList.put(i, this.topList.get(i));
        }
        return topList;
    }

    public int getPositionFromSong(Song song) {
        for(Map.Entry<Integer, TopListSong> entry : topList.entrySet()) {
            if(entry.getValue().getTitle().equalsIgnoreCase(song.getTitle()) && entry.getValue().getArtist().equalsIgnoreCase(song.getArtist())) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
