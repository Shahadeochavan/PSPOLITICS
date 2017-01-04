package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/25/2016.
 */
public class SocialWorkPojo {
    private String socialPhotos;
    private String socialInformation;
    private String socialDate;

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    private String shortInfo;


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

    public String getSocialPhotos() {
        return socialPhotos;
    }

    public void setSocialPhotos(String socialPhotos) {
        this.socialPhotos = socialPhotos;
    }

}
