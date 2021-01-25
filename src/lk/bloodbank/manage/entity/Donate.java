package lk.bloodbank.manage.entity;

public class Donate implements SuperEntity{
    private int donateID;
    private String did;
    private String Name;
    private String blood;
    private String donation_Type;
    private String qty;
    private String date;
    private String time;


    public Donate() {
    }

    public Donate(int donateID, String did, String name, String blood, String donation_Type, String qty, String date, String time) {
        this.donateID = donateID;
        this.did = did;
        Name = name;
        this.blood = blood;
        this.donation_Type = donation_Type;
        this.qty = qty;
        this.date = date;
        this.time = time;
    }

    public int getDonateID() {
        return donateID;
    }

    public void setDonateID(int donateID) {
        this.donateID = donateID;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDonation_Type() {
        return donation_Type;
    }

    public void setDonation_Type(String donation_Type) {
        this.donation_Type = donation_Type;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Donate{" +
                "donateID=" + donateID +
                ", did='" + did + '\'' +
                ", Name='" + Name + '\'' +
                ", blood='" + blood + '\'' +
                ", donation_Type='" + donation_Type + '\'' +
                ", qty='" + qty + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
