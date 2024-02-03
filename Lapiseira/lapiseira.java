import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

class Lead {
    private float thickness;
    private String hardness;
    private int size;

    public Lead(float thickness, String hardness, int size) {
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public String toString() {
        DecimalFormat form = new DecimalFormat("0.0");
        return form.format(thickness) + ":" + hardness + ":" + size;
    }

    public int usagePerSheet() {
        if(hardness.equals("HB"))
            return 1;
        else if(hardness.equals("2B"))
            return 2;
        else if(hardness.equals("4B"))
            return 4;
        else
            return 6;
    }
    public float getThickness() {
        return thickness;
    }
    public String getHardness() {
        return hardness;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}


class Pencil {
    private float thickness;
    private Lead tip; //lead da ponta
    private ArrayList<Lead> barrel = new ArrayList<> (); //grafites no cano

    public Pencil(float thickness) {
	    this.tip = null;
	    this.thickness = thickness;
    }

    //insere um lead no barril
    public boolean insert(Lead lead) {
	    if(lead.getThickness() == this.thickness){
			   this.barrel.add(lead);
			   return true;
	    }else{
		    System.out.println("fail: calibre incompatível");
		    return false;
	    }
    }

    //remove e retorna o lead da ponta
    public Lead remove() {
	    if(this.tip == null){
		    return null;
	    }else {
		    Lead ant = this.tip;
		    this.tip = null;
		    return ant;
	    }
    }

    // se a ponta estiver vazia, puxa o próximo lead do barril
    public boolean pull() {
	    if(this.tip == null){
		    if(!barrel.isEmpty()){
			    this.tip =this.barrel.get(0);
                            this.barrel.remove(0);
                            return true;
		    }else {
			    return false;
		    }
	    }else {
	        System.out.println("fail: ja existe grafite no bico");
		    return false;
	    }
    }

    //se tiver grafite suficiente no bico, gaste e retorne true
    //lembre que os últimos 10mm não podem ser utilizados
    public void writePage() {
	    if(this.tip == null){
		    System.out.println("fail: nao existe grafite no bico");
		    return;
	    }else{
		    if(this.tip.getSize() == 10){
			    System.out.println("fail: tamanho insuficiente");
			    return;
		    }
		    int sobra = this.tip.getSize()-this.tip.usagePerSheet();
		    if(sobra < 10){
			    this.tip.setSize(10);
			    System.out.println("fail: folha incompleta");
		    }else{
			    this.tip.setSize(sobra);
		    }
	    }

    }
    public String toString() {
        String saida = "calibre: " + thickness + ", bico: ";
        if (this.tip != null) {
            saida += "[" + this.tip + "]";
        } else {
            saida += "[]";
        }
        saida += ", tambor: {";
        for (Lead g : barrel) {
            saida += "[" + g + "]";
        }
        return saida + "}";
    }
}
class solver{
    public static void main(String[] _args) {
        
        Pencil lapiseira = new Pencil(0.5f);

        while(true) {
            var line = input();
            var args = line.split(" ");
            write("$" + line);

            if(args[0].equals("end")) {
                break;
            } else if(args[0].equals("init")) { //thickness
                lapiseira = new Pencil(numberf(args[1]));
            } else if(args[0].equals("insert")) {//thickness hardness size
                lapiseira.insert(new Lead(numberf(args[1]), args[2],  number(args[3])));
            } else if(args[0].equals("remove")) {
                lapiseira.remove();
            } else if(args[0].equals("show")) {
                write("" + lapiseira);
            } else if (args[0].equals("write")) {
                lapiseira.writePage();
            } else if (args[0].equals("pull")) {
                lapiseira.pull();
            }  else {
                write("fail: comando invalido");
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);
    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static int number(String str)   { return Integer.parseInt(str); }
    public static Float numberf(String str)   { return Float.parseFloat(str); }
}
