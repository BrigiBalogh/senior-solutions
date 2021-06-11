package ipaddress;

public class Ip {

    private String ipName;
    private int ordinalNumber;


    public Ip(String ipName, int ordinalNumber) {
        this.ipName = ipName;
        this.ordinalNumber = ordinalNumber;
    }

    public String getIpName() {
        return ipName;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }
}
