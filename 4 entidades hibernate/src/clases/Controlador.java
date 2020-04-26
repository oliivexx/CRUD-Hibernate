

package clases;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Controlador {
    ArrayList<Instituto> arrayInstitutos;
    ArrayList<Clase> arrayClases;
    ArrayList<Profesor> arrayProfesores;
    ArrayList<Alumno> arrayAlumnos;
    
    public Controlador() {
    }

    public Controlador(ArrayList<Instituto> arrayInstitutos, ArrayList<Clase> arrayClases, ArrayList<Profesor> arrayProfesores, ArrayList<Alumno> arrayAlumnos) {
        this.arrayInstitutos = arrayInstitutos;
        this.arrayClases = arrayClases;
        this.arrayProfesores = arrayProfesores;
        this.arrayAlumnos = arrayAlumnos;
    }
    

    public void leerInstituto()  {
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            Query q = (Query) s.createQuery("FROM Instituto").setReadOnly(true);
            List<Instituto> listaInstitutos  = s.createQuery("FROM Instituto").list();
            
            for (Instituto ins : listaInstitutos) {
                    arrayInstitutos.add(ins);   
            }

            
             
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void insertarInstituto(Instituto aux){
    
        Transaction t = null; 
        int row = 0;
        try { 
            
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.save(aux);
            t.commit();

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void borrarInstituto(Instituto aux){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (aux != null) {
                s.delete(aux);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
    
    public void modificarInstituto(Instituto aux){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(aux);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
         
    }
    
    /*----------------------------Clase-------------------------------------*/

    public void leerClase(){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            Query q = (Query) s.createQuery("FROM Clase").setReadOnly(true);
            List<Clase> listaClases  = s.createQuery("FROM Clase").list();
            
            for (Clase clase:listaClases) {
                    arrayClases.add(clase);  
            }

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void insertarClase(Clase aux){
    
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.save(aux);
            t.commit();

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void borrarClase(Clase aux){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (aux != null) {
                s.delete(aux);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
    public void modificarClase(Clase aux){
        //no funsiona
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(aux);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
         
    }
    
    /*----------------------------PROFESOR-------------------------------------*/
    
    public void leerProfesores()  {
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            Query q = (Query) s.createQuery("FROM Profesor").setReadOnly(true);
            List<Profesor> listaProfes  = s.createQuery("FROM Profesor").list();
            
            for (Profesor profe:listaProfes) {
                    arrayProfesores.add(profe);  
            }

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void insertarProfe(Profesor aux){
    
       Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.save(aux);
            t.commit();

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void borrarProfe(Profesor aux){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (aux != null) {
                s.delete(aux);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
    public void modificarProfe(Profesor aux){
        //no funsiona
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(aux);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
         
    }
    
    /*----------------------------ALUMNO-------------------------------------*/
    
    public void leerAlumnos()  {
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            Query q = (Query) s.createQuery("FROM Alumno").setReadOnly(true);
            List<Alumno> listaAlumnos  = s.createQuery("FROM Alumno").list();
            
            for (Alumno alumn:listaAlumnos) {
                    arrayAlumnos.add(alumn);  
            }

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void insertarAlumno(Alumno aux){
    
       Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.save(aux);
            t.commit();

            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }

    }
    
    public void borrarAlumno (Alumno aux){
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            if (aux != null) {
                s.delete(aux);
            }
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
    }
    
    public void modificarAlumno(Alumno aux){
        //no funsiona
        Transaction t = null; 
        try { 
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            t = s.beginTransaction(); 
            s.update(aux);
            t.commit();
            s.close();
        }catch (HibernateException e) { 
                    e.printStackTrace(System.err); 
                    if (t != null) { 
                            t.rollback(); 
                    }
        }
         
    }

}
