package app;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ComunicacaoTCP {
	//IP
	private InetAddress IPAddress;
	//Porta
	private int porta;
	//Conexao com o servidor
	private Socket connectionSocket;
	//Cliente conectado ao servidor
	private Socket clienteSocket;
	private ServerSocket serverSocket;
	//Entrada e saida de dados
	private ObjectInputStream entrada;
	private ObjectOutputStream saida;
	//Lista com todos os clientes conecatodos ao servidor
	private ArrayList<ComunicacaoTCP> clientes = null;
        
        public String erro = "";
	

	public ComunicacaoTCP conectar (String ip, int porta) {
		          System.out.println("api.ComunicacaoTCP.conectar()" + ip+":"+ +porta);
                try {
			return conectar(InetAddress.getByName(ip), porta);
		} catch (UnknownHostException e) {
		}
		return this;
	}

	/**
	 * 
     * @param ip
     * @param porta
     * @return 
	 */
	public ComunicacaoTCP conectar(InetAddress ip, int porta) {
                this.erro = "";
		try {
			this.IPAddress = ip;
			this.porta = porta;
			this.connectionSocket = new Socket(this.IPAddress ,this.porta);
			this.connectionSocket.setKeepAlive(true);
                        System.out.println("Comunicacao conectar deu certo para o ip "  +ip+"  porta "+porta);
		} catch (IOException e) {
                        erro = "Não foi possível conectar ao servidor.";
			System.out.println("[LOG] - ComunicacaoTCP - conectar() - Falha ao conectar!");
		}
		return this;
	}
	
	public ComunicacaoTCP iniciarServidor(int porta) {
                System.out.println("Passou a porta iniciarServidor:  "+porta);
		try {
                        System.out.println("Porta passada: "+porta);
			this.setClientes(new ArrayList<>());
                        System.out.println("criou um array de cliente  "+clientes);
			this.serverSocket = new ServerSocket(porta);
                        
			System.out.println("[SERVER TCP] - Iniciado! IP "+serverSocket.getInetAddress().getHostAddress()+" Porta "+serverSocket.getLocalPort());
		} catch (IOException e) {
			System.out.println("[LOG] - ComunicacaoTCP - iniciarServido() - Falha ao iniciar o servido!");
		}
		return this;
	}
	
	/**
	 * 
     * @param mensagem 
     * @return  
     * @throws java.io.IOException 
     * 
	 */
	public boolean enviarMensagem(Object mensagem) throws IOException{
		boolean enviado = true;
		
			if(this.connectionSocket != null && ! this.connectionSocket.isClosed()) {
				this.saida = new ObjectOutputStream(this.connectionSocket.getOutputStream());
				this.saida.writeObject(mensagem);
			} else if(this.clienteSocket != null && ! this.clienteSocket.isClosed()) {
				this.saida = new ObjectOutputStream(this.clienteSocket.getOutputStream());
				this.saida.writeObject(mensagem);
			} 
	
		return enviado;
	}

	
	public Object receberMensagem(){
		Object retorno = null;
		try {
			if(this.connectionSocket != null){
                            
				this.entrada = new ObjectInputStream(this.connectionSocket.getInputStream());
                                retorno = this.entrada.readObject();
			} else if(this.clienteSocket != null){
				this.entrada = new ObjectInputStream(this.clienteSocket.getInputStream());
                                retorno = this.entrada.readObject();                               
			} 
 
		} catch (IOException e) {
			System.out.println("[LOG] - ComunicacaoTCP - receberMensagem() - Conexao Fechada!");
			
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                  
            }
            
		return retorno;
	}

	public ComunicacaoTCP conectarCliente() {
		ComunicacaoTCP cliente = null;
		try {
			cliente = new ComunicacaoTCP();
			this.clienteSocket = this.serverSocket.accept();
			this.clienteSocket.setKeepAlive(true);
			this.IPAddress = clienteSocket.getInetAddress();
			this.porta = clienteSocket.getPort();
			//Cria um novo objeto do tipo ComunicacaoTCP e seta os parametros do cliente conectodo
			cliente.setConnectionSocket(this.clienteSocket);
			cliente.setIPAddress(this.IPAddress);
			cliente.setPorta(this.porta);
			//Adciona o cliente a lista
			clientes.add(cliente);
		} catch (IOException e) {
		}
		//System.out.println("[LOG] conectarCliente() - Cliente conectado TCP!");
		return cliente;
	}
	
	/**
	 * 
     * @return 
	 */
	public boolean fecharConexao() {
		boolean fechar = true;
		try {
			this.entrada.close();
			this.saida.close();
			if(this.clienteSocket != null){
				this.clienteSocket.close();
			}
			if(this.connectionSocket != null){
				this.connectionSocket.close();
			}
		} catch (IOException e) {
			fechar = false;
		}
		
		return fechar;
	}

	/**
	 * 
     * @return 
	 */
	public boolean isConexaoEncerrada() {
		if(this.serverSocket != null){
			return this.serverSocket.isClosed();
		}
		if(this.clienteSocket != null){
			return this.clienteSocket.isClosed();
		}
		if(this.connectionSocket != null){
			return this.connectionSocket.isClosed();
		}
		return false;
	}
	
	/**
	 * Get r Set
     * @return 
	 */
	
	public Socket getConnectionSocket() {
		return connectionSocket;
	}
	public void setConnectionSocket(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}
	public Socket getClienteSocket() {
		return clienteSocket;
	}
	public void setClienteSocket(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}
	public ObjectInputStream getEntrada() {
		return entrada;
	}
	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}
	public ObjectOutputStream getSaida() {
		return saida;
	}
	public void setSaida(ObjectOutputStream saida) {
		this.saida = saida;
	}
	public InetAddress getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(InetAddress iPAddress) {
		IPAddress = iPAddress;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public InetAddress getIp() {
		return this.IPAddress;
	}
	public int getPorta() {
		return this.porta;
	}
	public ArrayList<ComunicacaoTCP> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<ComunicacaoTCP> clientes) {
		this.clientes = clientes;
	}
	
}
