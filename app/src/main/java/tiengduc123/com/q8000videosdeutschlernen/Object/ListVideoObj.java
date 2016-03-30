package tiengduc123.com.q8000videosdeutschlernen.Object;

/**
 * Created by Dell on 24/03/2016.
 */
public class ListVideoObj {
    int ID;
    String listID;
    String listName;
    String ImageKey;
    int CountVideo;



    public ListVideoObj(int ID, String listID, String listName, String ImageKey, int CountVideo) {
        this.ID = ID;
        this.listID = listID;
        this.listName = listName;
        this.ImageKey = ImageKey;
        this.CountVideo = CountVideo;
    }
    public int getCountVideo() {
        return CountVideo;
    }

    public void setCountVideo(int countVideo) {
        CountVideo = countVideo;
    }

    public String getImageKey() {
        return ImageKey;
    }

    public void setImageKey(String imageKey) {
        ImageKey = imageKey;
    }

    public int getID() {
        return ID;
    }

    public String getListID() {
        return listID;
    }

    public String getListName() {
        return listName;
    }
}
