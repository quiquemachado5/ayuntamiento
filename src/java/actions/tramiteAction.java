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
import dao.Departamento;
import dao.DepartamentoDAO;
import dao.Incidencia;
import dao.IncidenciaDAO;
import dao.Tramite;
import dao.TramiteDAO;
import dao.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author emdominguez
 */
public class tramiteAction extends ActionSupport {

    private Integer id;
    private Departamento departamento;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Set citas = new HashSet(0);

    private List<Tramite> tramites;
    private List<Departamento> departamentos;
    private String formulario;

    public tramiteAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String listar() {
        TramiteDAO dao = new TramiteDAO();
        setTramites(dao.listarTramites());
        return SUCCESS;
    }

    public String crear() {
        TramiteDAO dao = new TramiteDAO();
        Tramite tramite = new Tramite(departamento, nombre, descripcion, activo, new HashSet(0));
        dao.crearTramite(tramite);
        setTramites(dao.listarTramites());
        return SUCCESS;
    }

    public String redirigir() {
        DepartamentoDAO daoD = new DepartamentoDAO();
        setDepartamentos(daoD.listarDepartamentos());
        return SUCCESS;
    }

    public String preparaEdicion() {
        TramiteDAO dao = new TramiteDAO();
        DepartamentoDAO daoD = new DepartamentoDAO();
        // Cargar lista de departamentos para el <select>
        setDepartamentos(daoD.listarDepartamentos());
        // Obtener el tramite actual
        Tramite t = dao.obtenerTramitePorId(id);

        if (t != null) {
            setId(t.getId());
            setNombre(t.getNombre());
            setDescripcion(t.getDescripcion());
            setActivo(t.getActivo());
            setDepartamento(t.getDepartamento());
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String editar() {
        TramiteDAO dao = new TramiteDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
        Usuario usuario = (Usuario) session.get("usuario");

        Tramite tramite = dao.obtenerTramitePorId(id);
        tramite.setNombre(nombre);
        tramite.setDescripcion(descripcion);
        tramite.setActivo(activo);
        tramite.setId(id);
        tramite.setDepartamento(departamento);

        dao.actualizarTramite(tramite);

        if (usuario.getRol().equals("ADMIN")) {
            setTramites(dao.listarTramites());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setTramites(dao.listarTramites());
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String atras() {
        TramiteDAO dao = new TramiteDAO();
        setTramites(dao.listarTramites());
        return SUCCESS;
    }
    
    public String borrar(){
        TramiteDAO dao = new TramiteDAO();
        Tramite t = dao.obtenerTramitePorId(id);
        dao.eliminarTramite(t);
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        Usuario usuario = (Usuario) session.get("usuario");

        if (usuario.getRol().equals("ADMIN")) {
            setTramites(dao.listarTramites());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setTramites(dao.listarTramites());
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    public void validate() {
        //Validate para crear y editar
        if ("crear".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombreTramite.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionTramite.required"));
            }
            DepartamentoDAO dao = new DepartamentoDAO();
            setDepartamentos(dao.listarDepartamentos());
        } else if ("editar".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombreTramite.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionTramite.required"));
            }
            DepartamentoDAO dao = new DepartamentoDAO();
            setDepartamentos(dao.listarDepartamentos());
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the activo
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the citas
     */
    public Set getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(Set citas) {
        this.citas = citas;
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
     * @return the departamentos
     */
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    /**
     * @param departamentos the departamentos to set
     */
    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
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
