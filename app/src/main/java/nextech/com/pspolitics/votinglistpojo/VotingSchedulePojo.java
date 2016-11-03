package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/27/2016.
 */
public class VotingSchedulePojo {
    public String votingDay;
    public String votingDate;
    public String votingStartTime;
    public String votingEndTime;

    public VotingSchedulePojo(String votingDay,String votingDate,String votingStartTime,String votingEndTime){
        this.votingDay=votingDay;
        this.votingDate=votingDate;
        this.votingStartTime=votingStartTime;
        this.votingEndTime=votingEndTime;
    }
}
