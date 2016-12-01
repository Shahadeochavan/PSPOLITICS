package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class VotingCenterPojo {
    private String placeName;
    private String address;
    private String date;
    private String startTime;
    private String endTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public VotingCenterPojo(String placeName, String address, String date, String startTime, String endTime) {
        this.placeName = placeName;
        this.address = address;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }
}