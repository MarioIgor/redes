package prog;

import app.ClientePrincipal;

public class Cliente {   
    public static void main(String args[]) {              
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientePrincipal().setVisible(true);
            }
        });
    }    
}
