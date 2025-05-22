/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emdominguez
 */
public class IncidenciaDAO {

    public List<Incidencia> listarIncidencias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "FROM Incidencia i JOIN FETCH i.departamento JOIN FETCH i.usuario";
        Query q = session.createQuery(hql);
        List<Incidencia> incidencias = q.list();

        tx.commit();
        session.close();
        return incidencias;
    }

    public List<Incidencia> listarIncidenciasPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "FROM Incidencia i JOIN FETCH i.departamento WHERE i.usuario = :usuario";
        Query q = session.createQuery(hql);
        q.setParameter("usuario", usuario);
        List<Incidencia> incidencias = q.list();

        tx.commit();
        session.close();
        return incidencias;
    }

    public void crearIncidencia(Incidencia incidencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(incidencia);
        tx.commit();
        session.close();
    }

    public Incidencia obtenerIncidenciaID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hq1 = "FROM Incidencia WHERE id = :id"; // 
        Query q = session.createQuery(hq1);
        q.setParameter("id", id);

        Incidencia e = (Incidencia) q.uniqueResult();
        
        session.close();

        return e;
    }

    public void editarIncidencia(int id, String titulo, Departamento departamento, Usuario usuario, String descripcion, String estado, Date fechaReporte) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        Incidencia inci = (Incidencia) session.get(Incidencia.class, id);  // o obtenerIncidenciaID(id) adaptado para usar la sesión actual
        if (inci != null) {
            inci.setTitulo(titulo);
            inci.setDepartamento(departamento);
            inci.setUsuario(usuario);
            inci.setDescripcion(descripcion);
            inci.setEstado(estado);
            inci.setFechaReporte(fechaReporte);

            session.update(inci);
        }
        tx.commit();
        session.close();
    }

    public void borrar(Incidencia incidencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(incidencia);
        tx.commit();
        session.close();
    }

    //Método para el total de incidencias que vemos en home
    public int totalIncidencias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "SELECT COUNT(i) FROM Incidencia i WHERE i.estado != 'CERRADA'";
        Long total = (Long) session.createQuery(hql).uniqueResult();

        tx.commit();
        session.close();
        return total != null ? total.intValue() : 0;
    }

    //Método para el total de incidencias individual por usuario que vemos en home
    public int totalIncidenciasIndividual(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "SELECT COUNT(i) FROM Incidencia i WHERE i.usuario = :usuario AND i.estado != 'CERRADA'";
        Long total = (Long) session.createQuery(hql)
                .setParameter("usuario", usuario)
                .uniqueResult();

        tx.commit();
        session.close();
        return total != null ? total.intValue() : 0;
    }

}
