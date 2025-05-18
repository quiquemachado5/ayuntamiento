/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import dao.Departamento;
import dao.DepartamentoDAO;
import dao.EventoMunicipal;
import dao.EventoMunicipalDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author emdominguez
 */
public class eventomunicipalAction extends ActionSupport {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String lugar;
    private Boolean visible;

    private List<EventoMunicipal> eventos;
    private String formulario;

    public eventomunicipalAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String listar() {
        EventoMunicipalDAO dao = new EventoMunicipalDAO();
        eventos = dao.listarEventos();
        setEventos(eventos);
        return SUCCESS;
    }

    public String redirigir() {

        return SUCCESS;
    }

    public String crear() throws ParseException {
        EventoMunicipalDAO dao = new EventoMunicipalDAO();

        //CONVERSION FECHA
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(fecha);
        Date fechaConvertida = sdf.parse(fechaFormateada);

        EventoMunicipal e = new EventoMunicipal(titulo, descripcion, fechaConvertida, lugar, true);
        dao.crearEvento(e);
        eventos = dao.listarEventos();
        setEventos(eventos);
        return SUCCESS;
    }

    public String editar() throws ParseException {
        EventoMunicipalDAO dao = new EventoMunicipalDAO();
        setId(id);
        setTitulo(titulo);
        setDescripcion(descripcion);
        setLugar(lugar);
        setFecha(fecha);
        EventoMunicipal e = dao.obtenerEventoId(id);

        e.setTitulo(titulo);
        e.setDescripcion(descripcion);

        //CONVERSION FECHA
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(fecha);
        Date fechaConvertida = sdf.parse(fechaFormateada);

        e.setFecha(fechaConvertida);
        e.setLugar(lugar);
        dao.actualizarEvento(e);
        eventos = dao.listarEventos();
        setEventos(eventos);
        return SUCCESS;
    }

    public String borrar() {
        EventoMunicipalDAO dao = new EventoMunicipalDAO();
        dao.borrarEvento(dao.obtenerEventoId(id));
        setEventos(dao.listarEventos());
        return SUCCESS;
    }

    public String atras() {
        EventoMunicipalDAO dao = new EventoMunicipalDAO();
        setEventos(dao.listarEventos());
        return SUCCESS;
    }

    @Override
    public void validate() {
        if ("crear".equals(formulario)) {
            if (titulo == null || titulo.trim().isEmpty()) {
                addFieldError("titulo", getText("error.tituloEvento.required"));
            }
            if (lugar == null || lugar.trim().isEmpty()) {
                addFieldError("lugar", getText("error.lugarEvento.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionEvento.required"));
            }
            if (fecha == null) {
                addFieldError("fecha", getText("error.fechaEvento.required"));
            }
        } else if ("editar".equals(formulario)) {

            if (titulo == null || titulo.trim().isEmpty()) {
                addFieldError("titulo", getText("error.tituloEvento.required"));
            }
            if (lugar == null || lugar.trim().isEmpty()) {
                addFieldError("lugar", getText("error.lugarEvento.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionEvento.required"));
            }
            if (fecha == null) {
                addFieldError("fecha", getText("error.fechaEvento.required"));
            }

        }
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

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the eventos
     */
    public List<EventoMunicipal> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(List<EventoMunicipal> eventos) {
        this.eventos = eventos;
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
}
