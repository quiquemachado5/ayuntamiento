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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM Departamento";
        Query q = session.createQuery(hq1);
        List<Departamento> departamentos = new ArrayList<>();
        departamentos = (ArrayList<Departamento>) q.list();

        tx.commit();
        return departamentos;
    }

    public Departamento obtenerDepartamentoEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM Departamento WHERE emailContacto = :email"; // 
        Query q = session.createQuery(hq1);
        q.setParameter("email", email);

        Departamento d = (Departamento) q.uniqueResult();
        tx.commit();

        return d;
    }

    public void actualizarDepartamento(Departamento departamento) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(departamento);
        tx.commit();
    }

    public void borrarDepartamento(int departamentoId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Borrar trámites
            Query deleteTramites = session.createQuery("DELETE FROM Tramite t WHERE t.departamento.id = :depId");
            deleteTramites.setParameter("depId", departamentoId);
            deleteTramites.executeUpdate();

            // Borrar departamento
            Query deleteDepartamento = session.createQuery("DELETE FROM Departamento d WHERE d.id = :depId");
            deleteDepartamento.setParameter("depId", departamentoId);
            deleteDepartamento.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

    public boolean tieneIncidenciasAsociadas(int idDepartamento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) session.createQuery(
                "select count(i.id) from Incidencia i where i.departamento.id = :id")
                .setParameter("id", idDepartamento)
                .uniqueResult();
        session.close();
        return count > 0;
    }

    public void crearDepartamento(Departamento departamento) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(departamento);
        tx.commit();
    }

}
