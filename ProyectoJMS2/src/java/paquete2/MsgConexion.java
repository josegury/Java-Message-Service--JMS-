/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete2;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author usuario
 */
public class MsgConexion {
    public String nombreCola="cola"; // Nombre externo de la cola
    public Context contexto=null; // Contexto JNDI
    public QueueConnectionFactory factoria=null; // Factoria de conexiones
    public QueueConnection conexionCola=null; // conexion
    public QueueSession sesionCola=null; // sesion
    public Queue cola = null; // cola de mensajes
    
    public MsgConexion (){
        
    }
    public boolean inicializaCola() throws JMSException{
        if (contexto == null){
            try {
                // Aun no se ha realizado la inicializacion. Una vez realizada
                // no tiene sentido volver a realizarla.
                contexto = new InitialContext(); // Obtiene contexto JNDI
                // Obtiene factoria de conexion a colas (ha debido ser creada externamente)
                factoria = (QueueConnectionFactory) contexto.lookup("jms/factoria");
                // Obtiene la cola (ha debido ser creada externamente)
                cola = (Queue) contexto.lookup(nombreCola);
                // Ahora crea la conexion a la cola
                conexionCola=factoria.createQueueConnection();
                // Crea la sesion
                sesionCola = conexionCola.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
                conexionCola.start(); // Hay que activar la conexion para empezar.                
            } catch (NamingException ex) {
                Logger.getLogger(MsgConexion.class.getName()).log(Level.SEVERE, null, ex);
                contexto=null;
                return false;
            }
        }
        return true;
    }
    public boolean cerrarConexion()
    {
        try {
            conexionCola.close();
            return true;
        } catch (JMSException ex) {
            Logger.getLogger(MsgConexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
