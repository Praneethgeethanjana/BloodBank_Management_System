package lk.bloodbank.manage.view.tm;

import javafx.scene.control.Button;

import java.awt.*;

public class DonateTM {
       private int donateID;
       private String did;
       private String Name;
       private String blood;
       private String donation_Type;
       private String qty;
       private String date;
       private String time;
       private Button btn;

    public DonateTM() {
    }

    public DonateTM(int donateID, String did, String name, String blood, String donation_Type, String qty, String date, String time, Button btn) {
        this.donateID = donateID;
        this.did = did;
        Name = name;
        this.blood = blood;
        this.donation_Type = donation_Type;
        this.qty = qty;
        this.date = date;
        this.time = time;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
