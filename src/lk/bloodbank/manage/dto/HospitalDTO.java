package lk.bloodbank.manage.dto;

public class HospitalDTO {
     private String hid;
     private String name;
     private String address;
     private String type;
     private String contact;


    public HospitalDTO() {
    }

    public HospitalDTO(String hid, String name, String address, String type, String contact) {
        this.hid = hid;
        this.name = name;
        this.address = address;
        this.type = type;
        this.contact = contact;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
