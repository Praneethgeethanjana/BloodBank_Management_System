package lk.bloodbank.manage.entity;

public class Order implements SuperEntity {
    private int oid;
    private String hid;
    private String date;

    public Order() {
    }

    public Order(int oid, String hid, String date) {
        this.oid = oid;
        this.hid = hid;
        this.date = date;
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

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", hid='" + hid + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
