/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.ReservaJpaController;
import DAO.SalaJpaController;
import DTO.Blog;
import DTO.Reserva;
import DTO.Sala;
import bd.Conexion;
import static beans.beanBlog.pagina;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class beanActividades{

    private String fechaSel;
    private Sala SalaElegido;
    private String codSalaElegida;
    private EntityManagerFactory emf;
    private List listaActividades;
    private SalaJpaController ctrSala;
    private ReservaJpaController ctrReserva;
    private String horaElegida;
    private List<Integer> listaHorasDisp;
    static int numPag;
   
    private List<Integer> listaHorasOcupadas;
    private List<Reserva> listaReservasOcupadas;
    private Reserva reservaSel;
    private int ocupacion;
    private List paginas;
 private boolean lleno;
 
    public beanActividades() {
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        
        ctrSala = new SalaJpaController(emf);
  
        listaHorasDisp = new ArrayList();
        listaHorasOcupadas = new ArrayList();
        listaReservasOcupadas = new ArrayList();
        ctrReserva = new ReservaJpaController(emf);
 
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        beanActividades.numPag = numPag;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public boolean isLleno() {
        return lleno;
    }

    public void setLleno(boolean lleno) {
        this.lleno = lleno;
    }

    public Reserva getReservaSel() {
        return reservaSel;
    }

    public void setReservaSel(Reserva reservaSel) {
        this.reservaSel = reservaSel;
    }

    public ReservaJpaController getCtrReserva() {
        return ctrReserva;
    }

    public void setCtrReserva(ReservaJpaController ctrReserva) {
        this.ctrReserva = ctrReserva;
    }

    public String getHoraElegida() {
        return horaElegida;
    }

    public List<Integer> getListaHorasDisp() {
        return listaHorasDisp;
    }

    public void setListaHorasDisp(List<Integer> listaHorasDisp) {
        this.listaHorasDisp = listaHorasDisp;
    }

  
    public List<Integer> getListaHorasOcupadas() {
        return listaHorasOcupadas;
    }

    public void setListaHorasOcupadas(List<Integer> listaHorasOcupadas) {
        this.listaHorasOcupadas = listaHorasOcupadas;
    }

    public List<Reserva> getListaReservasOcupadas() {
        return listaReservasOcupadas;
    }

    public void setListaReservasOcupadas(List<Reserva> listaReservasOcupadas) {
        this.listaReservasOcupadas = listaReservasOcupadas;
    }

    public void setHoraElegida(String horaElegida) {
        this.horaElegida = horaElegida;
    }

 

    public String getCodSalaElegida() {
        return codSalaElegida;
    }

    public void setCodSalaElegida(String codSalaElegida) {
        this.codSalaElegida = codSalaElegida;
    }

    public List getPaginas() {
        return paginas;
    }

    public void setPaginas(List paginas) {
        this.paginas = paginas;
    }



    public Sala getSalaElegido() {
        return SalaElegido;
    }

    public void setSalaElegido(Sala SalaElegido) {
        this.SalaElegido = SalaElegido;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List getListaActividades() {


        return listaActividades;
    }

    public void setListaActividades(List listaActividades) {
        this.listaActividades = listaActividades;
    }

    public SalaJpaController getCtrSala() {
        return ctrSala;
    }

    public void setCtrSala(SalaJpaController ctrSala) {
        this.ctrSala = ctrSala;
    }

    public String getFechaSel() {
        return fechaSel;
    }

    public void setFechaSel(String fechaSel) {
        this.fechaSel = fechaSel;
    }

    public void comprobarDisp(ValueChangeEvent event) {
    String pepe=(String)event.getNewValue();
    
    String paco="";
    
     /*   Date fs= Date.valueOf(fechaSel);
        int cs=Integer.parseInt(codSalaElegida);
        int he=Integer.parseInt(horaElegida);*/
     //  listaReservasOcupadas=  ctrReserva.traeFechas(fs,cs,he);

    }
    public List <Sala> obtenerSalas(){
    listaActividades= new ArrayList();
    List <Sala> lista= ctrSala.findSalaEntities();
    
    if(pagina==0 || pagina==1){
    
        for (int i = 0; i < 2; i++) {
            listaActividades.add(lista.get(i));
        }
    
    }else{
        for (int i=(pagina*2)-2;i < pagina*2;i++) {
            
            if(i<lista.size()){
                listaActividades.add(lista.get(i));
            }
            
        }
    
    
    }
    
    
    
    return listaActividades;
    }
    public List obtenerNumeropaginas(){
         paginas= new ArrayList();
    int total = ctrSala.getSalaCount();
    
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
    public void cogerCita() {

    }

}
