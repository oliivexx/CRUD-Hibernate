package clases;
// Generated 24-feb-2020 14:12:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Clase generated by hbm2java
 */
public class Clase  implements java.io.Serializable {
     private Integer codClase;
     private Instituto instituto;
     private int numeroAlumnos;
     private String asignatura;
     private Set profesors = new HashSet(0);

    public Clase() {
    }

	
    public Clase(Instituto instituto, int numeroAlumnos, String asignatura) {
        this.instituto = instituto;
        this.numeroAlumnos = numeroAlumnos;
        this.asignatura = asignatura;
    }
    public Clase(Instituto instituto, int numeroAlumnos, String asignatura, Set profesors) {
       this.instituto = instituto;
       this.numeroAlumnos = numeroAlumnos;
       this.asignatura = asignatura;
       this.profesors = profesors;
    }
   
    public Integer getCodClase() {
        return this.codClase;
    }
    
    public void setCodClase(Integer codClase) {
        this.codClase = codClase;
    }
    public Instituto getInstituto() {
        return this.instituto;
    }
    
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }
    public int getNumeroAlumnos() {
        return this.numeroAlumnos;
    }
    
    public void setNumeroAlumnos(int numeroAlumnos) {
        this.numeroAlumnos = numeroAlumnos;
    }
    public String getAsignatura() {
        return this.asignatura;
    }
    
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    public Set getProfesors() {
        return this.profesors;
    }
    
    public void setProfesors(Set profesors) {
        this.profesors = profesors;
    }




}

