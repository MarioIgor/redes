/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;



import java.io.IOException;




public class ClienteService {
    
    private ComunicacaoTCP c = new ComunicacaoTCP();
    
    public ComunicacaoTCP conectar() {
        
        //c = ComunicacaoTCP.getConexao(ComunicacaoTCP.TCP);
        c.conectar("127.0.0.1", 5560);
        
        return c;
        
    }
        public ComunicacaoTCP conectar(String ip, int porta) {
        
        //c = ComunicacaoFactory.getConexao(tipo);
        
        c.conectar(ip, porta);
        
        return c;
        
    }
    
    public void enviar(FileMessage fm) throws IOException{
        c.enviarMensagem(fm);
        
    }
    
    public boolean desconectar(FileMessage fm) throws IOException{
        
        fm.setAction(Action.DESCONECTAR);
        c.enviarMensagem(fm);
        
        
        return true;
    }
    
}
