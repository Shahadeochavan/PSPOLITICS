package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/25/2016.
 */
public class SocialWorkPojo {
    private int socialPhotos;
    private String socialInformation;
    private String socialDate;

    public String getSocialDate() {
        return socialDate;
    }

    public void setSocialDate(String socialDate) {
        this.socialDate = socialDate;
    }

    public String getSocialInformation() {
        return socialInformation;
    }

    public void setSocialInformation(String socialInformation) {
        this.socialInformation = socialInformation;
    }

    public int getSocialPhotos() {
        return socialPhotos;
    }

    public void setSocialPhotos(int socialPhotos) {
        this.socialPhotos = socialPhotos;
    }

    public SocialWorkPojo(int socialPhotos, String socialInformation, String socialDate){
        this.socialPhotos=socialPhotos;
        this.socialInformation=socialInformation;
        this.socialDate=socialDate;
    }
}
