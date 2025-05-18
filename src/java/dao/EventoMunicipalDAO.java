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
import org.hibernate.Transaction;

/**
 *
 * @author emdominguez
 */
public class EventoMunicipalDAO {
    public List<EventoMunicipal> listarEventos() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM EventoMunicipal";
        Query q = session.createQuery(hq1);
        List<EventoMunicipal> eventos = new ArrayList<>();
        eventos = (ArrayList<EventoMunicipal>) q.list();

        tx.commit();
        return eventos;
    }

    public EventoMunicipal obtenerEventoId(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM EventoMunicipal WHERE id = :id"; // 
        Query q = session.createQuery(hq1);
        q.setParameter("id", id);

        EventoMunicipal e = (EventoMunicipal) q.uniqueResult();
        tx.commit();
        
        return e;
    }
    
    public EventoMunicipal obtenerEventoTitulo(String titulo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM EventoMunicipal WHERE titulo = :titulo"; // 
        Query q = session.createQuery(hq1);
        q.setParameter("titulo", titulo);

        EventoMunicipal e = (EventoMunicipal) q.uniqueResult();
        tx.commit();
        
        return e;
    }

    public void actualizarEvento(EventoMunicipal evento) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(evento);
        tx.commit();
    }

    public void borrarEvento(EventoMunicipal evento) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(evento);
        tx.commit();

    }

    public void crearEvento(EventoMunicipal evento) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(evento);
        tx.commit();
    }
}
