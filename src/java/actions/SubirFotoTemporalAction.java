package actions;

/**
 *
 * @author emdominguez
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ImageUtil;
import dao.Usuario;
import dao.UsuarioDAO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class SubirFotoTemporalAction extends ActionSupport {

    private File imagen;
    private String imagenFileName;

    public String execute() {
        try {
            UsuarioDAO dao = new UsuarioDAO();

            Map<String, Object> session = ActionContext.getContext().getSession();
            Usuario usuario = (Usuario) session.get("usuario");
            String nombreFoto = usuario.getEmail();

            String basePath = "C:\\Users\\emdominguez\\OneDrive - Indra\\Documentos\\IT\\Exámenes anteriores resueltos\\ayuntamientoSevilla\\web\\img\\tmp\\";
            File destino = new File(basePath + nombreFoto + ".png");
            File miniatura = new File(basePath + "mini_" + nombreFoto + ".png");

            FileUtils.copyFile(imagen, destino);
            ImageUtil.resize(destino, miniatura, 200, 200);  // Crea una miniatura de 200x200

            // Guardas el nombre en el usuario en sesión
            usuario.setFotoPerfil(nombreFoto);  // Esto debes tener el atributo en Usuario o puedes usar session.put("fotoPerfil", nombreFoto);
            session.put("usuario", usuario);  // ACTUALIZA el objeto en la sesión

            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

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
