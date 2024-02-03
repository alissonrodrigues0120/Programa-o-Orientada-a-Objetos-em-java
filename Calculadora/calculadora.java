import java.util.*;



class Calculator {
    public int batteryMax;
    public int battery;
    public float display;

    public Calculator(int batteryMax) {
	   this.batteryMax = batteryMax;
    }

    public void chargeBattery(int value) {
	    this.battery += value;
	    if(this.battery > this.batteryMax){
		    this.battery = this.batteryMax;
	    }
    }
   

    public boolean useBattery() {
	    if(this.battery > 0){
		    return true;
	    } else{
		    return false;
	    }
    }

    public void sum(int a, int b) {
	    if(useBattery() == false){
		    System.out.println("fail: bateria insuficiente");
		    return;
	    }
	    this.display = a + b;
	    this.battery -= 1;
    }

    public void division(int a, int b){
	    if(b == 0){
		    System.out.println("fail: divisao por zero");
		    this.battery -= 1;
		    return;
	    }

	    if(useBattery() == false){
                    System.out.println("fail: bateria insuficiente");
                    return;
            }

	    float number1 = a;
	    float number2 = b;

	    this.display = number1 / number2;
	    this.battery -= 1;
    }

    public String toString() {
        return String.format("display = %.2f, battery = %d", this.display, this.battery);

        // se seu java estiver utilizando `,` como separador decimal, use:
        // DecimalFormat df = new DecimalFormat("0.00");
        // return String.format("display = %s, battery = %d", df.format(this.display), this.battery);
    }
}

public class calculadora {
    static Calculator calc = new Calculator(0);

    public static void main(String[] _args) {
        while (true) {
            String line = input();
            String[] args = line.split(" ");
            write('$' + line);

            if ("show".equals(args[0])) {
                write(calc.toString());
            }
            else if ("init".equals(args[0])) {
                calc = new Calculator((int) number(args[1]));
            }
            else if ("charge".equals(args[0])) {
                calc.chargeBattery((int) number(args[1]));
            }
            else if ("sum".equals(args[0])) {
                calc.sum((int) number(args[1]), (int) number(args[2]));
            }
	    else if ("div".equals(args[0])) {
                calc.division((int) number(args[1]), (int) number(args[2]));
            }

            else if ("end".equals(args[0])) {
                break;
            }
            else {
                write("fail: comando invalido");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String input() { return scanner.nextLine(); }
    private static double number(String value) { return Double.parseDouble(value); }
    private static void write(String value) { System.out.println(value); }
}
