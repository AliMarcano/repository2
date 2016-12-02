package org.test.zk.manager;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;


public class Cmanagercontroller extends SelectorComposer<Component> {

   
    private static final long serialVersionUID = 7423111049335041046L;
   
    @Listen ("onClick=#idadd")
    public void botonadd(Event event){
        //en comentarios porque ?
        //Map arg = new HashMap();
        //arg.put("someName", someValue);
       Window win = ( Window ) Executions.createComponents("/dialog.zul",null , null); //llama a la pagina dialog como una ventana emergente
       win.doModal();
    }
    @Listen ("onClick=#iddelete")
    public void botondelete(Event event){
        
        
    }
    @Listen ("onClick=#idmodify")
    public void botonmodificar(Event event){
        
        
    }
    
}
