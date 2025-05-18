/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.Usuario;
import dao.UsuarioDAO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author emdominguez
 */
public class usuarioAction extends ActionSupport {

    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private String rol;
    private Set incidencias = new HashSet(0);
    private Set citas = new HashSet(0);

    private String emailLogin;
    private String passwordLogin;

    private String formulario;

    private List<Usuario> usuarios;

    public usuarioAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String registro() {
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setTelefono(telefono);
        setDireccion(direccion);
        setRol(rol);
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.existeUsuario(email, password) == true) {
            addActionError("Usuario existente. Por favor, inicia sesión.");
            return ERROR;
        } else {
            Usuario usuario = new Usuario(nombre, email, password, telefono, direccion, rol, new HashSet<>(), new HashSet<>());
            if (dao.crearUsuario(usuario) == true) {
                ActionContext.getContext().getSession().put("usuario", usuario); // Guardamos el usuario en sesión
                if ("ADMIN".equals(usuario.getRol())) {
                    usuarios = dao.listarUsuarios();
                    setUsuarios(usuarios);
                } else {

                }
                return SUCCESS;
            } else {
                return ERROR;
            }
        }
    }

    public String login() {
        setEmailLogin(emailLogin);
        setPasswordLogin(passwordLogin);
        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuario = dao.obtenerUsuarioPorCredenciales(emailLogin, passwordLogin);

        if (usuario != null) {
            ActionContext.getContext().getSession().put("usuario", usuario); // Guardamos el usuario en sesión
            if ("ADMIN".equals(usuario.getRol())) {
                usuarios = dao.listarUsuarios();
                setUsuarios(usuarios);
            } else {

            }
            return SUCCESS;
        } else {
            addActionError("Usuario no existente. Por favor, regístrese primero.");
            return ERROR;
        }
    }

    public String listar() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarios = dao.listarUsuarios();
        System.out.println("Total usuarios encontrados: " + usuarios.size());

        setUsuarios(usuarios);
        return SUCCESS;
    }

    public String cierraSesion() {
        ActionContext.getContext().getSession().clear();
        return SUCCESS;
    }

    public String redirigir() {
        return SUCCESS;
    }

    public String editar() {
        UsuarioDAO dao = new UsuarioDAO();
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        setTelefono(telefono);
        setDireccion(direccion);
        setRol(rol);
        Usuario usuario = dao.obtenerUsuarioPorEmail(email);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setRol(rol);
        usuario.setCitas(usuario.getCitas());
        usuario.setIncidencias(usuario.getIncidencias());
        dao.actualizarUsuario(usuario);

        usuarios = dao.listarUsuarios();
        setUsuarios(usuarios);

        return SUCCESS;
    }

    public String borrar() {
        UsuarioDAO dao = new UsuarioDAO();
        setEmail(email);
        Usuario usuario = dao.obtenerUsuarioPorEmail(email);
        dao.eliminarUsuario(usuario.getId());

        usuarios = dao.listarUsuarios();
        setUsuarios(usuarios);

        return SUCCESS;
    }

    @Override
    public void validate() {
        if ("registro".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombre.required"));
            }
            if (email == null || email.trim().isEmpty()) {
                addFieldError("email", getText("error.email.required"));
            }
            if (password == null || password.trim().isEmpty()) {
                addFieldError("password", getText("error.password.required"));
            }
            if (rol == null || rol.trim().isEmpty()) {
                addFieldError("rol", getText("error.rol.required"));
            }
        } else if ("login".equals(formulario)) {
            if (emailLogin == null || emailLogin.trim().isEmpty()) {
                addFieldError("emailLogin", "El email es obligatorio para login");
            }
            if (passwordLogin == null || passwordLogin.trim().isEmpty()) {
                addFieldError("passwordLogin", "La contraseña es obligatoria para login");
            }
        } else if ("editar".equals(formulario)) {
            if (nombre == null || nombre.trim().isEmpty()) {
                addFieldError("nombre", getText("error.nombre.required"));
            }
            if (email == null || email.trim().isEmpty()) {
                addFieldError("email", getText("error.email.required"));
            }
            if (password == null || password.trim().isEmpty()) {
                addFieldError("password", getText("error.password.required"));
            }
            if (rol == null || rol.trim().isEmpty()) {
                addFieldError("rol", getText("error.rol.required"));
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
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
     * @return the accion
     */
    public String getFormulario() {
        return formulario;
    }

    /**
     * @param accion the accion to set
     */
    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    /**
     * @return the emailLogin
     */
    public String getEmailLogin() {
        return emailLogin;
    }

    /**
     * @param emailLogin the emailLogin to set
     */
    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    /**
     * @return the passwordLogin
     */
    public String getPasswordLogin() {
        return passwordLogin;
    }

    /**
     * @param passwordLogin the passwordLogin to set
     */
    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
