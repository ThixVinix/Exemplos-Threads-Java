package banheiro;

public class Principal {

	public static void main(String[] args) {

		Banheiro banheiro = new Banheiro();

		Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "João");
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Maria");
		Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Roberto");
		Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Roberta");

		Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");

		/*
		 * A LIMPEZA SO IRA CONTINUAR EM FUNCIONAMENTO ENQUANTO AS OUTRAS THREADS
		 * ESTIVEREM EM EXECUCAO.
		 */
		limpeza.setDaemon(true);

		convidado1.start();
		convidado2.start();
		convidado3.start();
		convidado4.start();

		limpeza.start();

	}
}
