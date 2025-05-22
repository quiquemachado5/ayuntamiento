/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emdominguez
 */
public class TramiteDAO {

    public List<Tramite> listarTramites() {
        //READ
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "SELECT t FROM Tramite t JOIN FETCH t.departamento";
        Query q = session.createQuery(hql);
        List<Tramite> tramites = q.list();

        session.close();
        return tramites;
    }
    
    
    public void crearTramite(Tramite tramite) {
        //create
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(tramite);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public Tramite obtenerTramitePorId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Tramite) session.get(Tramite.class, id);
        } finally {
            session.close();
        }
    }
    
    public void actualizarTramite(Tramite tramite) {
        //update
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(tramite);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void eliminarTramite(Tramite tramite) {
        //DELETE
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(tramite);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
