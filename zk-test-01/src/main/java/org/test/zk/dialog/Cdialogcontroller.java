package org.test.zk.dialog;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.test.zk.dao.Cperson;
import org.test.zk.manager.Cmanagercontroller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Cdialogcontroller extends SelectorComposer<Component> {
    
    private static final long serialVersionUID = 2127738451903407979L;
    
    protected Cmanagercontroller mana = new Cmanagercontroller();
    
    protected ListModelList<Cperson> dataModel = new ListModelList<Cperson>();
    
    protected ListModelList<String> sexo = new ListModelList<String>();//select sexo
    
    protected Cperson person;
    protected Component botonmo;
    protected Component add;
  
    @Wire
    Window windowpersona;
    
    @Wire
    Label labelid;
    
    @Wire
    Textbox textid;
    
    @Wire
    Label labenombre;
    
    @Wire
    Textbox textnombre;
    
    @Wire
    Label labeapellido;
    
    @Wire
    Textbox textapellido;
    
    @Wire
    Label labegenero;
    
    @Wire
    Selectbox selectgenero;
    
    @Wire
    Label labelfecha;
    
    @Wire
    Datebox fecha;
    
    @Wire
    Label labelcomen;
    
    @Wire
    Textbox textcomenta;
    
    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
            
            super.doAfterCompose( comp );
            fecha.setFormat( "yyyy-MM-dd" );//arreglamos el error de formato, dandole un formato deseado en tiempo de ejecucion
            //cargamos al modelo las opciones
            sexo.add( "Femenino" );//otrocomentario
            sexo.add( "Masculino" );
            //se las asignamos a el selec
            selectgenero.setModel( sexo );
            //colocamos Femenico como 1a opcion que se muestra para que el selec no se vea vacio
            selectgenero.setSelectedIndex( 0 );
            sexo.addToSelection( "Femenino" );
            textcomenta.setValue( "inserte comentario" );
            
            final Execution execution = Executions.getCurrent();
            
            person = ( Cperson ) execution.getArg().get( "personmodify" );
            botonmo = ( Component ) execution.getArg().get( "idmodify" );
            add = ( Component ) execution.getArg().get( "agregar" );
            
            
            if(person!=null){
                textid.setValue( person.getId() );
                textnombre.setValue( person.getName() );
                textapellido.setValue( person.getSecondname() );
                if ( person.getGenero() == 0 ) {
                    sexo.addToSelection( "Femenino" );
                }
                else {
                    sexo.addToSelection( "Masculino" );
                }
                fecha.setValue( java.sql.Date.valueOf( person.getNacio() ) );
                textcomenta.setValue( person.getComentario() );//aqui cargamos todo a la vista
                 
            }
               
        }
        catch ( Exception e ) {
            
            e.printStackTrace();
        }
    }
    
    @Listen( "onClick=#idaceptar" )
    public void onClickaceptar( Event event ) {
                
              
               LocalDate nacio = new java.sql.Date(fecha.getValue().getTime()).toLocalDate();
               
               //fecha.setValue( java.sql.Date.valueOf( person.getNacio() ) );
               if(selectgenero.getSelectedIndex() == 1){
                   
                   person = new Cperson( textid.getValue(), textnombre.getValue(), textapellido.getValue(), 1,nacio  , textcomenta.getValue() );
                   
               }else{
                   person = new Cperson( textid.getValue(), textnombre.getValue(), textapellido.getValue(), 0, nacio , textcomenta.getValue() );
               }
               //person.setNacio( java.sql.Date.valueOf());
               if(botonmo==null){
                   Events.echoEvent( new Event( "onAgregar", add, person ) ); 
               }
               else{
                   Events.echoEvent( new Event( "onFinaldialog", botonmo, person ) );    
               }
                windowpersona.detach();//detach se encarga de cerrar la ventana   
    
    }
    
    @Listen( "onClick=#idcancelar" )
    public void onClickcancelar( Event event ) {
        
        //System.out.println("Hello Cancel Des");
        Messagebox.show( "Cancelar", "Canlel", Messagebox.OK, Messagebox.EXCLAMATION );//mmessagebox muestra una ventana emergente con un mensaje
        windowpersona.detach();
        
    }
    
}
