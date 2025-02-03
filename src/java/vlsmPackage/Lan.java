package vlsmPackage;

/**
 *
 * @author dmont
 */
public class Lan {
    private String nombre;
    private int hosts;
    private int bits;
    private int mascara;
    private Ip ip;

    public Lan(String nombre, int hosts) {
        this.nombre = nombre;
        this.hosts = hosts;
        bits = (int) Math.ceil(Math.log(hosts + 2) / Math.log(2));
        mascara = 32 - bits;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getHosts() {
        return hosts;
    }

    public void setHosts(int hosts) {
        this.hosts = hosts;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getMascara() {
        return mascara;
    }

    public void setMascara(int mascara) {
        this.mascara = mascara;
    }

    public Ip getIp() {
        return ip;
    }

    public void setIp(Ip ip) {
        this.ip = ip;
    }
}
