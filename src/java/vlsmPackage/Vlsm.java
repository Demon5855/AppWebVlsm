package vlsmPackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmont
 */
public class Vlsm {

    public List<String[]>[] Calcular(Ip ipm, Lan[] ls) {

    	@SuppressWarnings("unchecked")
    	List<String[]>[] impresion = new List[4];
    	impresion[0] = new ArrayList<>();
    	impresion[1] = new ArrayList<>();
    	impresion[2] = new ArrayList<>();
    	impresion[3] = new ArrayList<>();
        
        
        Red r = new Red(ls.clone());
        r.ordenar();

        Ip ipAux = new Ip(
                new Octeto(ipm.getOctetos()[0].transformarBinDec()), 
                new Octeto(ipm.getOctetos()[1].transformarBinDec()), 
                new Octeto(ipm.getOctetos()[2].transformarBinDec()), 
                new Octeto(ipm.getOctetos()[3].transformarBinDec()), 
                ipm.getMascara()).ipRed();
        
        Posicion p = new Posicion(ipAux.getMascara());

        int bol = 0;
        int l = 0;
        int esp = 0;
        String espaciado = "\t\t";

        List<String> proceso = new ArrayList<>();

        proceso.add(("\n") + (
                espaciado.repeat(esp)) + 
                (ipm.toString()) + ("\t") + 
                (ipm.toStringDecimal()) + 
                (" /") + (ipm.getMascara()));
        proceso.add(("\n") + (espaciado.repeat(esp)) + (ipAux.toString()) + ("\t") + (ipAux.toStringDecimal()) + (" /") + (ipAux.getMascara()));
        
        ipAux.aumMascara();
        while (r.getLans()[r.getLans().length - 1].getIp() == null) {
            if (bol == 1) {
                while (ipAux.getOctetos()[p.getOct()].getBits()[p.getPoct()].equals("1")) {
                    ipAux.getOctetos()[p.getOct()].modBit(p.getPoct());
                    ipAux.disMascara();
                    p.retroceder();
                }
                ipAux.getOctetos()[p.getOct()].modBit(p.getPoct());

                bol = 0;
            }
            if (r.getLans()[l].getMascara() == ipAux.getMascara()) {
                r.getLans()[l].setIp(new Ip(
                        new Octeto(ipAux.getOctetos()[0].transformarBinDec()), 
                        new Octeto(ipAux.getOctetos()[1].transformarBinDec()), 
                        new Octeto(ipAux.getOctetos()[2].transformarBinDec()), 
                        new Octeto(ipAux.getOctetos()[3].transformarBinDec()), 
                        ipAux.getMascara())
                );
                proceso.add(("\n") + (
                        espaciado.repeat(ipAux.getMascara() - ipm.getMascara())) + 
                        (ipAux.toString()) + ("\t") + 
                        (ipAux.toStringDecimal()) + 
                        (" /") + (ipAux.getMascara()) + 
                        (" ==> " + r.getLans()[l].getNombre())
                );
                l++;
                ipAux.getOctetos()[p.getOct()].modBit(p.getPoct());
                if (ipAux.getOctetos()[p.getOct()].getBits()[p.getPoct()].equals("0")) {
                    bol = 1;
                    ipAux.disMascara();
                    p.retroceder();
                }
            } else {
                proceso.add(("\n") + (
                        espaciado.repeat(ipAux.getMascara() - ipm.getMascara())) + 
                        (ipAux.toString()) + ("\t") + 
                        (ipAux.toStringDecimal()) + 
                        (" /") + (ipAux.getMascara()));
                ipAux.aumMascara();
                p.avanzar();
            }
        }

        impresion[0].add(new String[]{("IP BASE: \t") + (ipm.toStringDecimal()) + ("\t/") + (ipm.getMascara()) + ("\n")});

        for (int i = 0; i < r.getLans().length; i++) {
            impresion[1].add(new String[]{
                r.getLans()[i].getNombre(),
                String.valueOf(r.getLans()[i].getHosts()),
                String.valueOf(r.getLans()[i].getMascara())
            });
        }

        impresion[2].add(new String[]{"\n"});
        for (int i = 0; i < proceso.size(); i++) {
            impresion[2].add(new String[]{proceso.get(i)});
        }
        impresion[2].add(new String[]{"\n\n\n"});

        for (int i = 0; i < ls.length; i++) {
            impresion[3].add(new String[]{
                ls[i].getNombre(),
                ls[i].getIp().toStringDecimal(),
                ls[i].getIp().hostIniFin().toStringDecimal(),
                ls[i].getIp().hostFin().toStringDecimal(),
                ls[i].getIp().hostBro().hostBro().hostIniFin().toStringDecimal()
            });
        }

        return impresion;
    }
}
