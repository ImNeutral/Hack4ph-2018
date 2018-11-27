package app.hackathon.suki.suki.models;

public class AccountDetails {
    String fullName;
    String gender;
    String birthDate;
    String completeAddress;
    String mobileNumber;

    public AccountDetails(String fullName, String gender, String birthDate, String completeAddress, String mobileNumber) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.completeAddress = completeAddress;
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
