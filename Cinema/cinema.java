import java.util.*;

class Client {
    private String id;
    private String fone;
    public Client(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

    
    public String toString() {
        return id + ":" + fone;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}


class Sala{
    private ArrayList<Client> cadeiras = new ArrayList<> ();

    private int procurar(String nome){
	    for(int i=0;i < this.cadeiras.size();i++){
		    if(this.cadeiras.get(i)!=null){
			    Client name = this.cadeiras.get(i);
			    if(name.getId().equals(nome)){
				    return i;
			    }
		    }
	    }

	    return -1;
    }


    private boolean verificarIndice(int value){
	    if(value < this.cadeiras.size() && value >=0){
		    return true;
	    }else {
		    return false;
	    }

    }


    public Sala(int capacidade) {
	    for(int i=0;i < capacidade;i++){
		    this.cadeiras.add(null);
	    }
    }

    public ArrayList<Client> getCadeiras() {
	    return this.cadeiras;
    }
    
    public boolean reservar(String id, String fone, int ind) {
	    if(!verificarIndice(ind)){
	        System.out.println("fail: cadeira nao existe");
		    return false;
	    }

	    if(this.cadeiras.get(ind)!=null){
		    System.out.println("fail: cadeira ja esta ocupada");
		    return false;
	    }

	    if(procurar(id)!= -1){
		    System.out.println("fail: cliente ja esta no cinema");
		    return false;
	    }

	    Client novo = new Client (id, fone);
	    this.cadeiras.add(ind, novo);
	    return true;

    }

    public void cancelar(String id) {
	    if(procurar(id)== -1){
		    System.out.println("fail: cliente nao esta no cinema");

	    }else{
		    
		    this.cadeiras.add(procurar(id), null);
		    this.cadeiras.remove(procurar(id));
	    }
    }

    
    public String toString() {
        String saida = "[";
        int cont =0;
        int last = 0;
        for(int i= 0; i< this.cadeiras.size() - 1; i++){
            if(this.cadeiras.get(i) != null){
                cont++;
                last =i;
            }
        }
        
        for(int i=0; i < cadeiras.size(); i++){
            if(cadeiras.get(i)!=null){
                saida += cadeiras.get(i);
            }else {
                saida += "-";
            }
            
            if(cont > 0){
                if(i!=last){
                    saida += " ";
                }else {
                    break;
                }
            }else {
                if(i != cadeiras.size()-1){
                    saida += " ";
                }
            }
        }
        return saida + "]";
    }
}

class cinema {
    static Shell sh = new Shell();
    static Sala sala = new Sala(0);

    public static void main(String args[]) {
        sh.chain.put("init",     () -> { sala = new Sala(getInt(1));});
        sh.chain.put("show",     () -> { System.out.println(sala);});
        sh.chain.put("reservar", () -> { sala.reservar(getStr(1), getStr(2), getInt(3));});
        sh.chain.put("cancelar", () -> { sala.cancelar(getStr(1));});

        sh.execute();
    }

    static int getInt(int pos) {
        return Integer.parseInt(sh.param.get(pos));
    }
    static String getStr(int pos) {
        return sh.param.get(pos);
    }
}

class Shell {    
    public Scanner scanner = new Scanner(System.in);
    public HashMap<String, Runnable> chain = new HashMap<>();
    public ArrayList<String> param = new ArrayList<>();
    public Shell() {
        Locale.setDefault(new Locale("en", "US"));
    }
    public void execute() {
        while(true) {
            param.clear();
            String line = scanner.nextLine();
            Collections.addAll(param, line.split(" "));
            System.out.println("$" + line);
            if(param.get(0).equals("end")) {
                break;
            } else if (chain.containsKey(param.get(0))) {
                chain.get(param.get(0)).run();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
    }
}

