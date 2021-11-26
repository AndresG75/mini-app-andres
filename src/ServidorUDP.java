import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

    byte[] buffer = new byte[1024];
      //  Crear una puerta en el puerto 19876
      int PUERTO;
      public ServidorUDP(int PUERTO){
        this.PUERTO = PUERTO;

       };

    public void start(){
        try {
          //  Definir la matriz de bytes que se enviará
        byte[] sendData = new byte[1024];//  La matriz no se puede abrir dinámicamente. . .
    
        //  Definir la matriz de bytes que se recibirá
        byte[] receiveData = new byte[1024];
            DatagramSocket serverSocket = new DatagramSocket(PUERTO);
            Log log = new Log("log.txt");
            Lector lectorTXT = new Lector();


            System.out.println("Conectado");

            while(true){
                DatagramPacket recievePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(recievePacket);
                String comando = new String(recievePacket.getData());
                System.out.println(comando);
                int clientPort = recievePacket.getPort();
                InetAddress direccionClient = recievePacket.getAddress();
                String[] cadena = comando.split("\\s");
                log.addcommand(cadena[0]+cadena[1], "UDP", clientPort, "127.0.0.1");


                //Traducir comando
                String nombre = comando.split("\\s")[1];
                System.out.println("Este es el nombre "+nombre);




                if (lectorTXT.Lectura(nombre)) {

                    sendData = "OK".getBytes();
                    
                    DatagramPacket respuestaAlCliente = new DatagramPacket(sendData, sendData.length, direccionClient, clientPort);
                    serverSocket.send(respuestaAlCliente);

                    log.addcommand("OK", "UDP", clientPort, "127.0.0.1");
 
                    byte[] pedirMensaje = new String("Introduzca su mensaje").getBytes();
                    DatagramPacket pedirMensajePaquete = new DatagramPacket(pedirMensaje, pedirMensaje.length, direccionClient, clientPort);

                    serverSocket.send(pedirMensajePaquete);

                    recievePacket = new DatagramPacket(receiveData,
                    receiveData.length);

                    serverSocket.receive(recievePacket);

                    String mensaje_cliente = new String(recievePacket.getData());
                    
                    log.addcommand(mensaje_cliente, "UDP", clientPort, "127.0.0.1");
    
                    
                }else{
                    buffer = "Usuario Invalido".getBytes();
                    
                    DatagramPacket respuestaCliente = new DatagramPacket(buffer, buffer.length, direccionClient, clientPort);
                    serverSocket.send(respuestaCliente);

                    log.addcommand("Usuario Invalido", "UDP", clientPort, direccionClient.toString());
    
                    serverSocket.close();
                }
    
    

    
            }
               
    
            

            // while (true) {
   
            //     //  Definir la matriz de bytes de los datos recibidos.
            //     byte[] receiveData = new byte[1024];
    
            //     //  Definir una matriz de bytes para enviar datos
            //     byte[] sendData = new byte[1024];

     

            //     DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            //     //  Convertir el datagrama UDP recibido a String
            //     String comando = new String(receivePacket.getData());
                
            //         //  Obtenga la dirección IP de origen del datagrama UDP recibido
            //         InetAddress IPAddress = receivePacket.getAddress();
            //         //  Obtenga el número de puerto de origen del datagrama UDP recibido
            //         int port_client = receivePacket.getPort();

            //     log.addcommand(comando, "UDP", port_client, "127.0.0.1");


            //     //Traducir comando
            //     String nombre = comando.split("\\s")[1];


            //     Lector lectorTXT = new Lector();


            //     if (lectorTXT.Lectura(nombre)) {

            //         sendData = "OK".getBytes();
                    
            //         DatagramPacket mensajeAlCliente = new DatagramPacket(sendData, sendData.length, IPAddress, port_client);
            //         serverSocket.send(mensajeAlCliente);

            //         log.addcommand("OK", "UDP", port_client, IPAddress.toString());
            //         sendData = "Introduzca su nombre".getBytes();

            //         mensajeAlCliente = new DatagramPacket(sendData, sendData.length, IPAddress, port_client);
            //         serverSocket.send(mensajeAlCliente);   
                    
            //         receivePacket = new DatagramPacket(receiveData,
            //         receiveData.length);

            //         serverSocket.receive(receivePacket);

            //         String mensaje_cliente = new String(receivePacket.getData());
                    
            //         log.addcommand(mensaje_cliente, "UDP", port_client, IPAddress.toString());
    
                    
            //     }else{
            //         sendData = "Usuario Invalido".getBytes();
                    
            //         DatagramPacket mensajeAlCliente = new DatagramPacket(sendData, sendData.length, IPAddress, port_client);
            //         serverSocket.send(mensajeAlCliente);

            //         log.addcommand("Usuario Invalido", "UDP", port_client, IPAddress.toString());
    
            //         serverSocket.close();
            //     }
    
    

    
            // }
               
           } catch (Exception e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
           }

    }
       

         
           
      
}
