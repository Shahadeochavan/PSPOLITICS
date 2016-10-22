package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/21/2016.
 */
public class RallyPojo {

    private int id;
    private String startPlaceName;
    private String endPlaceName;
    private String date;
    private String startTime;
    private String endTime;


    public RallyPojo(int id,String startPlaceName,String endPlaceName,String date,String startTime,String endTime){
        this.id=id;
        this.startPlaceName=startPlaceName;
        this.endPlaceName=endPlaceName;
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public RallyPojo() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
