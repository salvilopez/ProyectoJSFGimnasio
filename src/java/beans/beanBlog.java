/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.BlogJpaController;
import DTO.Blog;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HP
 */
public class beanBlog {

    private EntityManagerFactory emf;
    private List <Blog> listaNoticias;
   
    private BlogJpaController ctrBlog;
    static int pagina;
    private List paginas;
    public beanBlog() {
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
           
   
        ctrBlog = new BlogJpaController(emf);
      
    } 

   

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        beanBlog.pagina = pagina;
    }

    public List getPaginas() {
        return paginas;
    }

    public void setPaginas(List paginas) {
        this.paginas = paginas;
    }



    public BlogJpaController getCtrBlog() {
        return ctrBlog;
    }

    public void setCtrBlog(BlogJpaController ctrBlog) {
        this.ctrBlog = ctrBlog;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Blog> getListaNoticias() {
        return listaNoticias;
    }

    public void setListaNoticias(List<Blog> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }


    
    public List <Blog> obtenerBlogs(){
 listaNoticias= new ArrayList();
    List <Blog> lista= ctrBlog.findBlogEntities();
    
    if(pagina==0 || pagina==1){
    
        for (int i = 0; i < 2; i++) {
            listaNoticias.add(lista.get(i));
        }
    
    }else{
        for (int i=(pagina*2)-2;i < pagina*2;i++) {
            
            if(i<lista.size()){
                listaNoticias.add(lista.get(i));
            }
            
        }
    
    
    }
    
    
    
    return listaNoticias;
    }
    public List obtenerNumeropaginas(){
         paginas= new ArrayList();
    int total = ctrBlog.getBlogCount();
    
    int pag=total/2;
    int resto= total%2; 
    
        for (int i = 0; i < pag; i++) {
            paginas.add(""+(i+1)+"");
            
        }
        
        if(resto>0){
        paginas.add(""+(pag+1)+"");
        
        }
    return paginas;
    }
}
