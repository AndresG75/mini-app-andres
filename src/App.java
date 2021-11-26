import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sn = new Scanner(System.in);
        String nombre = sn.next();
        System.out.println("El nombre introducido es "+nombre);
        Lectura(nombre);



    }
    public static boolean Lectura(String nombre){
        File archivo;
        String nombre_archivo = "usuarios.txt";
        FileReader fr;
        BufferedReader br;
        final Charset ENCODING = StandardCharsets.UTF_8;


        try {
            archivo = new File(nombre_archivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            List<String> inlines = Files.readAllLines(Paths.get(nombre_archivo), ENCODING);

            if(inlines.contains(nombre)){
                System.out.println("APROBADO");
                
                br.close();
                fr.close();
                return true;

            }
           
       

        
            
            br.close();
            fr.close();
            System.out.println("No se encontro el nombre "+nombre);

            return false;


        } catch (Exception e) {
            System.out.println("El archivo no se pudo abrir");
            return false;
        }
        
    }
}
