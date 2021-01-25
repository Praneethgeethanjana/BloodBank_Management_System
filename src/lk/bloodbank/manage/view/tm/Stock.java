package lk.bloodbank.manage.view.tm;

public class Stock {
    private String blood;
    private String whole;
    private String rbc;
    private String platelets;
    private String plasma;

    public Stock() {
    }

    public Stock(String blood, String whole, String rbc, String platelets, String plasma) {
        this.blood = blood;
        this.whole = whole;
        this.rbc = rbc;
        this.platelets = platelets;
        this.plasma = plasma;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getWhole() {
        return whole;
    }

    public void setWhole(String whole) {
        this.whole = whole;
    }

    public String getRbc() {
        return rbc;
    }

    public void setRbc(String rbc) {
        this.rbc = rbc;
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

    @Override
    public String toString() {
        return "Stock{" +
                "blood='" + blood + '\'' +
                ", whole='" + whole + '\'' +
                ", rbc='" + rbc + '\'' +
                ", platelets='" + platelets + '\'' +
                ", plasma='" + plasma + '\'' +
                '}';
    }
}
