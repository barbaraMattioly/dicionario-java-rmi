public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {

    //Identificador único da classe serializada (classe convertida em uma sequência de bytes para ser transmitida pela rede) utilizado para garantir que a classe possa ser desserializada corretamente, mesmo que ela seja alterada
	private static final long serialVersionUID = 1L;

    //Variável utlizada para manter um controle do número total de operações realizadas
	private int contador = 0;

	public DicionarioServant() throws java.rmi.RemoteException {
		super();
	}

	public double somar(double a, double b) throws java.rmi.RemoteException {
		return a + b + contador++;
	}

	public double subtrair(double a, double b) throws java.rmi.RemoteException {
		return a - b + contador++;
	}

	public double multiplicar(double a, double b) throws java.rmi.RemoteException {
		return a * b + contador++;
	}

	public double dividir(double a, double b) throws java.rmi.RemoteException {
		return a / b + contador++;
	}
}