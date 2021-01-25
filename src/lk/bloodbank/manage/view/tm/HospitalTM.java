package lk.bloodbank.manage.view.tm;

import javafx.scene.control.Button;

public class HospitalTM {
    private String HoID;
    private String HoName;
    private String HoAddress;
    private String HoType;
    private String HoContact;
    private Button btn;

    public HospitalTM() {
    }

    public HospitalTM(String hoID, String hoName, String hoAddress, String hoType, String hoContact, Button btn) {
        HoID = hoID;
        HoName = hoName;
        HoAddress = hoAddress;
        HoType = hoType;
        HoContact = hoContact;
        this.btn = btn;
    }


    public String getHoID() {
        return HoID;
    }

    public void setHoID(String hoID) {
        HoID = hoID;
    }

    public String getHoName() {
        return HoName;
    }

    public void setHoName(String hoName) {
        HoName = hoName;
    }

    public String getHoAddress() {
        return HoAddress;
    }

    public void setHoAddress(String hoAddress) {
        HoAddress = hoAddress;
    }

    public String getHoType() {
        return HoType;
    }

    public void setHoType(String hoType) {
        HoType = hoType;
    }

    public String getHoContact() {
        return HoContact;
    }

    public void setHoContact(String hoContact) {
        HoContact = hoContact;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "HospitalTM{" +
                "HoID='" + HoID + '\'' +
                ", HoName='" + HoName + '\'' +
                ", HoAddress='" + HoAddress + '\'' +
                ", HoType='" + HoType + '\'' +
                ", HoContact='" + HoContact + '\'' +
                ", btn=" + btn +
                '}';
    }
}
