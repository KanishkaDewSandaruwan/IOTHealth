package com.example.iothealth;

public class DoctorDetails {

    String did, email, name, address, nic, mobile, gender, regnumber;

    public DoctorDetails() {
    }

    public DoctorDetails(String did, String email, String name, String address, String nic, String mobile, String gender, String regnumber) {
        this.did = did;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.gender = gender;
        this.regnumber = regnumber;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }
}
