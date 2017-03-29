package prog;
import app.ServidorPrincipal;
import app.ServidorService;

public class Servidor {
     public static void main(String[] args) {         
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorPrincipal().setVisible(true);
            }
        });                
    }
}
