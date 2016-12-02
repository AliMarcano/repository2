package org.test.zk.dialog;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



public class Cdialogcontroller extends SelectorComposer<Component> {

    private static final long serialVersionUID = 2127738451903407979L;
    protected ListModelList<String> sexo = new ListModelList<String>();//select sexo

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
    public void doAfterCompose(Component comp) {

            try {
                
                super.doAfterCompose(comp);
                fecha.setFormat( "dd-MM-yyyy" );//arreglamos el error de formato, dandole un formato deseado en tiempo de ejecucion
                //cargamos al modelo las opciones
                sexo.add( "Femenino" );
                sexo.add( "Masculino" );
                //se las asignamos a el selec
                selectgenero.setModel( sexo );
                //colocamos Femenico como 1a opcion que se muestra para que el selec no se vea vacio
                selectgenero.setSelectedIndex( 0 );
                sexo.addToSelection( "Femenino" );
                
            }
            catch ( Exception e ) {
              
                e.printStackTrace();
            }


     }
    
    @Listen("onClick=#idaceptar")
    public void onClickaceptar (Event event){
        
        //System.out.println("Hello Accept desu");
        Messagebox.show("ID = "+textid.getValue()+" Nombre= "+textnombre.getValue()+" Apellido= "+textapellido.getValue()+" Comentario= "+ textcomenta.getValue(), "Aceptar", Messagebox.OK, Messagebox.INFORMATION);
        windowpersona.detach();
    
    }   
    @Listen("onClick=#idcancelar")
    public void onClickcancelar(Event event){
        //System.out.println("Hello Cancel Des");
        Messagebox.show("Cancelar", "Canlel", Messagebox.OK, Messagebox.EXCLAMATION);
        windowpersona.detach();
    
    }    
    
}
