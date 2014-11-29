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
public class Consumidor {
    public static void main(String[] args) {
        try {
            int i; // Para recibir varios mensajes
            MsgConexion mc; // Conexion propia a cola
            QueueReceiver receptor; // Receptor
            TextMessage m; // Mensaje recibido
            boolean ok; // Comprobacion retorno
            mc = new MsgConexion(); // Crea su objeto MsgConexion
            ok=mc.inicializaCola(); // Inicializa parametros
            if (ok){
                // Prepara receptor sobre cola
                receptor = mc.sesionCola.createReceiver(mc.cola);
                // Recibe los mensajes:
                for (i=0; i<5; i++){
                    m = (TextMessage)receptor.receive(1000);
                    System.out.println("Mensaje recibido:" + m.getText());
                }
            }
            mc.cerrarConexion();
        } catch (JMSException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
