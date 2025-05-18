/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emdominguez
 */
public class IncidenciaDAO {

    public List<Incidencia> listarIncidencias() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "FROM Incidencia i JOIN FETCH i.departamento JOIN FETCH i.usuario";
        Query q = session.createQuery(hql);
        List<Incidencia> incidencias = q.list();

        tx.commit();
        return incidencias;
    }

    public List<Incidencia> listarIncidenciasPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "FROM Incidencia i JOIN FETCH i.departamento WHERE i.usuario = :usuario";
        Query q = session.createQuery(hql);
        q.setParameter("usuario", usuario);
        List<Incidencia> incidencias = q.list();

        tx.commit();
        return incidencias;
    }

}
