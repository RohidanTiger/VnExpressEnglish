package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.models;

/**
 * Created by TigerStyle on 4/6/2017.
 */

public class NewsObject {
    private String title;
    private String image;
    private String link;

    public NewsObject(){

    }

    public NewsObject(String title, String image, String link) {
        this.title = title;
        this.image = image;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
