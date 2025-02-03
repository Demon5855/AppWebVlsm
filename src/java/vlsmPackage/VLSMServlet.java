package vlsmPackage;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dmont
 */
@WebServlet(name = "VLSMServlet", urlPatterns = {"/VLSMServlet"})
public class VLSMServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String baseIP = request.getParameter("baseIP");
        String netmaskParam = request.getParameter("netmask");
        String numSubnetsParam = request.getParameter("numSubnets");

        // Validación de parámetros
        if (baseIP == null || baseIP.isEmpty() || netmaskParam == null || netmaskParam.isEmpty() || numSubnetsParam == null || numSubnetsParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros necesarios.");
            return;
        }

        int netmask = 0;
        int numSubnets = 0;

        try {
            // Convertir los parámetros a enteros
            netmask = Integer.parseInt(netmaskParam);
            numSubnets = Integer.parseInt(numSubnetsParam);
            
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros no válidos.");
            return;
        }

        // Crear el arreglo de Lan según el número de subredes
        Lan[] ls = new Lan[numSubnets];
        int nwan = 1;
        int nlan = 1;
        for (int i = 0; i < ls.length; i++) {
            String hostsRequiredParam = request.getParameter("hostsRequired" + (i + 1)); // Asegurarse de que el índice es correcto

            if (hostsRequiredParam == null || hostsRequiredParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros de hosts requeridos.");
                return;
            }
            try {
                ls[i] = new Lan(("LAN " + (nlan)), Integer.parseInt(hostsRequiredParam));
                if(Integer.parseInt(hostsRequiredParam) == 2){
                    ls[i].setNombre("WAN " + nwan++);
                }
                else nlan++;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hosts requeridos no válidos.");
                return;
            }
        }
        
        String[] saux = baseIP.split("\\.");
        int[] aux = new int[]{
            Integer.parseInt(saux[0]),
            Integer.parseInt(saux[1]),
            Integer.parseInt(saux[2]),
            Integer.parseInt(saux[3])
        };
        

        // Crear objeto Ip
        Ip ipm = new Ip(aux[0], aux[1], aux[2], aux[3], netmask);

        // Calcular el resultado
        Vlsm calculator = new Vlsm();
        List<String[]>[] resultado = calculator.Calcular(ipm, ls);

        // Pasar el resultado al JSP
        request.setAttribute("resultado", resultado);
        request.getRequestDispatcher("resultados.jsp").forward(request, response);
    }
}
