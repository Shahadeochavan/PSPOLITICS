package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 11/3/2016.
 */
public class PartyPojo {
    private String personName;
    private String personImage;
    private String partyName;
    private String partyImage;
    private String desgination;

    public String getAboutParty() {
        return aboutParty;
    }

    public void setAboutParty(String aboutParty) {
        this.aboutParty = aboutParty;
    }

    private String aboutParty;
    public String getPersonImage() {
        return personImage;
    }

    public void setPersonImage(String personImage) {
        this.personImage = personImage;
    }
    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

    public String getPartyImage() {
        return partyImage;
    }

    public void setPartyImage(String partyImage) {
        this.partyImage = partyImage;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


}
