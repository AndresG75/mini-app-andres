import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Servidor {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 19876;
        DataInputStream in;
        DataOutputStream out;
        System.out.println("Mini Aplicacion Andres De Armas");
        System.out.println("-------------------------\n");
        System.out.println("1 - Protocolo TCP");
        System.out.println("2 - Protocolo UDP");
        System.out.println("3 - Salir");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();


        switch (choice) {
            case 1:
            System.out.println("Bienvenido al protocolo TCP");
            ServidorTCP sTcp = new ServidorTCP(HOST, PUERTO);
            sTcp.start();
                break;
            case 2:
            System.out.println("Bienvenido al protocolo UDP");
            ServidorUDP sUdp = new ServidorUDP(PUERTO);
            sUdp.start();
                // Perform "encrypt number" case.
                break;
            case 3:
            System.out.println("Hasta luego");
                // Perform "decrypt number" case.
                break;
            default:
                // The user input an unexpected choice.
        }

       
    }

    
}
