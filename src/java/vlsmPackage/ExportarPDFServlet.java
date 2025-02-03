package vlsmPackage;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ExportarPDFServlet", urlPatterns = {"/ExportarPDFServlet"})
public class ExportarPDFServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración para la respuesta en PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Resultados_VLSM.pdf");

        Document document = new Document();
        try {
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            // Agregar el título al documento PDF
            Paragraph title1 = new Paragraph("Resultados del Cálculo VLSM");
            Paragraph title2 = new Paragraph("DIEGO MONTESDEOCA");
            title1.setAlignment(Element.ALIGN_CENTER);
            title2.setAlignment(Element.ALIGN_CENTER);
            document.add(title1);
            document.add(title2);

            // Obtener los resultados desde la sesión
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
			List<String[]>[] resultados = (List<String[]>[]) session.getAttribute("resultado");
            
            if (resultados == null) {
                // Maneja el caso en el que 'resultados' es null (por ejemplo, enviando un error o mostrando un mensaje)
                document.add(new Paragraph("No se encontraron resultados."));
            } else {
            	// Agregar contenido al PDF
                if (resultados[0] != null && !resultados[0].isEmpty()) {
                    for (String[] line : resultados[0]) {
                        String replace = line[0].replaceAll("\t", "    ");
                        document.add(new Paragraph(replace));
                    }
                    document.add(new Paragraph(" "));
                } else {
                    document.add(new Paragraph("No se encontraron resultados."));
                }
                
                if (resultados[1] != null && !resultados[1].isEmpty()) {
                    // Crear la tabla para mostrar los resultados
                    PdfPTable table = new PdfPTable(3); // 3 columnas: Nombre, Hosts, Mascara
                    table.setWidthPercentage(100);
                    table.addCell("Nombre");
                    table.addCell("Hosts");
                    table.addCell("Máscara");

                    // Agregar las filas a la tabla
                    for (String[] fila : resultados[1]) {
                        table.addCell(fila[0]); // Nombre
                        table.addCell(fila[1]); // Hosts
                        table.addCell(fila[2]); // Mascara
                    }

                    // Añadir la tabla al documento
                    document.add(table);
                } else {
                    // En caso de que no haya resultados
                    document.add(new Paragraph("No se encontraron resultados para mostrar."));
                }
                
                // Agregar contenido al PDF
                if (resultados[2] != null && !resultados[2].isEmpty()) {
                    Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);
                    if (resultados[2].size() > 12) font.setSize(10);
                    else if (resultados[2].size() > 20) font.setSize(7);
                    for (String[] line : resultados[2]) {
                        String replace = line[0].replaceAll("\t", "    ");
                        document.add(new Phrase(replace, font));
                    }
                } else {
                    document.add(new Paragraph("No se encontraron resultados."));
                }
                
                if (resultados[3] != null && !resultados[3].isEmpty()) {
                    // Crear la tabla para mostrar los resultados
                    PdfPTable table = new PdfPTable(5); // 5 columnas: Subred, Primera IP, Última IP, Broadcast, Máscara
                    table.setWidthPercentage(100);
                    table.addCell("Nombre");
                    table.addCell("Ip de Red");
                    table.addCell("Ip Minima");
                    table.addCell("Ip Maxima");
                    table.addCell("Broadcast");

                    // Agregar las filas a la tabla
                    for (String[] fila : resultados[3]) {
                        table.addCell(fila[0]); // Nombre
                        table.addCell(fila[1]); // Ip de Red
                        table.addCell(fila[2]); // Ip Minima
                        table.addCell(fila[3]); // Ip Maxima
                        table.addCell(fila[4]); // Broadcast
                    }

                    // Añadir la tabla al documento
                    document.add(table);
                } else {
                    // En caso de que no haya resultados
                    document.add(new Paragraph("No se encontraron resultados para mostrar."));
                }
            }

            // Cerrar el documento PDF
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error al generar el PDF: " + e.getMessage());
        }
    }
}
