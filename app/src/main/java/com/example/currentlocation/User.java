package com.example.currentlocation;

public class User {

    private String fullname;
    private String dateofbirth;
    private String mobilenumber;
    private String gender;
    private String usertype;
    private String username;
    private String email2;
    private String password2;



    public User(String fullname, String dateofbirth, String mobilenumber,String gender,String usertype, String username, String email2, String password2)
    {
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
        this.mobilenumber = mobilenumber;
        this.gender=gender;
        this.usertype=usertype;
        this.username = username;
        this.email2 = email2;
        this.password2 = password2;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail2() {
        return email2;
    }

    public String getPassword2() {
        return password2;
    }

    public String getGender() {
        return gender;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
