package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class NewsListPojo {
    private String name;
    private String date;
    private String time;
    private String informationofphots;
    private  int share;
    private int photoId,photsNews;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformationofphots() {
        return informationofphots;
    }

    public void setInformationofphots(String informationofphots) {
        this.informationofphots = informationofphots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getPhotsNews() {
        return photsNews;
    }

    public void setPhotsNews(int photsNews) {
        this.photsNews = photsNews;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
