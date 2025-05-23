/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author emdominguez
 */
public class UsuarioDAO {

    /*Uso session.close para evitar el error de Nested*/
    // Crear usuario
    public boolean crearUsuario(Usuario usuario) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(usuario);
            tx.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Leer usuario por ID
    public Usuario obtenerUsuarioPorId(int id) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Usuario usuario = (Usuario) session.get(Usuario.class, id);
            tx.commit();
            session.close();
            return usuario;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    /*Obtener usuario por email*/
    public Usuario obtenerUsuarioPorEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        String hq1 = "FROM Usuario WHERE email = :email";
        Query q = session.createQuery(hq1);
        q.setParameter("email", email);
        Usuario usuario = (Usuario) q.uniqueResult();
        tx.commit();
        session.close();
        return usuario;
    }

    /*Booleano para saber si existe*/
    public boolean existeUsuario(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean existe = false;

        try {
            tx = session.beginTransaction();
            /*Comprobamos con email y password*/
            String hql = "FROM Usuario u WHERE u.email = :email AND u.password = :password";
            Usuario usuario = (Usuario) session.createQuery(hql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();

            if (usuario != null) {
                existe = true;
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return existe;
    }

    /*Login DAO*/
    public Usuario obtenerUsuarioPorCredenciales(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Usuario WHERE email = :email AND password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (Usuario) query.uniqueResult();
        } finally {
            session.close();
        }
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        String hq1 = "FROM Usuario";
        Query q = session.createQuery(hq1);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) q.list();

        tx.commit();
        session.close();
        return usuarios;
    }

    // Actualizar usuario
    public boolean actualizarUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(usuario);
        tx.commit();
        session.close();
        return true;
    }

    // Eliminar usuario
    public boolean eliminarUsuario(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();

        // Obtener usuario
        Usuario u = (Usuario) session.get(Usuario.class, id);

        if (u != null) {
            // Borrar primero citas relacionadas
            String hqlDeleteCitas = "DELETE FROM Cita WHERE usuario.id = :id";
            Query deleteCitasQuery = session.createQuery(hqlDeleteCitas);
            deleteCitasQuery.setParameter("id", id);
            deleteCitasQuery.executeUpdate();

            // Borrar incidencias relacionadas
            String hqlDeleteIncidencias = "DELETE FROM Incidencia WHERE usuario.id = :id";
            Query deleteIncidenciasQuery = session.createQuery(hqlDeleteIncidencias);
            deleteIncidenciasQuery.setParameter("id", id);
            deleteIncidenciasQuery.executeUpdate();

            // Ahora borrar el usuario
            session.delete(u);
        }

        tx.commit();
        session.close();
        return true;
    }

    public String buscarFotoEnDisco(String email) {
        // Suponiendo que la foto se guarda como "email.jpg" en la carpeta img/tmp
        String ruta = ServletActionContext.getServletContext().getRealPath("/img/tmp/");
        System.out.println("Ruta real en servidor: " + ruta);

        String nombreArchivo = "mini_" + email + ".png";

        File archivo = new File(ruta + nombreArchivo);
        if (archivo.exists()) {
            return nombreArchivo;
        } else {
            return null; // no hay foto
        }
    }

}
