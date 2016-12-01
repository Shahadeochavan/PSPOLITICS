package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/27/2016.
 */
public class VotingSchedulePojo {
    private String votingDay;
    private String votingDate;
    private String votingStartTime;
    private String votingEndTime;

    public String getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(String votingDate) {
        this.votingDate = votingDate;
    }

    public String getVotingDay() {
        return votingDay;
    }

    public void setVotingDay(String votingDay) {
        this.votingDay = votingDay;
    }

    public String getVotingEndTime() {
        return votingEndTime;
    }

    public void setVotingEndTime(String votingEndTime) {
        this.votingEndTime = votingEndTime;
    }

    public String getVotingStartTime() {
        return votingStartTime;
    }

    public void setVotingStartTime(String votingStartTime) {
        this.votingStartTime = votingStartTime;
    }

    public VotingSchedulePojo(String votingDay, String votingDate, String votingStartTime, String votingEndTime){
        this.votingDay=votingDay;
        this.votingDate=votingDate;
        this.votingStartTime=votingStartTime;
        this.votingEndTime=votingEndTime;
    }
}
