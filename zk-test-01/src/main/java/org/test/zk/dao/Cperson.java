package org.test.zk.dao;

import java.io.Serializable;
import java.time.LocalDate;

public class Cperson implements Serializable {
   
    private static final long serialVersionUID = -6867180606799067089L;
   
    protected String strId;
    protected String name;
    protected String secondname;
    protected int genero; //0 female 1 male
    protected LocalDate nacio=null;
    protected String comentario;
    
    //recuerda debes crear los set y get dado segundo click en la variable en la parte de source 
    //generate getters and setters
    //constructor
    
    public Cperson ( String Id, String rename, String resecondname, int generos, LocalDate cumple, String  describe ){//el nombre es el debe ser el mismo de la clase
         this.strId = Id;
         this.name = rename;
         this.secondname = resecondname;
         this.genero= generos;
         this.nacio=cumple;
         this.comentario=describe;
         
         
    }


    public String getId() {
        
        return strId;
    }
    
    public void setId( String strId ) {
        
        this.strId = strId;
    }
    
    public String getName() {
        
        return name;
    }
    
    public void setName( String name ) {
        
        this.name = name;
    }
    
    public String getSecondname() {
        
        return secondname;
    }
    
    public void setSecondname( String secondname ) {
        
        this.secondname = secondname;
    }
 public int getGenero() {
        
        return genero;
    }


    
    public void setGenero( int genero ) {
        
        this.genero = genero;
    }


    
    public LocalDate getNacio() {
        
        return nacio;
    }


    
    public void setNacio( LocalDate nacio ) {
        
        this.nacio = nacio;
    }


    
    public String getComentario() {
        
        return comentario;
    }


    
    public void setComentario( String comentario ) {
        
        this.comentario = comentario;
    }
    
}
