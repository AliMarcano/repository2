package org.test.zk.manager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.test.zk.dao.Cperson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


public class Cmanagercontroller extends SelectorComposer<Component> {

   
    private static final long serialVersionUID = 7423111049335041046L;
   
    protected ListModelList< Cperson > dataModel = new ListModelList<Cperson>();
    
    public class Rendererpersonas implements ListitemRenderer<Cperson>{
        

        @Override
        public void render( Listitem lista, Cperson persona, int arg2 ) throws Exception {
            
            try{
                Listcell id = new Listcell();
                
                id.setLabel( persona.getId() );
                
                lista.appendChild(id);
                
                
                Listcell nombre = new Listcell();
                
                nombre.setLabel( persona.getName() );
                
                lista.appendChild(nombre);
                
                
                Listcell apellido = new Listcell();
                
                apellido.setLabel( persona.getSecondname());
                
                lista.appendChild(apellido);
                
                
                Listcell genero = new Listcell();
                
                genero.setLabel( persona.getGenero()==0?"female" : "male");
                
                lista.appendChild(genero);
                
                
                
                Listcell fecha = new Listcell();
                
                fecha.setLabel( persona.getNacio().toString());
                
                lista.appendChild(fecha);
                
                
                Listcell comentario = new Listcell();
                
                comentario.setLabel( persona.getComentario());
                
                lista.appendChild(comentario);
                
            }
            catch (Exception ex){
                ex.printStackTrace();
                
            }
            
            // TODO Auto-generated method stub
            
        }
     }


    
    
    @Wire
    Listbox listabox;
    
    @Override
    public void doAfterCompose(Component comp) {

            try {
                super.doAfterCompose(comp);
                
              //desde aqui+
                //creo las personas
                Cperson persona1= new Cperson("24437593","Ali","Marcano",1, LocalDate.parse( "1995-04-24" ),"lolicon");
                Cperson persona2= new Cperson("22xxxxxx","German","Cedeño",1, LocalDate.parse( "1994-08-02" ),"kudere a live");
                Cperson persona3= new Cperson("25xxxxxx","Roger","Paesani",1, LocalDate.parse( "1995-05-24" ),"ui si un comentario");
                Cperson persona4= new Cperson("25xxxxxx","Juan","Manuel",1, LocalDate.parse( "1995-09-28" ),"nekofilico");
                //las asigno al ata moder
                dataModel.add( persona1 );
                dataModel.add( persona2 );
                dataModel.add( persona3 );
                dataModel.add( persona4 );
                
              //-hasta aqui si tenemos una "base de datos" seria cuestion se hacer el metodo que selecione uno por 
              // uno para ir agregando data model  
              /** 
               * public void insetdata(Component com, lista listapersona){
               * if(listapersona!=null){
               *  
               *  Cperson loquesea = new Cperson(listapersona.id,listapersona.nombre,listapersona.apellido);
               *  dataModel.add(loquesea);
               *  insetdata(comp, lista.sig)
               *  }
               * }
               * 
               * listbox.setModel(datamodel);
               * */  
                
                //le digo a la lista que agarre el data model
                
                
                dataModel.setMultiple( true ); //por si quieres elecion multiple en la lista
                
                listabox.setModel( dataModel );
                
                
                listabox.setItemRenderer( new Rendererpersonas() );
                
            }
            catch ( Exception e ) {
              
                e.printStackTrace();
            }


     }
    
    
    
    //------------------------------------------- inicio añadir---------------------------------------------------------------------
    
    
    @Listen ("onClick=#idadd")
    public void botonadd(Event event){
        //en comentarios porque ? 
        //Map arg = new HashMap();
        //arg.put("someName", someValue);
       Window win = ( Window ) Executions.createComponents("/dialog.zul",null , null); //llama a la pagina dialog como una ventana emergente
       win.doModal();
    }
    
    
    //------------------------------------------- inicio Delete--------------------------------------------------------------------- 
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    
    
    @Listen ("onClick=#idDelete")
    public void botondelete(Event event){
        Set<Cperson> Selecteditem = dataModel.getSelection();
        
        if( Selecteditem != null && Selecteditem.size()>0){
           //esto es para cuando se seleccionaron varias
           String mostra=null; 
            
           for( Cperson person : Selecteditem ){
             if (mostra != null){
                 mostra= mostra+"\n"+person.getId()+" "+person.getName()+" "+person.getSecondname();
             }else{
                 mostra= person.getId()+" "+person.getName()+" "+person.getSecondname();
             }
             
             
               
     
               
           }
           Messagebox.show("Seguro desea eliminar "+ Integer.toString( Selecteditem.size())+" de los registros \n"+mostra , "Eliminar", Messagebox.OK  | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
               public void onEvent(Event evt) throws InterruptedException {
                   if (evt.getName().equals("onOK")) {
                     //eliminar registros
                      
                       while( Selecteditem.iterator().hasNext() ){
                           Cperson person= Selecteditem.iterator().next();
                           
                           //Selecteditem.iterator().remove(); 
                           
                           dataModel.remove( person );
                           
                       }

                   } 
               }
           }); 
           //Messagebox.show( mostra );  
          //esto es para una persona  
          // Cperson person=Selecteditem.iterator().next();
          // Messagebox.show(person.getId()+" "+person.getName()+" "+person.getSecondname());
 
        }else{
            Messagebox.show( "No seleccionaste nada");
        }
     
    }
    
 //------------------------------------------- inicio modificar---------------------------------------------------------------------------------   
    
    @Listen ("onClick=#idmodify")
    public void botonmodificar(Event event){
        
        
        Set<Cperson> Selecteditem = dataModel.getSelection();
        
        if( Selecteditem != null && Selecteditem.size()>0){
            Cperson person = Selecteditem.iterator().next();
            
            Map<String,Object> arg = new HashMap<String,Object>();
            
            arg.put("personmodify", person);
            Window win = ( Window ) Executions.createComponents("/dialog.zul",null,arg); //llama a la pagina dialog como una ventana emergente
            win.doModal();
            
        }else{
        
            Messagebox.show( "No seleccionaste nada");
        }
        
        
    }
    
}
