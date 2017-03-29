package app;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ClientePrincipal extends javax.swing.JFrame {

  
    //private Cliente cliente;
    private ComunicacaoTCP comunicacao;
    //private String[] listaCliente;
    private FileMessage mensagem;
    private String msg=null;
    private File arquivoPreparado;
    private ClienteService service;
    private String ip;
    private int porta;
    private String nome;
    private DefaultListModel listaModel;

    //private FileMessage mensagem;
    public ClientePrincipal() {

        //this.tipo = tipo;
        //this.ip = "";
        this.porta = 6655;
        this.listaModel = new DefaultListModel();

        initComponents();

    }

    private class ListenerCliente implements Runnable {

        private ComunicacaoTCP comunicacao;

        public ListenerCliente(ComunicacaoTCP comunicacao) {
            this.comunicacao = comunicacao;

        }

        @Override
        public void run() {
            FileMessage fm ;

            
                while ((fm = (FileMessage) comunicacao.receberMensagem()) != null) {
                    Action action = fm.getAction();

                    switch (action) {
                        case CONECTAR:
                            System.out.println(nome + " conectou...");
                            break;
                        case DESCONECTAR:
                            desconectar();
                            atualizarListaUsuarios(fm);
                            break;
                        case LISTA_CLIENTES:
                            System.out.println("passou no atualizar clientes");
                            atualizarListaUsuarios(fm);
                            break;
                        case ENVIAR_PARA_UM:
                            receber(fm);
                            break;
                        case ENVIAR_PARA_TODOS:
                            receber(fm);
                            break;
                        default:
                            break;
                    }

                }
        }

    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextIpServidor = new javax.swing.JTextField();
        jButtonConectar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jButtonEnviar1 = new javax.swing.JButton();
        jTextFieldNome2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListArquivosRecebidos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsuarios = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setName(""); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nome:");
        jPanel2.add(jLabel1);

        jTextFieldNome.setPreferredSize(new java.awt.Dimension(150, 28));
        jPanel2.add(jTextFieldNome);

        jLabel2.setText("IP do servidor:");
        jPanel2.add(jLabel2);

        jTextIpServidor.setPreferredSize(new java.awt.Dimension(150, 28));
        jTextIpServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextIpServidorActionPerformed(evt);
            }
        });
        jPanel2.add(jTextIpServidor);

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonConectar);

        jButtonSair.setText("Sair");
        jButtonSair.setEnabled(false);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSair);

        jButtonEnviar1.setText("Enviar");
        jButtonEnviar1.setEnabled(false);
        jButtonEnviar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviar1ActionPerformed(evt);
            }
        });

        jTextFieldNome2.setEditable(false);
        jTextFieldNome2.setPreferredSize(new java.awt.Dimension(150, 28));
        jTextFieldNome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNome2ActionPerformed(evt);
            }
        });

        jListArquivosRecebidos.setModel(listaModel);
        jListArquivosRecebidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListArquivosRecebidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListArquivosRecebidos);

        jListUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListUsuarios);

        jLabel3.setText("Mensagens");

        jLabel4.setText("Usuarios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jTextFieldNome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonEnviar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEnviar1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed
        conectar();
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        desconectar();
        listaModel.clear();
        habilitarTelaConexao(false);
        
        
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jListArquivosRecebidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListArquivosRecebidosMouseClicked
        if(evt.getClickCount() == 2){
            abriArquivo((String) jListArquivosRecebidos.getSelectedValue());
           
        }
    }//GEN-LAST:event_jListArquivosRecebidosMouseClicked

    private void jButtonEnviar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviar1ActionPerformed
        
       
        msg = jTextFieldNome2.getText();
        
        System.out.println("Mensagem da dando certo     "+msg);
        if (msg != null) {

            System.out.println("botao enviar - " + msg == null);
            int opt = jListUsuarios.getSelectedIndex();
            if (opt != -1) {

                String dest = (String) jListUsuarios.getSelectedValue();
                FileMessage fm = new FileMessage(dest, nome, msg, Action.ENVIAR_PARA_UM);
                jListUsuarios.clearSelection();
                enviar(fm);
            } else {

                FileMessage fm = new FileMessage(nome, msg, Action.ENVIAR_PARA_TODOS);
                enviar(fm);

            }
        }
        listaModel.addElement(nome+" falou: "+msg);        
        jListArquivosRecebidos.setModel(listaModel);
        jTextFieldNome2.setText(null);
        
    }//GEN-LAST:event_jButtonEnviar1ActionPerformed

    private void jTextFieldNome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNome2ActionPerformed
        // jTextFieldNomeDoArquivo.setText(arquivoPreparado.getName());
    }//GEN-LAST:event_jTextFieldNome2ActionPerformed

    private void jTextIpServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIpServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIpServidorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JButton jButtonEnviar1;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jListArquivosRecebidos;
    private javax.swing.JList jListUsuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNome2;
    private javax.swing.JTextField jTextIpServidor;
    // End of variables declaration//GEN-END:variables

    private void conectar() {

        nome = jTextFieldNome.getText();
        ip = jTextIpServidor.getText();
        FileMessage mensagem = new FileMessage();
        //se campo nome nao for vazio
        if (!nome.isEmpty() && !ip.isEmpty()) {

            mensagem = new FileMessage(nome);

            mensagem.setAction(Action.CONECTAR);

            if (comunicacao == null) {

                this.service = new ClienteService();
                this.comunicacao = this.service.conectar(ip, porta); 
                if (this.comunicacao.erro.length() > 0) {
                    JOptionPane.showMessageDialog(this, this.comunicacao.erro);
                } else {
                    new Thread(new ListenerCliente(comunicacao)).start();
                    enviar(mensagem);
                    habilitarTelaConexao(true);
                }                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Preencha seu nome e o ip do servidor.");
        }
    }

    private void enviar(FileMessage fm) {

        fm.setRemetente(nome);
        try {
            this.comunicacao.enviarMensagem(fm);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar o arquivo\n" + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            arquivoPreparado = null;

            //jTextFieldNomeDoArquivo.setText("");
        }

    }

    private void prepararParaEnviar() {


        JFileChooser jfc = new JFileChooser();
        
        int opt = jfc.showOpenDialog(ClientePrincipal.this);

        if (opt == JFileChooser.APPROVE_OPTION) {

            arquivoPreparado = jfc.getSelectedFile();
            //jTextFieldNomeDoArquivo.setText(arquivoPreparado.getName());
        }
    }

    private void desconectar() {

        try {
            FileMessage fm = new FileMessage(nome);
            fm.setAction(Action.DESCONECTAR);
            comunicacao.enviarMensagem(fm);

            comunicacao.fecharConexao();
            comunicacao = null;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar \n", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void habilitarTelaConexao(boolean b) {
        this.jButtonConectar.setEnabled(!b);
        this.jTextFieldNome.setEditable(!b);
        //this.jButtonEnviar.setEnabled(b);
        this.jButtonSair.setEnabled(b);
        //this.jButtonSelecionar.setEnabled(b);
        this.jListUsuarios.setEnabled(b);
        //this.jTextFieldNomeDoArquivo.setEditable(!b);
       this.jButtonEnviar1.setEnabled(b); 
       this.jTextFieldNome2.setEditable(b);
      

    }

    protected void atualizarListaUsuarios(FileMessage fm) {

        System.out.println(new Date());
        Set<String> setAtivos = fm.getListaClientes();


        String[] array = setAtivos.toArray(new String[setAtivos.size()]);
        this.jListUsuarios.setListData(array);

    }
private void receberArquivo(FileMessage fm){
   int op = JOptionPane.showConfirmDialog(null, "Você recebeu uma aquivo de " + fm.getRemetente() + " deseja salvar?\nNome do arquivo: " + fm.getFile().getName(), "Arquivo recebido:" + fm.getFile().getName(), JOptionPane.YES_NO_OPTION);
           //se deseja salvar
        if (op == JOptionPane.YES_OPTION) {
            
            
            
            JFileChooser jfc = new JFileChooser();
            
            jfc.setSelectedFile(fm.getFile());
            int salvar = jfc.showSaveDialog(this);
            
            
            if (salvar == JFileChooser.APPROVE_OPTION) {
                try {
                    
                   
                    
                    FileInputStream fileInputStream = new FileInputStream(fm.getFile());
                    FileOutputStream fileOutputStream = new FileOutputStream(jfc.getSelectedFile());
                    FileChannel fin = fileInputStream.getChannel();
                    FileChannel fout = fileOutputStream.getChannel();
                    long size = fin.size();
                    fin.transferTo(0, size, fout);

                    listaModel.addElement(jfc.getSelectedFile().getAbsolutePath());

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo de entrada\n" + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível salvar o arquivo\n" + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }
    public void abriArquivo(String caminho){
        try { 
            java.awt.Desktop.getDesktop().open( new File( caminho) );
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo!");
            Logger.getLogger(ClientePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    private void receber(FileMessage fm) {
       
        
        if(fm.getMsg()!= null){
            
            System.out.println("esta recebendo uma msg de "+fm.getDestinatario()+"   "+fm.getMsg());
            listaModel.addElement(fm.getRemetente()+" falou: "+fm.getMsg());        
            jListArquivosRecebidos.setModel(listaModel);
            
        }else{
            int op = JOptionPane.showConfirmDialog(null, "Você recebeu uma aquivo de " + fm.getRemetente() + " deseja salvar?\nNome do arquivo: " + fm.getFile().getName(), "Arquivo recebido:" + fm.getFile().getName(), JOptionPane.YES_NO_OPTION);
                  
            //se deseja salvar
            if (op == JOptionPane.YES_OPTION) {
                JFileChooser jfc = new JFileChooser();
            
                jfc.setSelectedFile(fm.getFile());
                int salvar = jfc.showSaveDialog(this);
            
            
                if (salvar == JFileChooser.APPROVE_OPTION) {
                    
                    
                    try {
                      
                    
                        FileInputStream fileInputStream = new FileInputStream(fm.getFile());
                        FileOutputStream fileOutputStream = new FileOutputStream(jfc.getSelectedFile());
                        FileChannel fin = fileInputStream.getChannel();
                        FileChannel fout = fileOutputStream.getChannel();
                        long size = fin.size();
                        fin.transferTo(0, size, fout);

                        listaModel.addElement(jfc.getSelectedFile().getAbsolutePath());

                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo de entrada\n" + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Não foi possível salvar o arquivo\n" + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }
    }


}
