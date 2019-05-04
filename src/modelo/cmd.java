package modelo;

import controlador.controladorFromPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ronny Matute
 */
public class cmd implements Runnable {

    private static String query;
    private static boolean control = false;

    public cmd() {
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public void getLine(String query) {
        cmd.query = query;
        cmd.control = true;
    }

    @Override
    public void run() {
        while (control) {
            Runtime r = Runtime.getRuntime();
            String salida = null;
            int i = 1;
            try {
                String command = (System.getProperty("os.name").contains("Windows") ? "cmd /c " : "") + query;
                Process p = r.exec(command);
                InputStreamReader entrada = new InputStreamReader(p.getInputStream());
                BufferedReader cmdInput = new BufferedReader(entrada);
                //mostramos la salida del comando
                controladorFromPrincipal.fp.txtArea.append("\n " + i + ">  " + cmdInput.readLine());
                i++;
                while ((salida = cmdInput.readLine()) != null) {
                    controladorFromPrincipal.fp.txtArea.append("\n " + i + ">  " + salida);
                    controladorFromPrincipal.fp.txtArea.setCaretPosition(controladorFromPrincipal.fp.txtArea.getDocument().getLength());
                    i++;
                }
                controladorFromPrincipal.fp.txtArea.append("\n " + i + ">  " + "END");
                cmd.control = false;
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }
}
