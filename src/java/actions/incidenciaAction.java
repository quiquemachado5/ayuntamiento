/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.Departamento;
import dao.DepartamentoDAO;
import dao.Incidencia;
import dao.IncidenciaDAO;
import dao.Usuario;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emdominguez
 */
public class incidenciaAction extends ActionSupport {

    private Integer id;
    private Departamento departamento;
    private Usuario usuario;
    private String titulo;
    private String descripcion;
    private String estado;
    private Date fechaReporte;

    private List<Incidencia> incidencias;

    public incidenciaAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String listar() {
        IncidenciaDAO dao = new IncidenciaDAO();
         // Obtener el usuario de sesión
        Map<String, Object> session = ActionContext.getContext().getSession();
        Usuario usuario = (Usuario) session.get("usuario");
        if(usuario.getRol().equals("ADMIN")){
             setIncidencias(dao.listarIncidencias());
             return SUCCESS;
        }else{
            setIncidencias(dao.listarIncidenciasPorUsuario(usuario));
            return SUCCESS;
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
     * @return the departamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
     * @return the fechaReporte
     */
    public Date getFechaReporte() {
        return fechaReporte;
    }

    /**
     * @param fechaReporte the fechaReporte to set
     */
    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    /**
     * @return the incidencias
     */
    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    /**
     * @param incidencias the incidencias to set
     */
    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

}
