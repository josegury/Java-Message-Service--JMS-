/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;

/**
 *
 * @author usuario
 */
public class Productor {
   
    public static void main(String[] args) {   
        try {
            int i; // Para enviar varios mensajes
            MsgConexion mc; // Conexion propia a cola
            QueueSender emisor; // Emisor
            TextMessage m; // Mensaje a enviar
            boolean ok; // Para comprobar retorno de metodo
            mc = new MsgConexion(); // Crea objeto MsgConexion
            ok=mc.inicializaCola(); // Prepara los parametros
            
            if (ok){
                // Prepara emisor
                emisor = mc.sesionCola.createSender(mc.cola);
                // Prepara mensaje
                m=mc.sesionCola.createTextMessage(); // Crea mensaje
                // Hace varios envios
                for (i=0; i<5; i++){
                    m.setText("Hola Mundo"+i); // Contenido mensaje
                    emisor.send(m); // ENVIO MENSAJ
                }
            }
            mc.cerrarConexion();
        } catch (JMSException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
