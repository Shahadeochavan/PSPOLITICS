package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 11/3/2016.
 */
public class PartyPojo {
    public String personName;
    public int personImage;
    public String partyName;
    public int partyImage;
    public String desgination;
    public PartyPojo(String personName,int personImage, String partyName,int partyImage,String desgination){
        this.personName=personName;
        this.personImage=personImage;
        this.partyName=partyName;
        this.partyImage=partyImage;
        this.desgination=desgination;
    }

}
