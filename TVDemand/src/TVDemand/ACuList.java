package TVDemand;


public class ACuList {
    String fname;
    String lname;
    String email;
    String address;
    String district;
    String postalcode;
    String phone;
    String city;
    String country;
    String type;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ACuList(String fname, String lname, String email, String address, String district, String postalcode, String phone, String city, String country, String type) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.district = district;
        this.postalcode = postalcode;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.type = type;
    }
}
