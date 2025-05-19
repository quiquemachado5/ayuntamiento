/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.IncidenciaDAO;
import dao.Usuario;
import java.util.Map;

/**
 *
 * @author emdominguez
 */
public class totalIncidenciasAction extends ActionSupport {

    public totalIncidenciasAction() {
    }

    private int totalIncidencias;
    private int totalIncidenciasUsuarios;

    public String execute() {
        IncidenciaDAO dao = new IncidenciaDAO();
        setTotalIncidencias(dao.totalIncidencias());
        Map<String, Object> session = ActionContext.getContext().getSession();
        Usuario usuario = (Usuario) session.get("usuario");
        setTotalIncidenciasUsuarios(dao.totalIncidenciasIndividual(usuario));
        return SUCCESS;
    }

    public int getTotalIncidencias() {
        return totalIncidencias;
    }

    public void setTotalIncidencias(int totalIncidencias) {
        this.totalIncidencias = totalIncidencias;
    }

    /**
     * @return the totalIncidenciasUsuarios
     */
    public int getTotalIncidenciasUsuarios() {
        return totalIncidenciasUsuarios;
    }

    /**
     * @param totalIncidenciasUsuarios the totalIncidenciasUsuarios to set
     */
    public void setTotalIncidenciasUsuarios(int totalIncidenciasUsuarios) {
        this.totalIncidenciasUsuarios = totalIncidenciasUsuarios;
    }

}
