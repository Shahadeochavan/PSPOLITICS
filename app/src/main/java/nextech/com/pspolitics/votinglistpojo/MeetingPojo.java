package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 11/12/2016.
 */
public class MeetingPojo {
    public String meetingPersonName;
    public String meetingDate;
    public String meetingDay;
    public String meetingLocation;
    public String meetingStartTime;
    public  String meetingEndTime;

    public MeetingPojo(String meetingPersonName,String meetingDate,String meetingDay,String meetingLocation,String meetingStartTime,String meetingEndTime){
        this.meetingPersonName=meetingPersonName;
        this.meetingDate=meetingDate;
        this.meetingDay=meetingDay;
        this.meetingLocation=meetingLocation;
        this.meetingStartTime=meetingStartTime;
        this.meetingEndTime=meetingEndTime;
    }

}
