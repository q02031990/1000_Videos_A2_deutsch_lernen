package tiengduc123.com.q8000videosdeutschlernen.Object;

import java.io.Serializable;

public class VideoObj implements Serializable {
    public String nameVideo;
    public String timeVideo;
    public String key;
    public String nameOfList;

    public VideoObj(String nameVideo, String timeVideo, String key, String nameOfList) {
        this.nameVideo = nameVideo;
        this.timeVideo = timeVideo;
        this.key = key;
        this.nameOfList = nameOfList;
    }
}

