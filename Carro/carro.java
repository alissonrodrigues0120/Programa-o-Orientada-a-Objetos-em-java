import java.util.*;




class Car{
    public int pass = 0; // Passageiros
    public int passMax = 2; // limite de Passageiros
    public int gas = 0; // tanque
    public int gasMax = 100; // limite do tanque
    public int km = 0; // quantidade de quilometragem

    public Car() {

    }

    public void enter() {
	    if(pass < passMax){
		    pass++;

	    }else{
		    System.out.println("fail: limite de pessoas atingido");

	    }

    }

    public void leave() {
	    if(pass > 0){
		    pass--;
	    } else {
		    System.out.println("fail: nao ha ninguem no carro");

	    }
    }

    public void fuel(int valor) {
	    gas += valor;
	    if(gas > gasMax){
		    gas = gasMax;
	    }
    }
    
    public String toString() {
	    return "pass: " + pass + ", gas: " + gas + ", km: " + km;
    }

    public void drive(int valor){
	    if (pass == 0){
		    System.out.println("fail: nao ha ninguem no carro");
		    return;
	    }

	    if (gas == 0){
		    System.out.println("fail: tanque vazio");
		    return;
	    }

	    if(gas < valor){
		    System.out.println("fail: tanque vazio apos andar " + gas + " km");
		    km += gas;
		    gas = 0;
		    return;
	    }

	    gas -= valor;
	    km += valor;


           
    }
};


public class carro {
    public static void main(String[] a) {
        Car car = new Car();
        
        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if      (args[0].equals("show"))  { System.out.println(car);              }
            else if (args[0].equals("enter")) { car.enter();                          }
            else if (args[0].equals("leave")) { car.leave();                          }
            else if (args[0].equals("drive")) { car.drive((int) number(args[1]));     }
            //--
            else if (args[0].equals("fuel"))  { car.fuel((int) number(args[1]));      }
            else if (args[0].equals("end"))   { break;                                }
            else                              { write("fail: comando invalido");}
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}




