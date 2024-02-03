import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.SecureRandom;



class jogador{
	private String nome;
	private int point;
	jogador(String nome){
		if(nome.equals("")){
			this.nome = "jogador sem nome";
		}else{
			this.nome = nome;
		}

		this.point = 0;
	}


	void setNome(String nome){
		this.nome = nome;
	}

	void Pontuar(){
		this.point += 2;
	}
       
	public String getNome(){
		return this.nome;
	}

	public int getPoint(){
		return this.point;
	}

}


class Peca{
	private char Symbol;
	private int position;


	Peca( char Symbol,int position){
		this.Symbol = Symbol;
		this.position = position;

	}

	public int getPosition(){
		return this.position;
	}

	public char getSymbol(){
		return this.Symbol;
	}

	void setSymbol(char Symbol){
		this.Symbol = Symbol;
	}


        
}


 abstract class jogada{
	private Peca pecaone;
	private Peca pecatwo;
	private boolean pontuar;

	jogada(){
		
	}

	public void comparar(Peca pecaone, Peca pecatwo){
	    if(pecaone == null || pecatwo == null){
		    System.out.print("nao dar de fazer a comparacao");
		    return;
	    }
	    if(pecaone.getSymbol() == pecatwo.getSymbol()){
		    this.pontuar = true;
	    }else{
		this.pontuar = false;
		    System.out.print("nao sao iguais");
	    }
    }

}

interface manipulate{
	Peca choose(int postiion);
	Peca choosetwo(int position);
	String nameplayer();
	int pontuacao();

}  

 class mesa extends jogada implements manipulate{
    private  Map <Integer, Peca> pecas = new TreeMap <>();
    private  Map <Integer, Peca> display = new TreeMap <>();
    private String randomico;
    private  jogador player;

    mesa(String nome){
	    this.player = new jogador(nome);
		ArrayList<Integer> contidos = new ArrayList<>();
		int contar = 1;
	    randomico = "PPJJHHTTUUKK";		    
            Random random = new Random();
            for(int i = 0; i< randomico.length(); i++){
		        int index;
				do{
					index = random.nextInt(randomico.length());
				}while(contidos.contains(index));

				contidos.add(index);

				char caractere = randomico.charAt(index);

				Peca add = new Peca(caractere, contar);
				pecas.put(contar, add);
				contar++;

            } 
	    for(int i=1; i<= randomico.length(); i++){
		    Peca add = new Peca( '*', i);
		    display.put(i, add);
	    }
            	    
    }



   public  Peca choose(int position){

	 if(pecas.containsKey(position)){
	    Peca escolha = pecas.get(position);
	    return escolha;
	 }else{
		System.out.println("peca nao encontrada");
		return null;
	 }
    }

    public Peca choosetwo (int position){
	 if(pecas.containsKey(position)){
	    Peca escolha = pecas.get(position);
	    return escolha;
	 }else{
		System.out.println("peca nao encontrada");
		return null;
	 }
  }
     
    public void comparar(Peca one, Peca two){
	    if(one == null || two == null){
		    System.out.print("nao dar de fazer a comparacao");
		    return;
	    }
	    if(one.getSymbol() == two.getSymbol()){
		    display.get(one.getPosition()).setSymbol(one.getSymbol());
		    display.get(two.getPosition()).setSymbol(two.getSymbol());
		    System.out.print("+2 pontos");
		    player.Pontuar();

	    }else{
		    System.out.print("nao sao iguais");
	    }
    }

	public String nameplayer (){
		return this.player.getNome();
	}

	public void setNamePlayer(String nome){
		this.player.setNome(nome);
	}

   public  int pontuacao(){
	   return player.getPoint();
   }

   public int pecasFaltando(){
	int cont = 0;
	for(Peca alguem : pecas.values()){
		if(alguem.getSymbol()=='*'){
			cont++;
		}
	}
	return cont;
   }

   public String pecasShow(){
	String ss = "";
	int cont = 1;
	for(Peca alguem : pecas.values()){
		ss+= "		";
		ss+= alguem.getSymbol();
		if(cont%4 == 0){
			ss+="\n";
		}
		cont ++;
	}

	return ss;
   }

   public String toString(){
	String ss = "";
	int cont = 1;
	for(Peca alguem : display.values()){
		ss+= "		";
		ss+= alguem.getSymbol();
		if(cont%4 == 0){
			ss+="\n";
		}
		cont ++;
	}

	return ss;
   }



}


public class jogodamemoria{
	public static void main(String[] args){
		String padrao = "([1-9]|1[0-2])";
		Pattern pattern = Pattern.compile(padrao);
		while(true){
			limparTerminal();
			System.out.println("Quer jogar ? (S/N)");
			String answer = input();
			limparTerminal();
			if(answer.equals("s")){
				mesa Mesao = new mesa ("");
				while(true){
				System.out.println("colocar algum nome para o jogador ? (S/N)");
				String decision = input();
				if(decision.equals("s")){
					System.out.print("Nome: ");
					String nome_jogador = input();
					Mesao.setNamePlayer(nome_jogador);
					System.out.println("nome do jogador é " + Mesao.nameplayer());
					atrasarOneSec();
					break;
				}else if(decision.equals("n")){
					System.out.println(Mesao.nameplayer());
					atrasarOneSec();
					break;
				}else {
					System.out.println("Comando Invalido");
					atrasarOneSec();
					limparTerminal();
				}

			}
			    limparTerminal();
				int tempo = 3;
				System.out.println("o jogo começa em " + tempo);
				tempo -=1;
				atrasarOneSec();
				limparTerminal();
				System.out.println("o jogo começa em " + tempo);
				tempo -=1;
				atrasarOneSec();
				limparTerminal();
				System.out.println("o jogo começa em " + tempo);
				tempo -=1;
				atrasarOneSec();
				limparTerminal();

				
				boolean play = true;
				System.out.println("Voce tem 5 segundos pra memorizar essa tabela:");
				System.out.println(Mesao.pecasShow());
				atrasarOneSec();
				atrasarOneSec();
				atrasarOneSec();
				atrasarOneSec();
				atrasarOneSec();
				limparTerminal();
				while(play == true){
					if( Mesao.pontuacao() == 12){
							limparTerminal();
							System.out.println("você ganhou!!! Parabéns");
							System.out.println("Fim de Jogo");
							atrasarOneSec();
							atrasarOneSec();
							break;
						}
					System.out.println("jogador: " + Mesao.nameplayer());
					System.out.println("pontos: " + Mesao.pontuacao());
					System.out.println("---------------------------------");
					System.out.println(Mesao.toString());
					System.out.println("---------------------------------");
					System.out.println("desistir: digite Q");
					System.out.println("escolher peca: digite de 1 ao 12");
					System.out.print("comando: ");
					String entrada = input();

					Matcher matcher = pattern.matcher(entrada);
					if(entrada.equals("q")){
						limparTerminal();
						System.out.println("Fim de Jogo");
						System.out.println(Mesao.pecasShow());
						atrasarOneSec();
						atrasarOneSec();
						break;
					}else if(matcher.matches()){
						int escolha = Integer.valueOf(entrada);
						Peca escolhaone = Mesao.choose(escolha);
						while(true){
							System.out.print("escolher outra peca: ");
							String inputtwo = input();
							Matcher verify = pattern.matcher(inputtwo);
							if(verify.matches()){
								int choosenew = Integer.valueOf(inputtwo);
								Peca escolhatwo = Mesao.choosetwo(choosenew);
								Mesao.comparar(escolhaone, escolhatwo);
								atrasarOneSec();
								break;
							}else{
								System.out.println("Comando não encontrado");
								atrasarOneSec();
								limparTerminal();
							}
						}
					}else{
						System.out.println("Comando não encontrado");
					}

					limparTerminal();

				}

				
				

			}else if(answer.equals("n")){
				limparTerminal();
				System.out.println("ok, mesmo assim obrigado por esta aqui");
				atrasarOneSec();
				atrasarOneSec();
				atrasarOneSec();
				break;
			}else{
				System.out.println("Comando não encontrado");
				atrasarOneSec();
				limparTerminal();
			}

		}
	}

	static Scanner scanner = new Scanner(System.in);

    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
	public static void atrasarOneSec(){
		try {
            // Coloca a thread atual para dormir por 1 segundo (1000 milissegundos)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public static void limparTerminal() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Se for Windows, use o comando "cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Se for Unix/Linux, use o comando "clear"
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





