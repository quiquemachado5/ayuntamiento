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
import dao.EmailUtil;
import dao.Incidencia;
import dao.IncidenciaDAO;
import dao.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;

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
    private List<Departamento> departamentos;
    private String formulario;
    private List<String> estadosValidos;

    private String emailUsuario;

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
        if (usuario.getRol().equals("ADMIN")) {
            setIncidencias(dao.listarIncidencias());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setIncidencias(dao.listarIncidenciasPorUsuario(usuario));
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String redirigir() {
        DepartamentoDAO daoD = new DepartamentoDAO();
        setDepartamentos(daoD.listarDepartamentos());
        return SUCCESS;
    }

    public String preparaEdicion() {
        IncidenciaDAO dao = new IncidenciaDAO();
        DepartamentoDAO daoD = new DepartamentoDAO();

        // Cargar lista de departamentos para el <select>
        setDepartamentos(daoD.listarDepartamentos());

        // Obtener la incidencia actual
        Incidencia inci = dao.obtenerIncidenciaID(id);

        if (inci != null) {
            setId(inci.getId());
            setTitulo(inci.getTitulo());
            setDescripcion(inci.getDescripcion());
            setDepartamento(inci.getDepartamento());
            setUsuario(inci.getUsuario());
            setEstado(inci.getEstado());
            setFechaReporte(inci.getFechaReporte());
            // Calcular estados válidos según estado actual
            switch (inci.getEstado()) {
                case "ABIERTA":
                    setEstadosValidos(Arrays.asList("ABIERTA", "EN_PROCESO", "RESUELTA"));
                    break;
                case "EN_PROCESO":
                case "RESUELTA":
                    setEstadosValidos(Arrays.asList(inci.getEstado(), "CERRADA"));
                    break;

                case "CERRADA":
                default:
                    setEstadosValidos(Arrays.asList(inci.getEstado())); // solo el estado actual
                    break;
            }
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String crear() throws ParseException, MessagingException {
        IncidenciaDAO dao = new IncidenciaDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
        usuario = (Usuario) session.get("usuario");

        Date ahora = new Date(); // fecha y hora actual completa con milisegundos
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Convertimos a String con el formato deseado (sin milisegundos)
        String fechaStr = formato.format(ahora);
        // Parseamos el String otra vez a Date para quitar milisegundos
        Date fechaFormateada = formato.parse(fechaStr);

        Incidencia incidencia = new Incidencia(departamento, usuario, titulo, descripcion, estado, fechaFormateada);
        dao.crearIncidencia(incidencia);

        // Enviar correo de confirmación
        String asunto = "Confirmación del trámite: " + incidencia.getTitulo();
        String mensaje = "Su trámite ha sido creado correctamente con la siguiente descripción: " + incidencia.getDescripcion();

        setEmailUsuario(emailUsuario);
        EmailUtil.sendEmail(emailUsuario, asunto, mensaje);

        if (usuario.getRol().equals("ADMIN")) {
            setIncidencias(dao.listarIncidencias());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setIncidencias(dao.listarIncidenciasPorUsuario(usuario));
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String atras() {
        IncidenciaDAO dao = new IncidenciaDAO();
        setIncidencias(dao.listarIncidencias());
        return SUCCESS;
    }

    public String editar() throws ParseException {
        IncidenciaDAO dao = new IncidenciaDAO();
        Map<String, Object> session = ActionContext.getContext().getSession();
        usuario = (Usuario) session.get("usuario");

        Date ahora = new Date(); // fecha y hora actual completa con milisegundos
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Convertimos a String con el formato deseado (sin milisegundos)
        String fechaStr = formato.format(ahora);
        // Parseamos el String otra vez a Date para quitar milisegundos
        Date fechaFormateada = formato.parse(fechaStr);

        DepartamentoDAO daoD = new DepartamentoDAO();

        // Cargar lista de departamentos para el <select>
        setDepartamentos(daoD.listarDepartamentos());

        // Obtener la incidencia actual
        Incidencia inci = dao.obtenerIncidenciaID(id);
        inci.setTitulo(titulo);
        inci.setDepartamento(departamento);
        inci.setUsuario(usuario);
        inci.setDescripcion(descripcion);

        String estadoActual = inci.getEstado();

        // Validar transición correcta
        boolean valido = false;
        switch (estadoActual) {
            case "ABIERTA":
                valido = estado.equals("ABIERTA") || estado.equals("EN_PROCESO") || estado.equals("RESUELTA");
                break;
            case "EN_PROCESO":
            case "RESUELTA":
                valido = "CERRADA".equals(estado);

                break;
            case "CERRADA":
                valido = estado.equals("CERRADA");
                break;
        }

        if (!valido) {
            addActionError("Cambio de estado no permitido");
            return ERROR;
        }

        // Si es válido, actualizar estado
        inci.setEstado(estado);

        //setFechaReporte(fechaFormateada);
        System.out.println("////////////INCIDENCIA: " + inci.toString());

        dao.editarIncidencia(inci.getId(), titulo, departamento, usuario, descripcion, estado, fechaFormateada);

        if (usuario.getRol().equals("ADMIN")) {
            setIncidencias(dao.listarIncidencias());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setIncidencias(dao.listarIncidenciasPorUsuario(usuario));
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String borrar() {
        IncidenciaDAO dao = new IncidenciaDAO();
        Incidencia i = dao.obtenerIncidenciaID(id);
        dao.borrar(i);
        Map<String, Object> session = ActionContext.getContext().getSession();
        usuario = (Usuario) session.get("usuario");

        if (usuario.getRol().equals("ADMIN")) {
            setIncidencias(dao.listarIncidencias());
            return SUCCESS;
        }
        if (usuario.getRol().equals("CIUDADANO")) {
            setIncidencias(dao.listarIncidenciasPorUsuario(usuario));
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    public void validate() {
        //Validate para crear y editar
        if ("crear".equals(formulario)) {
            if (titulo == null || titulo.trim().isEmpty()) {
                addFieldError("titulo", getText("error.tituloIncidencia.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionIncidencia.required"));
            }
            if (emailUsuario == null || emailUsuario.trim().isEmpty()) {
                addFieldError("emailUsuario", getText("error.emailUsuarioTramite.required"));
            } else if (!emailUsuario.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                addFieldError("emailUsuario", "El formato del correo no es válido.");
            }
            DepartamentoDAO dao = new DepartamentoDAO();
            setDepartamentos(dao.listarDepartamentos());
        } else if ("editar".equals(getFormulario())) {
            if (titulo == null || titulo.trim().isEmpty()) {
                addFieldError("titulo", getText("error.tituloIncidencia.required"));
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                addFieldError("descripcion", getText("error.descripcionIncidencia.required"));
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

    /**
     * @return the estadosValidos
     */
    public List<String> getEstadosValidos() {
        return estadosValidos;
    }

    /**
     * @param estadosValidos the estadosValidos to set
     */
    public void setEstadosValidos(List<String> estadosValidos) {
        this.estadosValidos = estadosValidos;
    }

    /**
     * @return the emailUsuario
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * @param emailUsuario the emailUsuario to set
     */
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

}
