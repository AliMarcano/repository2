package org.test.zk.dao;

import java.io.Serializable;

public class Cperson implements Serializable {
   
    private static final long serialVersionUID = -6867180606799067089L;
   
    protected String strId;
    protected String name;
    protected String secondname;
    
    //constructor
    
    public Cperson ( String Id, String rename, String resecondname ){//el nombre es el debe ser el mismo de la clase
         this.strId = Id;
         this.name = rename;
         this.secondname = resecondname;
         
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
    
    
}
