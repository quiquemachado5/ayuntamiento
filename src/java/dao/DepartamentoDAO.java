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
public class DepartamentoDAO {

    public List<Departamento> listarDepartamentos() {
        /* READ  */
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }

        List<Departamento> lista = session.createQuery("from Departamento").list();

        // tx.commit(); // mejor no hacer commit aquí para no interferir con otras operaciones
        return lista;

    }

    public Departamento obtenerDepartamentoEmail(String email) {
        /* READ email */
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM Departamento WHERE emailContacto = :email"; // 
        Query q = session.createQuery(hq1);
        q.setParameter("email", email);

        Departamento d = (Departamento) q.uniqueResult();
        tx.commit();
        session.close();
        return d;
    }

    public void actualizarDepartamento(Departamento departamento) {
        /* UPDATE */
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(departamento);
        tx.commit();
        session.close();
    }

    public void borrarDepartamento(int departamentoId) {
        /* DELETE */
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Borrar trámites primero
            Query deleteTramites = session.createQuery("DELETE FROM Tramite t WHERE t.departamento.id = :depId");
            deleteTramites.setParameter("depId", departamentoId);
            deleteTramites.executeUpdate();

            // Borrar departamento
            Query deleteDepartamento = session.createQuery("DELETE FROM Departamento d WHERE d.id = :depId");
            deleteDepartamento.setParameter("depId", departamentoId);
            deleteDepartamento.executeUpdate();

            tx.commit();
            session.close();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

    public boolean tieneIncidenciasAsociadas(int idDepartamento) {
        /* Método para ver si hay incidencias asociadas */
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) session.createQuery(
                "select count(i.id) from Incidencia i where i.departamento.id = :id")
                .setParameter("id", idDepartamento)
                .uniqueResult();
        session.close();
        return count > 0;
    }

    public void crearDepartamento(Departamento departamento) {
        /* CREATE */
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(departamento);
        tx.commit();
        session.close();
    }
    
    

}
