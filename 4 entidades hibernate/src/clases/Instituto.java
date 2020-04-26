package clases;
// Generated 24-feb-2020 14:12:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;


public class Instituto  implements java.io.Serializable {


     private Integer codInstituto;
     private String nombre;
     private String provincia;
     private String calle;
     private Set clases = new HashSet(0);

    public Instituto() {
    }

	
    public Instituto(String nombre, String provincia, String calle) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.calle = calle;
    }
    public Instituto(String nombre, String provincia, String calle, Set clases) {
       this.nombre = nombre;
       this.provincia = provincia;
       this.calle = calle;
       this.clases = clases;
    }
   
    public Integer getCodInstituto() {
        return this.codInstituto;
    }
    
    public void setCodInstituto(Integer codInstituto) {
        this.codInstituto = codInstituto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public Set getClases() {
        return this.clases;
    }
    
    public void setClases(Set clases) {
        this.clases = clases;
    }




}


