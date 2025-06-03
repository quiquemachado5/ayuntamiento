/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emdominguez
 */
public class CitaDAO {

    public List<Cita> listarCitas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hql = "SELECT c FROM Cita c JOIN FETCH c.tramite JOIN FETCH c.usuario";

        Query q = session.createQuery(hql);
        List<Cita> citas = q.list();

        session.close();
        return citas;
    }

     public List<Cita> obtenerPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT c FROM Cita c JOIN FETCH c.tramite JOIN FETCH c.usuario WHERE usuario_id = :id");
        query.setParameter("id", usuario.getId());
        List<Cita> lista = query.list();
        
        session.close();
        return lista;
    }
    
    public void crearCita(Cita cita) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cita);
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

    public Cita obtenerCitaPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Cita cita = (Cita) session.get(Cita.class, id);

            if (cita != null) {
                // Inicializar relaciones para que estén disponibles fuera del session
                Hibernate.initialize(cita.getTramite());
                Hibernate.initialize(cita.getUsuario());
            }

            return cita;
        } finally {
            session.close();
        }
    }

    public void actualizarCita(Cita cita) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(cita);
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

    public void eliminarCita(Cita cita) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(cita);
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

   

}
