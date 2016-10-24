package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/21/2016.
 */
public class RallyPojo {

    public int id;
   public String startPlaceName;
   public String endPlaceName;
    public String rallyDate;
   public String startTime;
   public String endTime;

    public RallyPojo(String startPlaceName,String endPlaceName,String rallyDate,String startTime,String endTime){
        this.startPlaceName=startPlaceName;
        this.endPlaceName=endPlaceName;
        this.rallyDate=rallyDate;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}

