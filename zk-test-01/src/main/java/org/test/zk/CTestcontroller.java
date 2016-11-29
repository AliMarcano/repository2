package org.test.zk;

import org.test.zk.dao.Cperson;
import org.zkoss.zk.ui.Component;/** libreria component*/
import org.zkoss.zk.ui.event.Event;/** libreria del evento*/
import org.zkoss.zk.ui.select.SelectorComposer;/** libreria del select*/
import org.zkoss.zk.ui.select.annotation.Listen;/** libreria del listen*/
import org.zkoss.zk.ui.select.annotation.Wire;/** libreria del wire*/
import org.zkoss.zul.Button; /** libreria del boton*/
import org.zkoss.zul.ItemRenderer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;/** libreria del la ventana*/


public class CTestcontroller extends SelectorComposer<Component> implements ItemRenderer<Cperson> {

    /**
     *clase que controla la vista test 01 
     */
    private static final long serialVersionUID = 8795469423415785130L;
    
    @Wire
    Button prueba1;
    @Wire("#superprueba")/**de esta forma se hace una busqueda de el boton en el .zul y se lo asocias a una variable diferente*/
    Button prueba2;
    @Wire
    Button obtengo1;
    @Wire
    Window windowtest01;
    @Wire
    Selectbox seleccion;
    @Wire
    Selectbox person;
    
    protected ListModelList<String> dataModel = new ListModelList<String>();
    protected ListModelList<Cperson> dataModelpersona = new ListModelList<Cperson>();  
    
    @Listen("onClick =#prueba1")
    public void onclickprueba(Event event){/**recoje el evento de el boton al que esta asociado el onclick del listen*/
        
       // windowtest01.setTitle( "Click Button test 01" );/**cambia el titulod e la ventana*/
       // prueba2.setLabel( "yea baby" );
       dataModelpersona.add( new Cperson("24437593","Ali","Fidel") ) ;
       dataModelpersona.add( new Cperson("21xxxxxx","German","Isaac") ) ;
       dataModelpersona.add( new Cperson("24xxxxxx","Juan","Manuel") ) ;
       dataModelpersona.add( new Cperson("24xxxxxx","Roger","nose D:") ) ;
       
       person.setModel( dataModelpersona );
       person.setItemRenderer( this );
        
    }
    
    @Listen("onClick =#superprueba")
    public void onclickprueba2(Event event){/**recoje el evento de el boton al que esta asociado el onclick del listen*/
        
        //windowtest01.setTitle( "boton 2" );/**cambia el titulod e la ventana*/
        //prueba1.setLabel( "funciono" );
        
        dataModel.add("1");/** ahora este boton serbira para cargar el valor del selectbox */
        dataModel.add("2");
        dataModel.add("3");
        dataModel.add("4");
        dataModel.add("5");
        
        seleccion.setModel( dataModel );
        dataModel.addToSelection( "1" );
        seleccion.setSelectedIndex( 0 );
    }
    
    @Listen ("onSelect =#seleccion")
    public void agregar (Event event){
        
        if(seleccion.getSelectedIndex()>=0){
          windowtest01.setTitle( dataModel.get( seleccion.getSelectedIndex() ) );    
        }
        
        
    }
    
    @Listen("onClick =#obtengo1")
    public void onclickobtengo1(Event event){/**recoje el evento de el boton al que esta asociado el onclick del listen*/
        
        windowtest01.doModal();/**ajusta la ventana y la vielve deslisable por la pantalla*/
        
    }
    @Listen ("onSelect =#person")
    public void selecperon (Event event){
        
        if(person.getSelectedIndex()>=0){
          Cperson selecionada =dataModelpersona.get( person.getSelectedIndex() ); 
            
          windowtest01.setTitle( selecionada.getId() );    
        }
        
        
    }

    @Override
    public String render( Component arg0, Cperson arg1, int arg2 ) throws Exception {
        
        // TODO Auto-generated method stub
        return arg1.getName()+" "+arg1.getSecondname();
    }    
    
    
}
