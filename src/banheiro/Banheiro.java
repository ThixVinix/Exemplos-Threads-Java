package banheiro;

public class Banheiro {

	private boolean estaSujo = true;

	// ** SOMENTE UM CONVIDADO PODE USAR ESTE METODO POR VEZ!* /
	public void fazerNumero1() {
		String nome = Thread.currentThread().getName();

		baterPortaBanheiro(nome);

		synchronized (this) {
			entrarNoBanheiro(nome);

			if (estaSujo) {
				reclamarBanheiroSujo(nome);
				while (estaSujo) {
					esperarLimpezaBanheiro();
				}
				entrarNoBanheiro(nome);
			}
			System.out.println(nome + " esta fazendo o numero 1...");
			determinarTempoUtilizacaoBanheiro(2000); // 2 Seconds
			this.estaSujo = true;
			sairDoBanheiro(nome);
		}
	}

	// ** SOMENTE UM CONVIDADO PODE USAR ESTE METODO POR VEZ!*/
	public void fazerNumero2() {
		String nome = Thread.currentThread().getName();

		baterPortaBanheiro(nome);

		synchronized (this) {
			entrarNoBanheiro(nome);

			if (estaSujo) {
				reclamarBanheiroSujo(nome);
				while (estaSujo) {
					esperarLimpezaBanheiro();
				}
				entrarNoBanheiro(nome);
			}
			System.out.println(nome + " esta fazendo o numero 2...");
			determinarTempoUtilizacaoBanheiro(4000); // 4 Seconds
			this.estaSujo = true;
			sairDoBanheiro(nome);
		}
	}

	public void limpar() {
		String nome = Thread.currentThread().getName();

		baterPortaBanheiro(nome);

		synchronized (this) {
			entrarNoBanheiro(nome);

			if (!estaSujo) {
				System.out.println(nome + " verificou que o banheiro nao esta sujo.");
				return;
			}
			
			System.out.println(nome + " esta limpando o banheiro...");
			determinarTempoUtilizacaoBanheiro(13000); // 13 Seconds
			this.estaSujo = false;

			System.out.println(nome + " esta saindo do banheiro!");
			this.notifyAll();
		}
	}

	private void determinarTempoUtilizacaoBanheiro(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void esperarLimpezaBanheiro() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void baterPortaBanheiro(String nome) {
		System.out.println(nome + " esta batendo na porta do banheiro...");
	}

	private void entrarNoBanheiro(String nome) {
		System.out.println(nome + " esta entrando no banheiro!");
	}

	private void reclamarBanheiroSujo(String nome) {
		System.out.println(
				nome + ": \"ECA! O BANHEIRO ESTA SUJO! Vou esperar la fora ate o que o banheiro esteja limpo!\"");
	}

	private void sairDoBanheiro(String nome) {
		System.out.println(nome + " esta dando descarga...");
		System.out.println(nome + " esta lavando a mao...");
		System.out.println(nome + " esta saindo do banheiro!");
	}

}
