package com.example.iothealth;

public class PatientDetails {
    private String PID;
    private String DPID;
    private String email;
    private String name;
    private String address;
    private String nic;
    private String mobile;
    private String gender;
    private  String doctorEmail;
    private String password;

    public PatientDetails() {

    }

    public PatientDetails(String DPID, String PID, String email, String name, String address, String nic, String mobile, String gender, String doctorEmail) {
        this.DPID = DPID;
        this.PID = PID;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.gender = gender;
        this.doctorEmail = doctorEmail;
    }

    public PatientDetails(String PID, String email, String name, String address, String nic, String mobile, String gender, String password) {
        this.PID = PID;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.gender = gender;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDPID() {
        return DPID;
    }

    public void setDPID(String DPID) {
        this.DPID = DPID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
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
}
