package service;

import dao.Conexion;
import java.net.URL;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator extends Conexion {

// ************ Usando recurso exteno con: URL y JasperReport  *******************
    public JasperPrint listarUsuarios() throws JRException, ClassNotFoundException {
        try {
            URL in = this.getClass().getResource("/reports/RegistroUsuario.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(in);
            JasperPrint reporteLleno = JasperFillManager.fillReport(jr, new HashMap(), this.conectar());
            return reporteLleno;
        } catch (Exception e) {
            System.out.println("Error en Jasper-RegistroUsuario " + e.getMessage());
        }
        return null;
    }

}
