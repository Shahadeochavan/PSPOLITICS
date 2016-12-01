package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 11/12/2016.
 */
public class MeetingPojo {
    private String meetingPersonName;
    private String meetingDate;
    private String meetingDay;
    private String meetingLocation;
    private String meetingStartTime;
    private  String meetingEndTime;

    public MeetingPojo(String meetingPersonName,String meetingDate,String meetingDay,String meetingLocation,String meetingStartTime,String meetingEndTime){
        this.meetingPersonName=meetingPersonName;
        this.meetingDate=meetingDate;
        this.meetingDay=meetingDay;
        this.meetingLocation=meetingLocation;
        this.meetingStartTime=meetingStartTime;
        this.meetingEndTime=meetingEndTime;
    }
    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(String meetingDay) {
        this.meetingDay = meetingDay;
    }

    public String getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(String meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getMeetingPersonName() {
        return meetingPersonName;
    }

    public void setMeetingPersonName(String meetingPersonName) {
        this.meetingPersonName = meetingPersonName;
    }

    public String getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(String meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

}
