package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class NewsListPojo {
    public String name;
   public String date;
    public String time;
    public String informationofphots;
    public  int share;
   public int photoId,photsNews;

    public NewsListPojo(String name, String date,String time, int photoId,int photsNews,String informationofphots,int share) {
        this.name = name;
        this.date = date;
        this.time=time;
        this.share=share;
        this.photoId = photoId;
        this.photsNews=photsNews;
        this.informationofphots=informationofphots;
        this.informationofphots=informationofphots;

    }
}
