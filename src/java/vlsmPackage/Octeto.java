package vlsmPackage;

/**
 *
 * @author dmont
 */
public class Octeto {
    private String[] bits;
    
    public Octeto(String valor){
        bits = valor.split("");
    }
    
    public Octeto(int valor){
        bits = transformarDecBin(valor).split("");
    }
    
    public Octeto(String[] bits){
        this.bits = bits;
    }

    public String[] getBits() {
        return bits;
    }

    public void setBits(String[] bits) {
        this.bits = bits;
    }
    
    public int transformarBinDec(){
        int inv = bits.length - 1;
        int res = 0;
        for (int i = 0; i < bits.length; i++) {
            res += Integer.parseInt(bits[i]) * Math.pow(2, inv);
            inv--;
        }
        return res;
    }
    
    public String transformarDecBin(int n){
        String res = "";
        while (n > 0){
            res = n%2 + res;
            n /= 2;
        }
        while(res.length() < 8){
            res = "0" + res;
        }
        return res;
    }
    
    public void modBit(int n){
        if (bits[n].equals("0")) bits[n] = "1";
        else bits[n] = "0";
    }
    
    public void modBit0(int n){
        bits[n] = "0";
    }
    
    @Override
    public String toString(){
        String res = "";
        for(String s: bits){
            res += s;
        }
        return res;
    }
    
    public String[] hostIni(){
        String[] aux = bits.clone();
        aux[7] = "1";
        return aux;
    }
}
