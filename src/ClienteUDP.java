import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Scanner;
public class ClienteUDP {
    int PORT_DESTINO;

    public ClienteUDP(int port_destino){
        this.PORT_DESTINO = port_destino;

    };


    public void start(){

     try {
        Locale loc = new Locale("es", "ES");

         //  Leer el flujo de bytes de la entrada del teclado
         Scanner sn = new Scanner(System.in, "UTF-8");
         sn.useLocale(loc);
         
    
    
        //  Cree una puerta para que el cliente transmita información, pero no estableció una conexión como TCP
        DatagramSocket clientSocket = new DatagramSocket();
    
        //  Llame a la consulta DNS para obtener la dirección IP correspondiente al nombre de host
        InetAddress IPAddress = InetAddress.getLocalHost();//Si es nulo, obtendrá su propia dirección IP
    
        //  Definir la matriz de bytes que se enviará
        byte[] sendData = new byte[1024];//  La matriz no se puede abrir dinámicamente. . .
    
        //  Definir la matriz de bytes que se recibirá
        byte[] receiveData = new byte[1024];
    
    
        System.out.println("Introduzca su nombre");    

    
        //  Guarde los datos recibidos del teclado primero con una cadena de caracteres
        String nombre_cliente = sn.nextLine();
        String comando = "helloiam " + nombre_cliente + "\n";
        sn.reset();
    
        //  Convierta comando a una cadena a byte y almacénela en sendData
        sendData = comando.getBytes();
        //  Prepárese para enviar datagramas UDP, que contienen información como el contenido y la dirección de destino.
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, PORT_DESTINO);
    
        //  Use esta puerta para enviar datagramas
        clientSocket.send(sendPacket);
    
        //  Prepárese para obtener el datagrama devuelto por el servidor
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
    
        //  Use esta puerta para recibir datagramas enviados por el servidor
        clientSocket.receive(receivePacket);
    
        //  Convierta el datagrama obtenido al tipo de cadena
        String confirmacionServidor = new String(receivePacket.getData());
    
        //  Imprimir contenido recibido del servidor
        System.out.println(confirmacionServidor);

        DatagramPacket paqueteRespuesta = new DatagramPacket(receiveData,
                receiveData.length);
    
        //  Use esta puerta para recibir datagramas enviados por el servidor
        clientSocket.receive(paqueteRespuesta);
    
        //  Convierta el datagrama obtenido al tipo de cadena
        String respuestaServidor = new String(paqueteRespuesta.getData());   
        System.out.println(respuestaServidor);
     
        String mensaje_cliente = sn.nextLine();
        sendData = mensaje_cliente.getBytes();
    
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT_DESTINO);
        clientSocket.send(sendPacket);

    
    
    
    
        //  Cierra esta puerta
        clientSocket.close();
    
     } catch (Exception e) {
         //TODO: handle exception
     }   
    
    }
}
    

