package tiengduc123.com.q1000videosB1deutschlernen.Object;

/**
 * Created by Dell on 26/03/2016.
 */
public class CategoryObj {
    int ID;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    String CategoryName;

    public CategoryObj(int ID, String categoryName) {
        this.ID = ID;
        CategoryName = categoryName;
    }
}
