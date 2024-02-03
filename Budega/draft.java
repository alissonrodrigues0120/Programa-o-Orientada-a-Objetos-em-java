import java.util.*;


class Pessoa {
    private String nome;
    Pessoa(String nome) {
	    this.nome = nome;
    }
    public String getNome() {
	    return this.nome;
    }
}

class Mercantil {
    private ArrayList < Pessoa > caixas = new ArrayList <>(); //caixas do supermercado
    private LinkedList < Pessoa > esperando = new LinkedList <>(); //lista de clientes esperando
    //inicializa esperando como uma lista de tamanho vazio
    //inicializa caixas como um vetor de tamanho qtd_caixas com todas as posições iguais a null
    Mercantil(int qtd_caixas) { //número de caixas no mercado//
		for(int i=0;i<qtd_caixas;i++){
			this.caixas.add(null);
		}
    }
    //verifica se o indice é válido para acessar os
    public boolean validarIndice(int indice) {
	    if(indice < this.caixas.size() && indice >= 0){
		    return true;
	    }else{
		    return false;
	    }
    }
    public void chegar(Pessoa person) {
	    this.esperando.add(person);
    }
    //1. verifica se o indice é válido
    //2. verifica se o caixa chamado está vazio
    //3. verifica se existe alguem na lista de espera
    //4. se tudo estiver ok, o primeiro cliente da lista de espera é movido para este caixa
    public boolean chamarNoCaixa(int indice) {
	    if(validarIndice(indice)){
	        
	        if(this.esperando.size()==0){
	            System.out.println("fail: sem clientes");
	            return false;
	        }
		    int temp = 0;
		    int flag =0;
			    if(this.caixas.get(indice) == null){
				    temp++;
			    }

			    if(temp == 1){
			        flag = 1;
			    }
		    
		    if(flag ==1){
		      this.caixas.remove(indice);
		      this.caixas.add(indice, this.esperando.get(0));
		      this.esperando.remove(0);
		      return true;
		  }else {
		      System.out.println("fail: caixa ocupado");
		      return false;
		  }
		    

	    }else {
		    return false;
	    }
    }
    //1. verifica se o indice é válido
    //2. verifica se este caixa possui um cliente
    //3. Se tudo estiver ok, o cliente é removido do caixa e volta a ser null para indicar que está vazio
    public Pessoa finalizar(int indice) {
	    if(indice < this.caixas.size() && indice >= 0){
		    if(this.caixas.get(indice)== null){
			    System.out.println("fail: caixa vazio");
			    return null;
		    }else {
			    this.caixas.remove(indice);
			    this.caixas.add(indice, null);
			    return this.caixas.get(indice);
		    }
	    }else{
		    System.out.println("fail: caixa inexistente");
		    return null;
	    }
    }
    public String toString() {
        StringBuilder Mercantil = new StringBuilder();
        Mercantil.append("Caixas: [");
        for (int i = 0; i < (int) this.caixas.size(); i++) {
            if (this.caixas.get(i) != null) {
                
                    Mercantil.append(caixas.get(i).getNome());
                
            } else {
                Mercantil.append("-----");
            }
            
            
            if((this.caixas.size() > 1) && (i != this.caixas.size() - 1 )){
                Mercantil.append(", ");
            }
        }
        Mercantil.append("]");
        Mercantil.append("\nEspera: [");
        for (int i = 0; i < this.esperando.size(); i++) {
            Mercantil.append(this.esperando.get(i).getNome());
            if (i < this.esperando.size() - 1) {
                Mercantil.append(", ");
            }
        }
        Mercantil.append("]");
        return Mercantil.toString();
    }
}


class draft {
    static Mercantil mercantil = new Mercantil(0);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$"+ line);
            String[] ui = line.split(" ");
            if(ui[0].equals("end")) {
                break;
            } else if(ui[0].equals("init")) { // name age
                mercantil = new Mercantil ( Integer.parseInt(ui[1]));
            } else if(ui[0].equals("call")) {
                mercantil.chamarNoCaixa(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("finish")) {
                mercantil.finalizar(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("arrive")) {//name
                mercantil.chegar(new Pessoa(ui[1]));
            } else if(ui[0].equals("show")) {
                System.out.println(mercantil);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
