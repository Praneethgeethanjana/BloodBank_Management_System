package lk.bloodbank.manage.view.tm;


import javafx.scene.control.Button;

public class DonorTM {
    private String did;
    private String name;
    private String nic;
    private String gender;
    private String dob;
    private String address;
    private String blood;
    private String contact;
    private String email;
    private Button btn;

    public DonorTM() {
    }

    public DonorTM(String did, String name, String nic, String gender, String dob, String address, String blood, String contact, String email, Button btn) {
        this.did = did;
        this.name = name;
        this.nic = nic;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.blood = blood;
        this.contact = contact;
        this.email = email;
        this.btn = btn;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "DonorTM{" +
                "did='" + did + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", blood='" + blood + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", btn=" + btn +
                '}';
    }
}
