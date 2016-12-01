package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/21/2016.
 */
public class RallyPojo {

    private int id;
    private String startPlaceName;
    private String endPlaceName;
    private String rallyDate;
    private String startTime;
    private String endTime;
    private String dayRally;

  /*  public RallyPojo(String startPlaceName,String endPlaceName,String rallyDate,String startTime,String endTime,String dayRally){
        this.startPlaceName=startPlaceName;
        this.endPlaceName=endPlaceName;
        this.rallyDate=rallyDate;
        this.startTime=startTime;
        this.endTime=endTime;
        this.dayRally=dayRally;
    }*/
    public String getDayRally() {
        return dayRally;
    }

    public void setDayRally(String dayRally) {
        this.dayRally = dayRally;
    }

    public String getEndPlaceName() {
        return endPlaceName;
    }

    public void setEndPlaceName(String endPlaceName) {
        this.endPlaceName = endPlaceName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRallyDate() {
        return rallyDate;
    }

    public void setRallyDate(String rallyDate) {
        this.rallyDate = rallyDate;
    }

    public String getStartPlaceName() {
        return startPlaceName;
    }

    public void setStartPlaceName(String startPlaceName) {
        this.startPlaceName = startPlaceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}

