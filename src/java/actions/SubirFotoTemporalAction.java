package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ImageUtil;
import dao.Usuario;
import dao.UsuarioDAO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

public class SubirFotoTemporalAction extends ActionSupport implements ServletContextAware {

    private File imagen;
    private String imagenFileName;
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String execute() {
        try {
            Map<String, Object> session = ActionContext.getContext().getSession();
            Usuario usuario = (Usuario) session.get("usuario");
            if (usuario == null) {
                addActionError("Usuario no está en sesión");
                return ERROR;
            }

            String nombreFoto = usuario.getEmail(); // Nombre base para la foto

            // Ruta para guardar la imagen original y la miniatura
            String basePath = servletContext.getRealPath("/img/perfiles/");
            File carpeta = new File(basePath);
            if (!carpeta.exists()) {
                carpeta.mkdirs();  // Crea la carpeta si no existe
            }

            File archivoOriginal = new File(basePath + nombreFoto + ".png");
            File archivoMiniatura = new File(basePath + "mini_" + nombreFoto + ".png");

            // Guardamos la imagen subida
            FileUtils.copyFile(imagen, archivoOriginal);

            // Creamos la miniatura (200x200 px) en mi clase de resize
            ImageUtil.resize(archivoOriginal, archivoMiniatura, 200, 200);

            // Guardamos el nombre de la miniatura en el usuario en sesión
            usuario.setFotoPerfil("mini_" + nombreFoto + ".png");
            session.put("usuario", usuario);

            return SUCCESS;

        } catch (IOException e) {
            e.printStackTrace();
            addActionError("Error al subir la imagen: " + e.getMessage());
            return ERROR;
        }
    }

    // Getters y setters
    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String getImagenFileName() {
        return imagenFileName;
    }

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }
}
