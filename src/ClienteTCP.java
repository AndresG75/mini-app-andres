import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClienteTCP {
    String HOST;
    int PUERTO;

    public ClienteTCP(String HOST, int PUERTO){
        this.HOST = HOST;
        this.PUERTO = PUERTO;


    };

    public void start(){

        try {
            Socket sc = new Socket(HOST, PUERTO);
            Locale loc = new Locale("es", "ES");

            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
            Scanner sn = new Scanner(System.in, "UTF-8");
            sn.useLocale(loc);
            
            String respuesta_servidor = in.readUTF();
            System.out.println(respuesta_servidor);
            
            
            String nombre_cliente = sn.nextLine();

            System.out.println(nombre_cliente);

            sn.reset();


            String command = "helloiam " + nombre_cliente + "\n";
            wr.write(command);    
            wr.flush();        
            respuesta_servidor = in.readUTF();
            System.out.println(respuesta_servidor);
            respuesta_servidor = in.readUTF();
            System.out.println(respuesta_servidor);
            String mensaje_a_enviar = sn.nextLine();
            System.out.println(mensaje_a_enviar);

            out.writeUTF(mensaje_a_enviar);
            sn.reset();



            sc.close();


        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }

    };

     
}
