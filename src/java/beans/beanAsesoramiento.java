package beans;

import DAO.ClienteJpaController;
import DAO.ComidaJpaController;
import DAO.DietaJpaController;
import DAO.EjercicioJpaController;
import DAO.RutinaJpaController;

import DTO.Comida;
import DTO.Dieta;
import DTO.Ejercicio;
import DTO.Rutina;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

public class beanAsesoramiento {
    private String tipoComida;
    static String diaSel;
    private EntityManagerFactory emf;
    private List<Dieta>listaDietas;
    private List<Ejercicio> listaPecho;
    private List<Ejercicio> listaEspalda;
    private List<Ejercicio> listaHombros;
    private List<Ejercicio> listaBrazos;
    private List<Ejercicio> listaPiernas;
    private List<Ejercicio> listaEjercicios;
    private List<Comida> listaComidas;
    private List<Comida> listaComidasDesayuno;
    private List<Comida> listaComidasAlmuerzo;
    private List<Comida> listaComidasMerienda;
    private List<Comida> listaComidasCena;
    private ComidaJpaController ctrComida;
    private EjercicioJpaController ctrEjercicio;
    private RutinaJpaController ctrRutina;
    private ClienteJpaController ctrCliente;
    private DietaJpaController ctrDieta;
    private Rutina ruti;
    private List<Rutina> listaRutina;
    private String parteDelCuerpo;

    public beanAsesoramiento() {
        emf = Persistence.createEntityManagerFactory("proyectoGimnasioPU");
        listaEjercicios = new ArrayList();
        listaEspalda = new ArrayList();
        listaBrazos = new ArrayList();
        listaHombros = new ArrayList();
        listaPecho = new ArrayList();
        listaPiernas = new ArrayList();
        listaComidas = new ArrayList();
        listaDietas= new ArrayList();
        listaComidasDesayuno = new ArrayList();
        listaComidasAlmuerzo = new ArrayList();
        listaComidasMerienda = new ArrayList();
        listaComidasCena = new ArrayList();
        ctrComida = new ComidaJpaController(emf);
        ctrEjercicio = new EjercicioJpaController(emf);
        ctrRutina = new RutinaJpaController(emf);
        listaRutina = new ArrayList();
        listaDietas= new ArrayList();
        ctrCliente = new ClienteJpaController(emf);
        ctrDieta= new DietaJpaController(emf);
    }

    public String getParteDelCuerpo() {
        return parteDelCuerpo;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public void setParteDelCuerpo(String parteDelCuerpo) {
        this.parteDelCuerpo = parteDelCuerpo;
    }

    public RutinaJpaController getCtrRutina() {
        return ctrRutina;
    }

    public void setCtrRutina(RutinaJpaController ctrRutina) {
        this.ctrRutina = ctrRutina;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public String getDiaSel() {
        return diaSel;
    }

    public List<Rutina> getListaRutina() {
        return listaRutina;
    }

    public void setListaRutina(List<Rutina> listaRutina) {
        this.listaRutina = listaRutina;
    }

    public void setDiaSel(String diaSel) {
        this.diaSel = diaSel;
    }

    public Rutina getRuti() {
        return ruti;
    }

    public void setRuti(Rutina ruti) {
        this.ruti = ruti;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Ejercicio> getListaPecho() {

        listaEjercicios = ctrEjercicio.findEjercicioEntities();

        for (int i = 0; i < listaEjercicios.size(); i++) {
            if (listaEjercicios.get(i).getGrupo().equals("pectoral")) {
                listaHombros.add(listaEjercicios.get(i));
            }

        }

        return listaPecho;
    }

    public void setListaPecho(List<Ejercicio> listaPecho) {
        this.listaPecho = listaPecho;
    }

    public List<Ejercicio> getListaEspalda() {
        return listaEspalda;
    }

    public void setListaEspalda(List<Ejercicio> listaEspalda) {
        this.listaEspalda = listaEspalda;
    }

    public List<Ejercicio> getListaHombros() {
        return listaHombros;
    }

    public void setListaHombros(List<Ejercicio> listaHombros) {
        this.listaHombros = listaHombros;
    }

    public List<Ejercicio> getListaBrazos() {
        return listaBrazos;
    }

    public void setListaBrazos(List<Ejercicio> listaBrazos) {
        this.listaBrazos = listaBrazos;
    }

    public List<Ejercicio> getListaPiernas() {
        return listaPiernas;
    }

    public void setListaPiernas(List<Ejercicio> listaPiernas) {
        this.listaPiernas = listaPiernas;
    }

    public List<Ejercicio> getListaEjercicios() {

        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public List<Comida> getListaComidas() {
        return listaComidas;
    }

    public ClienteJpaController getCtrCliente() {
        return ctrCliente;
    }

    public void setCtrCliente(ClienteJpaController ctrCliente) {
        this.ctrCliente = ctrCliente;
    }

    public void setListaComidas(List<Comida> listaComidas) {
        this.listaComidas = listaComidas;
    }

    public List<Comida> getListaComidasDesayuno() {
        return listaComidasDesayuno;
    }

    public void setListaComidasDesayuno(List<Comida> listaComidasDesayuno) {
        this.listaComidasDesayuno = listaComidasDesayuno;
    }

    public List<Comida> getListaComidasAlmuerzo() {
        return listaComidasAlmuerzo;
    }

    public void setListaComidasAlmuerzo(List<Comida> listaComidasAlmuerzo) {
        this.listaComidasAlmuerzo = listaComidasAlmuerzo;
    }

    public List<Comida> getListaComidasMerienda() {
        return listaComidasMerienda;
    }

    public void setListaComidasMerienda(List<Comida> listaComidasMerienda) {
        this.listaComidasMerienda = listaComidasMerienda;
    }

    public List<Comida> getListaComidasCena() {
        return listaComidasCena;
    }

    public void setListaComidasCena(List<Comida> listaComidasCena) {
        this.listaComidasCena = listaComidasCena;
    }

    public ComidaJpaController getCtrComida() {
        return ctrComida;
    }

    public void setCtrComida(ComidaJpaController ctrComida) {
        this.ctrComida = ctrComida;
    }

    public EjercicioJpaController getCtrEjercicio() {
        return ctrEjercicio;
    }

    public void setCtrEjercicio(EjercicioJpaController ctrEjercicio) {
        this.ctrEjercicio = ctrEjercicio;
    }

    public List<Ejercicio> verEjercicios(ValueChangeEvent event) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessio = (HttpSession) ctx.getSession(true);
        manageSesion bs = (manageSesion) sessio.getAttribute("bSesion");
        if (bs==null){
        String nombre="";
        }else{
        String nombre="";
  
          nombre = bs.getClienteLogeado().getNombreUsuario();
        
     
        String dia = diaSel;
        String musculo = (String) event.getNewValue();
        listaRutina = ctrRutina.traeRutinas(dia);
        
            for (int i = 0; i < listaRutina.size(); i++) {

                int num = listaRutina.get(i).getCodEjercicio();
                Ejercicio ej = ctrEjercicio.findEjercicio(num);

                if (ej.getGrupo().equals(musculo)&&listaRutina.get(i).getNombreUsuario().getNombreUsuario().equals(nombre)) {

                    listaEjercicios.add(ej);

                }

            }
        }

        return listaEjercicios;
    }
    
      public List<Comida> verAlimentos(ValueChangeEvent event) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessio = (HttpSession) ctx.getSession(true);
        manageSesion bs = (manageSesion) sessio.getAttribute("bSesion");
        if (bs==null){
        String nombre="";
        }else{
        String nombre="";
  
          nombre = bs.getClienteLogeado().getNombreUsuario();
        
     
        String dia = diaSel;
        String tipoComida = (String) event.getNewValue();
        listaDietas = ctrDieta.traeDietaspordia(dia);
            for (int i = 0; i < listaDietas.size(); i++) {

                int num = listaDietas.get(i).getCodComida().getCodComida();
                Comida Comi = ctrComida.findComida(num);

                if (listaDietas.get(i).getNombreUsuario().getNombreUsuario().equals(nombre)&&Comi.getTipoComida().equals(tipoComida)) {

                    listaComidas.add(Comi);

                }

            }
        }

        return listaComidas;
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
