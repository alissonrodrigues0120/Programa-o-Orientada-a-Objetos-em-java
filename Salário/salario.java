import java.util.*;

class MsgException extends RuntimeException {
    public MsgException(String message){
        
    }
}

abstract class Funcionario{
    protected String nome;
    protected int bonus=0;
    protected int diarias = 0;
    protected int maxDiarias;
    public Funcionario (String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setBonus(int bonus){
        this.bonus = bonus;
    }
    
    public void addDiaria(){
        if(diarias < maxDiarias){
            diarias += 1;
        }else{
            System.out.println("fail: limite de diarias atingido");
        }
    }
    
    public int getSalario(){
        return bonus + diarias * 100;
    }
}

class STA extends Funcionario {
    protected int nivel;
    public STA(String nome, int nivel){
        super(nome);
        this.nivel = nivel;
        super.maxDiarias = 1;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    
    public int getSalario(){
        return 3000 + 300*this.nivel + super.getSalario();
    }
    
    public String toString(){
        return "sta:" + super.getNome() + ":" + this.nivel + ":" + this.getSalario();
    }
}

class Professor  extends Funcionario{
    protected String classe;
    public Professor (String nome, String classe){
        super(nome);
        this.classe = classe;
        super.maxDiarias = 2;
    }
    
    public String getClasse(){
        return classe;
    }
    
    public int getSalario(){
        if(classe.equals("A")){
            return 3000 + super.getSalario();
        }else if(classe.equals("B")){
            return 5000 + super.getSalario();
        }else if(classe.equals("C")){
            return 7000 + super.getSalario();
        }else if(classe.equals("D")){
            return 9000 + super.getSalario();
        }else {
            return 11000 + super.getSalario();
        }
    }
    
    public String toString(){
        return "prof:" + super.getNome() + ":" + this.classe + ":" + this.getSalario();
    }
    
}

class Terceirizado extends Funcionario{
    protected int horas;
    protected boolean isSalubre = false;
    public Terceirizado (String nome, int horas, String isSalubre){
     super(nome);
     this.horas = horas;
     if(isSalubre.equals("sim")){
         this.isSalubre = true;
     }else{
         this.isSalubre= false;
     }
    }
    
    public int getHoras(){
        return this.horas;
    }
    
    public String getIsSalubre(){
        if(this.isSalubre){
            return "sim";
        }else{
            return "nao";
        }
    }
    public void addDiaria(){
        System.out.println("fail: terc nao pode receber diaria");
    }
    public int getSalario(){
        int insalubre;
        if(this.isSalubre){
            insalubre = 500;
        }else {
            insalubre = 0;
        }
        
        return 4* this.horas + insalubre;
    }
    
    public String toString(){
        return "ter:" + super.getNome() + ":" + this.horas + ":" + this.getIsSalubre() + ":" + this.getSalario();
    }
}

class UFC {
    private Map<String, Funcionario> funcionarios = new TreeMap<>();
    public String toString(){
        String ss = "";
        for(Funcionario arrumar : funcionarios.values()){
            ss += arrumar + "\n";
        }
        
        ss = ss.substring(0, ss.length()-1);
        
        return ss;
    }
    
    public Funcionario getFuncionario(String nome){
        return funcionarios.get(nome);
    }
    
    public void addFuncionario(Funcionario funcionario){
        funcionarios.put(funcionario.getNome(), funcionario);
    }
    
    public void rmFuncionario(String nome){
        funcionarios.remove(nome);
    }
    
    public void setBonus (int bonus){
        int repartir = bonus/funcionarios.size();
        for(Funcionario arrumar : funcionarios.values()){
            arrumar.setBonus(repartir);
        }
    }
}
class salario {
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);
        UFC ufc = new UFC();
        while(true){
            try{
                String line = scanner.nextLine();
                System.out.println("$" + line);
                String ui[] = line.split(" ");
                
                if(ui[0].equals("end")){
                    break;
                }else if(ui[0].equals("addProf")){
                    ufc.addFuncionario(new Professor (ui[1], ui[2]));
                }else if(ui[0].equals("addSta")){
                    ufc.addFuncionario(new STA (ui[1], Integer.parseInt(ui[2])));
                }else if(ui[0].equals("addTer")){
                    ufc.addFuncionario(new Terceirizado (ui[1], Integer.parseInt(ui[2]), ui[3]));
                }else if(ui[0].equals("rm")){
                    ufc.rmFuncionario(ui[1]);
                }else if(ui[0].equals("showAll")){
                    System.out.println(ufc);
                }else if(ui[0].equals("show")){
                    System.out.println(ufc.getFuncionario(ui[1]));
                }else if(ui[0].equals("addDiaria")){
                    ufc.getFuncionario(ui[1]).addDiaria();
                }else if(ui[0].equals("setBonus")){
                    ufc.setBonus(Integer.parseInt(ui[1]));
                }else{
                    System.out.print("fail: comando invalido");
                }
            } catch (MsgException me){
                System.out.println(me.getMessage());
            }
        }
    }
}
