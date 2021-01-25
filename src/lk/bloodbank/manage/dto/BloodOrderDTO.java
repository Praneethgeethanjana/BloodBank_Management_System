package lk.bloodbank.manage.dto;

import java.util.ArrayList;

public class BloodOrderDTO {
     private int oid;
     private String hid;
     private String date;
     private String blood;
     private String whole_qty;
     private String rbc_qty;
     private String platelets_qty;
     private String plasma_qty;



    public BloodOrderDTO() {
    }

    public BloodOrderDTO(int oid, String hid, String date, String blood, String whole_qty, String rbc_qty, String platelets_qty, String plasma_qty) {
        this.oid = oid;
        this.hid = hid;
        this.date = date;
        this.blood = blood;
        this.whole_qty = whole_qty;
        this.rbc_qty = rbc_qty;
        this.platelets_qty = platelets_qty;
        this.plasma_qty = plasma_qty;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getWhole_qty() {
        return whole_qty;
    }

    public void setWhole_qty(String whole_qty) {
        this.whole_qty = whole_qty;
    }

    public String getRbc_qty() {
        return rbc_qty;
    }

    public void setRbc_qty(String rbc_qty) {
        this.rbc_qty = rbc_qty;
    }

    public String getPlatelets_qty() {
        return platelets_qty;
    }

    public void setPlatelets_qty(String platelets_qty) {
        this.platelets_qty = platelets_qty;
    }

    public String getPlasma_qty() {
        return plasma_qty;
    }

    public void setPlasma_qty(String plasma_qty) {
        this.plasma_qty = plasma_qty;
    }

    @Override
    public String toString() {
        return "BloodOrderDTO{" +
                "oid=" + oid +
                ", hid='" + hid + '\'' +
                ", date='" + date + '\'' +
                ", blood='" + blood + '\'' +
                ", whole_qty='" + whole_qty + '\'' +
                ", rbc_qty='" + rbc_qty + '\'' +
                ", platelets_qty='" + platelets_qty + '\'' +
                ", plasma_qty='" + plasma_qty + '\'' +
                '}';
    }
}
