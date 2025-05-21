/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.Cita;
import dao.CitaDAO;
import dao.Departamento;
import dao.DepartamentoDAO;
import dao.Incidencia;
import dao.IncidenciaDAO;
import dao.Tramite;
import dao.TramiteDAO;
import dao.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.awt.Color;

/**
 *
 * @author emdominguez
 */
public class citasAction extends ActionSupport {

    private Integer id;
    private Tramite tramite;
    private Usuario usuario;
    private Date fecha;
    private Date hora;
    private String estado;
    private String observaciones;

    private List<Cita> citas;
    private String formulario;
    private List<Tramite> tramites;
    private List<String> estadosValidos;

    private InputStream pdfStream;

    public String generarPDF() throws Exception {
        CitaDAO dao = new CitaDAO();
        Cita cita = dao.obtenerCitaPorId(id);

        if (cita == null) {
            addActionError("No se encontró la cita.");
            return ERROR;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 50, 50, 80, 50); // márgenes
        PdfWriter.getInstance(document, baos);

        document.open();

        // 1. Encabezado - Título
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(148, 4, 68)); // color #940444
        Paragraph titulo = new Paragraph("Resumen de Cita", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Línea separadora
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(new Color(148, 4, 68));
        ls.setLineWidth(1);
        document.add(new Chunk(ls));

        document.add(Chunk.NEWLINE);

        // 2. Información del emisor (puedes personalizar)
        Font fontEmisor = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
        Paragraph emisor = new Paragraph("Centro de Atención - Dirección: Plaza Nueva 1, Sevilla\nTel: 954 123 456 - Email: info@aytosevilla.es", fontEmisor);
        emisor.setAlignment(Element.ALIGN_CENTER);
        document.add(emisor);

        document.add(Chunk.NEWLINE);

        // 3. Tabla con detalles de la cita
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] columnWidths = {2f, 4f};
        table.setWidths(columnWidths);

        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new Color(148, 4, 68));
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);

        // Método auxiliar para crear celdas con estilo

        table.addCell(createCell("Trámite:", fontHeader, true));
        table.addCell(createCell(cita.getTramite().getNombre(), fontBody, false));
        table.addCell(createCell("Usuario:", fontHeader, true));
        table.addCell(createCell(cita.getUsuario().getNombre(), fontBody, false));
        table.addCell(createCell("Fecha:", fontHeader, true));
        table.addCell(createCell(cita.getFecha().toString(), fontBody, false));
        table.addCell(createCell("Hora:", fontHeader, true));
        table.addCell(createCell(cita.getHora().toString(), fontBody, false));
        table.addCell(createCell("Estado:", fontHeader, true));
        table.addCell(createCell(cita.getEstado(), fontBody, false));
        table.addCell(createCell("Observaciones:", fontHeader, true));
        table.addCell(createCell(cita.getObservaciones() != null ? cita.getObservaciones() : "-", fontBody, false));

        document.add(table);

        // 4. Nota o instrucción final
        Font fontNota = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, Color.DARK_GRAY);
        Paragraph nota = new Paragraph("Por favor, llegue 10 minutos antes de la cita. En caso de necesitar reprogramar, contacte con nosotros con al menos 24 horas de antelación.", fontNota);
        nota.setAlignment(Element.ALIGN_LEFT);
        document.add(nota);

        // 5. Firma simulada y fecha de generación
        document.add(Chunk.NEWLINE);
        Font fontFirma = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, new Color(100, 100, 100));
        Paragraph firma = new Paragraph("Generado por Centro de Atención del Ayuntamiento de Sevilla", fontFirma);
        firma.setAlignment(Element.ALIGN_CENTER);
        document.add(firma);

        String fechaGeneracion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        Paragraph fecha = new Paragraph("Fecha de generación: " + fechaGeneracion, fontFirma);
        fecha.setAlignment(Element.ALIGN_CENTER);
        document.add(fecha);

        document.close();

        setPdfStream(new ByteArrayInputStream(baos.toByteArray()));

        return "pdf";
    }
    
    PdfPCell createCell(String text, Font font, boolean isHeader){
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.setPadding(8);
            if (isHeader) {
                cell.setBackgroundColor(new Color(224, 224, 224)); // gris claro para encabezados
            } else {
                cell.setBackgroundColor(Color.WHITE);
            }
            cell.setBorderColor(new Color(148, 4, 68));
            return cell;
        }

    public citasAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String listar() {
        CitaDAO dao = new CitaDAO();
        setCitas(dao.listarCitas());
        return SUCCESS;
    }

    public String atras() {
        CitaDAO dao = new CitaDAO();
        setCitas(dao.listarCitas());
        return SUCCESS;
    }

    public String redirigir() {
        TramiteDAO dao = new TramiteDAO();
        setTramites(dao.listarTramites());
        return SUCCESS;
    }

    public String crear() throws ParseException {
        CitaDAO dao = new CitaDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
        usuario = (Usuario) session.get("usuario");
        // Fecha y hora actual completas
        Date ahora = new Date();

        // 1) Fecha solo (yyyy-MM-dd) con hora 00:00:00
        SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = sdfFecha.format(ahora); // "2025-05-20"
        Date fecha = sdfFecha.parse(fechaStr);

        // 2) Hora solo (HH:mm:ss) con fecha 1970-01-01
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
        String horaStr = sdfHora.format(ahora); // "17:57:10"
        Date hora = sdfHora.parse(horaStr);

        Cita cita = new Cita(tramite, usuario, fecha, hora, estado, observaciones);
        dao.crearCita(cita);
        setCitas(dao.listarCitas());
        return SUCCESS;
    }

    public String preparaEdicion() {
        CitaDAO dao = new CitaDAO();
        TramiteDAO daoD = new TramiteDAO();

        // Cargar lista de departamentos para el <select>
        setTramites(daoD.listarTramites());

        // Obtener la citas actual
        Cita c = dao.obtenerCitaPorId(id);

        if (c != null) {
            setTramite(c.getTramite());
            setUsuario(c.getUsuario());
            setFecha(c.getFecha());
            setHora(c.getHora());
            setEstado(c.getEstado());
            setObservaciones(c.getObservaciones());
            // Calcular estados válidos según estado actual
            switch (c.getEstado()) {
                case "PENDIENTE":
                    estadosValidos = Arrays.asList("PENDIENTE", "ACEPTADA", "RECHAZADA");
                    break;
                case "ACEPTADA":
                case "RECHAZADA":
                    estadosValidos = Arrays.asList(c.getEstado(), "ATENDIDA");
                    break;

                case "ATENDIDA":
                default:
                    estadosValidos = Arrays.asList(c.getEstado()); // solo el estado actual
                    break;
            }
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String editar() throws ParseException {
        TramiteDAO daoD = new TramiteDAO();
        CitaDAO dao = new CitaDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
        usuario = (Usuario) session.get("usuario");

        if (id == null) {
            addActionError("ID de la cita no proporcionado");
            return ERROR;
        }

        Cita c = dao.obtenerCitaPorId(id);

        if (c == null) {
            addActionError("No se encontró la cita a editar");
            return ERROR;
        }

        String estadoActual = c.getEstado();

        // Validar transición correcta
        boolean valido = false;
        switch (estadoActual) {
            case "PENDIENTE":
                valido = estado.equals("PENDIENTE") || estado.equals("ACEPTADA") || estado.equals("RECHAZADA");
                break;
            case "ACEPTADA":
            case "RECHAZADA":
                valido = "ATENDIDA".equals(estado);

                break;
            case "ATENDIDA":
                valido = estado.equals("ATENDIDA");
                break;
        }

        if (!valido) {
            addActionError("Cambio de estado no permitido");
            return ERROR;
        }

        // Si es válido, actualizar estado
        c.setEstado(estado);
        dao.actualizarCita(c);

        setTramites(daoD.listarTramites());

        setCitas(dao.listarCitas());
        return SUCCESS;
    }

    public String borrar() {
        CitaDAO dao = new CitaDAO();
        Cita c = dao.obtenerCitaPorId(id);
        dao.eliminarCita(c);
        setCitas(dao.listarCitas());
        return SUCCESS;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    // Añade getters y setters para estadosValidos
    public List<String> getEstadosValidos() {
        return estadosValidos;
    }

    public void setEstadosValidos(List<String> estadosValidos) {
        this.estadosValidos = estadosValidos;
    }

    /**
     * @return the tramite
     */
    public Tramite getTramite() {
        return tramite;
    }

    /**
     * @param tramite the tramite to set
     */
    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public Date getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the citas
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    /**
     * @return the formulario
     */
    public String getFormulario() {
        return formulario;
    }

    /**
     * @param formulario the formulario to set
     */
    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    /**
     * @return the tramites
     */
    public List<Tramite> getTramites() {
        return tramites;
    }

    /**
     * @param tramites the tramites to set
     */
    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    /**
     * @return the pdfStream
     */
    public InputStream getPdfStream() {
        return pdfStream;
    }

    /**
     * @param pdfStream the pdfStream to set
     */
    public void setPdfStream(InputStream pdfStream) {
        this.pdfStream = pdfStream;
    }

}
