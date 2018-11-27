package app.hackathon.suki.suki.models;

public class Reseller {
    String resellerName;
    String resellerStoreName;
    String resellerContact;
    String resellerBusinessNo;
    String resellerBusinessAddress;

    public Reseller(String resellerName, String resellerStoreName, String resellerContact, String resellerBusinessNo, String resellerBusinessAddress) {
        this.resellerName = resellerName;
        this.resellerStoreName = resellerStoreName;
        this.resellerContact = resellerContact;
        this.resellerBusinessNo = resellerBusinessNo;
        this.resellerBusinessAddress = resellerBusinessAddress;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getResellerStoreName() {
        return resellerStoreName;
    }

    public void setResellerStoreName(String resellerStoreName) {
        this.resellerStoreName = resellerStoreName;
    }

    public String getResellerContact() {
        return resellerContact;
    }

    public void setResellerContact(String resellerContact) {
        this.resellerContact = resellerContact;
    }

    public String getResellerBusinessNo() {
        return resellerBusinessNo;
    }

    public void setResellerBusinessNo(String resellerBusinessNo) {
        this.resellerBusinessNo = resellerBusinessNo;
    }

    public String getResellerBusinessAddress() {
        return resellerBusinessAddress;
    }

    public void setResellerBusinessAddress(String resellerBusinessAddress) {
        this.resellerBusinessAddress = resellerBusinessAddress;
    }
}
