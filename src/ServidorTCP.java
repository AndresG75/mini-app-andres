import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ServidorTCP {
    String HOST;
    int PUERTO;
    

    public ServidorTCP(String HOST, int PUERTO){
        this.HOST = HOST;
        this.PUERTO = PUERTO;

    };

    public void start(){

        try {
            Log log = new Log("log.txt");

            Lector lectorTXT = new Lector();
            ServerSocket server = new ServerSocket(PUERTO);
            
            while(true){
                Socket sc = server.accept();
                DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF("Introduzca su nombre de usuario");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            
            String leer_comando = rd.readLine();
            log.addcommand(leer_comando, "TCP", PUERTO, "127.0.0.1");

            System.out.println(leer_comando);

            String nombre = leer_comando.split("\\s")[1];
            
            if (lectorTXT.Lectura(nombre)) {
                out.writeUTF("OK");
                log.addcommand("OK", "TCP", PUERTO, "127.0.0.1");
                out.writeUTF("Introduzca su mensaje");
                String mensaje = in.readUTF();
                log.addcommand(mensaje, "TCP", PUERTO, "127.0.0.1");

                
            }else{
                out.writeUTF("Usuario Invalido");
                log.addcommand("Usuario Invalido", "TCP", PUERTO, "127.0.0.1");

                sc.close();
            }
            rd.close();

            }
            

        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }

    };
}
