package vlsmPackage;

/**
 *
 * @author dmont
 */
public class Posicion {
    private int oct;
    private int poct;
    
    public Posicion(){
        oct = 0;
        poct = 0;
    }
    
    public Posicion(int oct, int poct){
        this.oct = oct;
        this.poct = poct;
    }
    
    public Posicion(int mascara){
        oct = mascara / 8;
        poct = mascara % 8 - 1;
        if (poct == -1) poct = 0;
    }

    public int getOct() {
        return oct;
    }

    public void setOct(int oct) {
        this.oct = oct;
    }

    public int getPoct() {
        return poct;
    }

    public void setPoct(int poct) {
        this.poct = poct;
    }
    
    public void avanzar(){
        poct++;
        if (poct == 8){
            poct = 0;
            oct++;
        }
        if(oct == 4){
            oct = 0;
        }
    }
    
    public void retroceder(){
        poct--;
        if (poct == -1){
            poct = 7;
            oct--;
        }
        if(oct == -1){
            oct = 3;
        }
    }
    
}
