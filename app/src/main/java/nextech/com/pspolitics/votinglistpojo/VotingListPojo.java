package nextech.com.pspolitics.votinglistpojo;

/**
 * Created by welcome on 10/17/2016.
 */
public class VotingListPojo {
    private String firstName;
    private String lastName;
    private String middleName;
    private  String phoneNumber;
    private String wardDetails;
    private int Id;
    public VotingListPojo(int Id,String firstName,String lastName,String middleName,String phoneNumber,String wardDetails){
        this.Id=Id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.middleName=middleName;
        this.phoneNumber=phoneNumber;
        this.wardDetails=wardDetails;

    }

    public VotingListPojo() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWardDetails() {
        return wardDetails;
    }

    public void setWardDetails(String wardDetails) {
        this.wardDetails = wardDetails;
    }
}
