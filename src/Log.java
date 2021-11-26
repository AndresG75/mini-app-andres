
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Log {

    private BufferedWriter buffered;
    private String ruta;

    public Log(String ruta) throws IOException {
        this.ruta = ruta;
        this.open(true);
    }

    public Log(String ruta, boolean reset) throws IOException {
        this.ruta = ruta;
        this.open(!reset);
    }

    private void open(boolean append) throws IOException {
        this.buffered = new BufferedWriter(new FileWriter(this.ruta, append));
    }

    public void addcommand(String command, String protocol, int port,String ip) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        String formatoFecha = sdf.format(new Date());
        this.open(true);
        this.buffered.write("[" + formatoFecha + "] " + "['" + ip + "'," + port + "] " + "[" + protocol + "]:  " + command + "\n");
        this.close();
    }

    public String[] getcommands() throws FileNotFoundException, IOException {

        ArrayList<String> commandsFile = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(this.ruta));

        String command;
        while ((command = br.readLine()) != null) {
            commandsFile.add(command);
        }
        
        br.close();

        String[] commands = new String[commandsFile.size()];
        
        for (int i = 0; i < commandsFile.size(); i++) {
            commands[i] = commandsFile.get(i);
        }
        
        return commands;
    }
    
    public void resetLog() throws IOException{
        this.open(false);
        this.close();
    }
    
    private void close() throws IOException{
        this.buffered.close();
    }
    
}