package lk.bloodbank.manage.view.tm;

import javafx.scene.control.Button;

public class BloodOrderTM {
    private String bloodGroup;
    private String wholeBlood;
    private String redBlood;
    private String platelets;
    private String plasma;
    private Button btn;

    public BloodOrderTM() {
    }

    public BloodOrderTM(String bloodGroup, String wholeBlood, String redBlood, String platelets, String plasma, Button btn) {
        this.setBloodGroup(bloodGroup);
        this.setWholeBlood(wholeBlood);
        this.setRedBlood(redBlood);
        this.setPlatelets(platelets);
        this.setPlasma(plasma);
        this.setBtn(btn);
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getWholeBlood() {
        return wholeBlood;
    }

    public void setWholeBlood(String wholeBlood) {
        this.wholeBlood = wholeBlood;
    }

    public String getRedBlood() {
        return redBlood;
    }

    public void setRedBlood(String redBlood) {
        this.redBlood = redBlood;
    }

    public String getPlatelets() {
        return platelets;
    }

    public void setPlatelets(String platelets) {
        this.platelets = platelets;
    }

    public String getPlasma() {
        return plasma;
    }

    public void setPlasma(String plasma) {
        this.plasma = plasma;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "BloodOrderTM{" +
                "bloodGroup='" + bloodGroup + '\'' +
                ", wholeBlood='" + wholeBlood + '\'' +
                ", redBlood='" + redBlood + '\'' +
                ", platelets='" + platelets + '\'' +
                ", plasma='" + plasma + '\'' +
                ", btn=" + btn +
                '}';
    }
}
