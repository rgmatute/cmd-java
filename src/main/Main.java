package main;

import controlador.controladorFromPrincipal;
import vista.fromPrincipal;

/**
 *
 * @author Ronny Matute
 */
public class Main {

    public static void main(String[] args) {
        fromPrincipal fp = new fromPrincipal();
        controladorFromPrincipal cp = new controladorFromPrincipal(fp);
    }

}
