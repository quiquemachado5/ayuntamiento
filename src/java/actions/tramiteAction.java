/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import dao.Departamento;
import dao.Tramite;
import java.util.HashSet;
import java.util.List;
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

    public tramiteAction() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
