
package logicaNegocio;

import DAO.BlogJpaController;
import DTO.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author HP
 */
public class traeblog extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroPag=request.getParameter("pagina");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        BlogJpaController ctrBlog = new BlogJpaController(emf);
        List <Blog> listaBlog =ctrBlog.findBlogEntities();
        
        JSONObject jso = new JSONObject();
        
        try {
            jso.put("listaBlog",listaBlog );
        } catch (JSONException ex) {
            Logger.getLogger(traeblog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String jsonS =jso.toString();
        response.getWriter().print(jsonS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
