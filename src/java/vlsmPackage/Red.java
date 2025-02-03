package vlsmPackage;

/**
 *
 * @author dmont
 */
public class Red {
    private Lan[] lans;
    
    public Red(Lan[] lans){
        this.lans = lans;
    }

    public Lan[] getLans() {
        return lans;
    }

    public void setLans(Lan[] lans) {
        this.lans = lans;
    }
    
    public void ordenar(){
        for (int i = 0; i < lans.length; i++) {
            for (int j = i + 1; j < lans.length; j++) {
                if (lans[i].getHosts() < lans[j].getHosts()){
                    Lan aux = lans[i];
                    lans[i] = lans[j];
                    lans[j] = aux;
                }
            }
        }
    }
    
}
