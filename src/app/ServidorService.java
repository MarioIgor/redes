/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Mariano
 */
public class ServidorService {
    
    private ComunicacaoTCP comunicacaoServidor;
    private Map<String,ComunicacaoTCP> map;
    private FileMessage msg;
       
    public ServidorService( int porta) {
        this.map = new HashMap<>();
        this.comunicacaoServidor = new ComunicacaoTCP();
        System.out.println("Passou a porta no ServidorService: "+porta);
        
        ComunicacaoTCP iniciarServidor;
        iniciarServidor = comunicacaoServidor.iniciarServidor(porta);
        
        System.out.println("Passou a comunicacaoServidor.iniciarServidor(porta);: ");
        
        while (true) {
            new Thread(new ListenerServidor()).start();        
        }
    }
    public void desconectarTodos() {
        try{
       Set<String> clientesAtivos = new HashSet<>();
       if (map.size() > 0) {
                map.entrySet().forEach((entry) -> {
                    String key = entry.getKey();
                    ComunicacaoTCP value = entry.getValue();
                    clientesAtivos.add(key);
                });
                FileMessage f = new FileMessage();
                f.setListaClientes(clientesAtivos);
                f.setAction(Action.LISTA_CLIENTES);
                comunicacaoServidor.enviarMensagem(f);
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar \n", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        }
    
   
        
    

    private class ListenerServidor implements Runnable {

        private final ComunicacaoTCP c;
        private FileMessage mensagem;

        private ListenerServidor() {

            c = comunicacaoServidor.conectarCliente();
            
        }

        @Override
        public void run() {            
            while (true) {
                receber();
            }
        }

        private void enviar(FileMessage mensagem) {
            try {
                System.out.println("app.ServidorService.ListenerServidor.enviar()");
                System.out.println("ENVIAR   "+ mensagem.getRemetente());
                map.get(mensagem.getDestinatario()).enviarMensagem(mensagem);
            } catch (IOException ex) {
                System.out.println("Erro ao enviar o arquivo para " + mensagem.getDestinatario());
            }
        }

        private void enviarParaTodos(FileMessage mensagem) {
            System.out.println("app.ServidorService.ListenerServidor.enviarParaTodos()");
            map.entrySet().stream().map((entry) -> {
                String key = entry.getKey();
                ComunicacaoTCP value = entry.getValue();
                mensagem.setDestinatario(key);
                return key;
            }).filter((key) -> (!key.equals(mensagem.getRemetente()))).forEachOrdered((_item) -> {
                enviar(mensagem);
            });
        }
        

        private void conectar(FileMessage m) {

            try {
                //adiciona no map de clientes ativos com o objeto comunicacao
                map.put(m.getRemetente(), c);
                comunicacaoServidor.enviarMensagem(m);

                //adiciona no set de cliente ativos e retorna a confirmacaco para o cliente
                c.enviarMensagem(mensagem);
                atualizarClientesAtivos();

            } catch (IOException ex) {
                System.out.println("Nao foi possivel conectar!");
            }

        }

        private void desconectar(FileMessage mensagem) {

            map.get(mensagem.getRemetente()).fecharConexao();
            map.remove(mensagem.getRemetente());

            System.out.println(mensagem.getRemetente() + " desconectou");
            atualizarClientesAtivos();

        }
        
        

        public void atualizarClientesAtivos() {

            Set<String> clientesAtivos = new HashSet<>();

            if (map.size() > 0) {
                map.entrySet().forEach((entry) -> {
                    String key = entry.getKey();
                    ComunicacaoTCP value = entry.getValue();
                    clientesAtivos.add(key);
                });
                FileMessage f = new FileMessage();
                f.setListaClientes(clientesAtivos);
                f.setAction(Action.LISTA_CLIENTES);
                enviarParaTodos(f);
            }

        }

        public void receber() {

            
            mensagem = (FileMessage) c.receberMensagem();

            while (mensagem != null) {

                switch (mensagem.getAction()) {
                    case CONECTAR:
                        conectar(mensagem);
                        break;
                    case DESCONECTAR:
                        desconectar(mensagem);
                        break;
                    case ENVIAR_PARA_UM:
                        enviar(mensagem);
                        break;
                    case ENVIAR_PARA_TODOS:
                        enviarParaTodos(mensagem);
                        break;
                    default:
                        break;
                }


                mensagem = (FileMessage) c.receberMensagem();
           }

        }
         

    }


}
