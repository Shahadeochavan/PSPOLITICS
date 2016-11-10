package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class VotingCenterPojo {
    public String placeName;
    public String address;
    public String date;
    public String startTime;
    public String endTime;

    public VotingCenterPojo(String placeName, String address, String date, String startTime, String endTime) {
        this.placeName = placeName;
        this.address = address;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }
}