package actions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import dao.Departamento;
import dao.DepartamentoDAO;
import dao.EventoMunicipalDAO;
import dao.Usuario;
import dao.UsuarioDAO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author emdominguez
 */
public class departamentoAction extends ActionSupport {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String telefonoContacto;
    private String emailContacto;
    private Set incidencias = new HashSet(0);
    private Set tramites = new HashSet(0);

    private List<Departamento> departamentos;
    private String formulario;

    public departamentoAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String listar() {
        DepartamentoDAO dao = new DepartamentoDAO();
        departamentos = dao.listarDepartamentos();
        setDepartamentos(departamentos);
        return SUCCESS;
    }

    public String redirigir() {
        return SUCCESS;
    }

    public String crear() {
        DepartamentoDAO dao = new DepartamentoDAO();
        Departamento d = new Departamento(nombre, descripcion, telefonoContacto, emailContacto, new HashSet<>(), new HashSet<>());
        dao.crearDepartamento(d);
        departamentos = dao.listarDepartamentos();
        setDepartamentos(departamentos);
        return SUCCESS;
    }

    public String editar() {
        DepartamentoDAO dao = new DepartamentoDAO();
        setNombre(nombre);
        setDescripcion(descripcion);
        setEmailContacto(emailContacto);
        setTelefonoContacto(telefonoContacto);
        Departamento d = dao.obtenerDepartamentoEmail(emailContacto);
        d.setNombre(nombre);
        d.setDescripcion(descripcion);
        d.setEmailContacto(emailContacto);
        d.setTelefonoContacto(telefonoContacto);
        d.setIncidencias(d.getIncidencias());
        d.setTramites(d.getTramites());
        dao.actualizarDepartamento(d);
        departamentos = dao.listarDepartamentos();
        setDepartamentos(departamentos);
        return SUCCESS;
    }

    public String borrar() {
        DepartamentoDAO dao = new DepartamentoDAO();
        setEmailContacto(emailContacto);
        Departamento d = dao.obtenerDepartamentoEmail(emailContacto);
        if (d == null) {
            // Manejar error, mostrar mensaje o redirigir
            addActionError("No se encontró el departamento con el email especificado.");
            return ERROR;
        } else {
            if (dao.tieneIncidenciasAsociadas(d.getId())) {
                departamentos = dao.listarDepartamentos();
                setDepartamentos(departamentos);
                addActionError("No se puede eliminar el departamento porque tiene incidencias asociadas.");
                return ERROR;
            } else {
                dao.borrarDepartamento(d.getId());
                departamentos = dao.listarDepartamentos();
                setDepartamentos(departamentos);
                return SUCCESS;
            }
        }
    }

    public String atras() {
        DepartamentoDAO dao = new DepartamentoDAO();
        setDepartamentos(dao.listarDepartamentos());
        return SUCCESS;
    }

    @Override
    public void validate() {
        if ("crear".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombreDepartamento.required"));
            }
            if (emailContacto == null || emailContacto.trim().isEmpty()) {
                addFieldError("emailContacto", getText("error.emailContactoDepartamento.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionDepartamento.required"));
            }
            if (telefonoContacto == null || telefonoContacto.trim().isEmpty()) {
                addFieldError("telefonoContacto", getText("error.telefonoContactoDepartamento.required"));
            }
        } else if ("editar".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombreDepartamento.required"));
            }
            if (emailContacto == null || emailContacto.trim().isEmpty()) {
                addFieldError("emailContacto", getText("error.emailContactoDepartamento.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionDepartamento.required"));
            }
            if (telefonoContacto == null || telefonoContacto.trim().isEmpty()) {
                addFieldError("telefonoContacto", getText("error.telefonoContactoDepartamento.required"));
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
     * @return the telefonoContacto
     */
    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    /**
     * @param telefonoContacto the telefonoContacto to set
     */
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    /**
     * @return the emailContacto
     */
    public String getEmailContacto() {
        return emailContacto;
    }

    /**
     * @param emailContacto the emailContacto to set
     */
    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    /**
     * @return the incidencias
     */
    public Set getIncidencias() {
        return incidencias;
    }

    /**
     * @param incidencias the incidencias to set
     */
    public void setIncidencias(Set incidencias) {
        this.incidencias = incidencias;
    }

    /**
     * @return the tramites
     */
    public Set getTramites() {
        return tramites;
    }

    /**
     * @param tramites the tramites to set
     */
    public void setTramites(Set tramites) {
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
