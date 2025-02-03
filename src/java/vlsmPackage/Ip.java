package vlsmPackage;

/**
 *
 * @author dmont
 */
public class Ip {
    private Octeto[] octetos;
    private int mascara;

    public Ip(Octeto octa1, Octeto octa2, Octeto octa3, Octeto octa4, int mascara) {
        octetos = new Octeto[4];
        octetos[0] = octa1;
        octetos[1] = octa2;
        octetos[2] = octa3;
        octetos[3] = octa4;
        this.mascara = mascara;
    }
    
    public Ip(String octb1, String octb2, String octb3, String octb4, int mascara) {
        octetos = new Octeto[4];
        octetos[0] = new Octeto(octb1);
        octetos[1] = new Octeto(octb2);
        octetos[2] = new Octeto(octb3);
        octetos[3] = new Octeto(octb4);
        this.mascara = mascara;
    }
    
    public Ip(int octc1, int octc2, int octc3, int octc4, int mascara) {
        octetos = new Octeto[4];
        octetos[0] = new Octeto(octc1);
        octetos[1] = new Octeto(octc2);
        octetos[2] = new Octeto(octc3);
        octetos[3] = new Octeto(octc4);
        this.mascara = mascara;
    }
    
    public Ip(Octeto[] octetos, int mascara) {
        this.octetos = octetos;
        this.mascara = mascara;
    }
    
    public Ip(String ip, int mascara) {
        String[] octetos = ip.split("\\.");
        this.octetos = new Octeto[] {
        new Octeto(octetos[0]),
        new Octeto(octetos[1]),
        new Octeto(octetos[2]),
        new Octeto(octetos[3])
        };
        this.mascara = mascara;
    }

    public Octeto[] getOctetos() {
        return octetos;
    }

    public void setOctetos(Octeto[] octetos) {
        this.octetos = octetos;
    }

    public int getMascara() {
        return mascara;
    }

    public void setMascara(int mascara) {
        this.mascara = mascara;
    }
    
    public boolean aumMascara(){
        if (mascara < 32){
            mascara++;
            return true;
        }
        return false;
    }
    
    public boolean disMascara(){
        if (mascara > 0){
            mascara--;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String res = "";
        for (int i = 0; i < 4; i++) {
            res += octetos[i].toString();
            if (i < 3) res += ".";
        }
        return res;
    }
    
    public String toStringDecimal(){
        String res = "";
        for (int i = 0; i < 4; i++) {
            res += octetos[i].transformarBinDec();
            if (i < 3) res += ".";
        }
        return res;
    }
    
    public Ip hostBro(){
        Posicion p = new Posicion(mascara + 1);
        Ip aux = new Ip(octetos.clone(), mascara);
        while (p.getOct() != 3 || p.getPoct() != 7){
            aux.octetos[p.getOct()].modBit(p.getPoct());
            p.avanzar();
        }
        return aux;
    }
    
    public Ip hostIniFin(){
        Ip aux = new Ip(octetos.clone(), mascara);
        aux.octetos[3].modBit(7);
        return aux;
    }
    
    public Ip hostFin(){
        Ip aux = (new Ip(octetos.clone(), mascara)).hostBro();
        aux.hostIniFin();
        return aux;
    }
    
    public Ip ipRed(){
        Posicion p = new Posicion(mascara + 1);
        Ip aux = (new Ip(octetos.clone(), mascara).hostFin());
        while (p.getOct() != 3 || p.getPoct() != 7){
            aux.octetos[p.getOct()].modBit0(p.getPoct());
            p.avanzar();
        }
        return aux;
    }
}
