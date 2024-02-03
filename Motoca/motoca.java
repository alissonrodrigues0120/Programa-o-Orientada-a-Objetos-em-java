import java.util.*;

class Person {
    private String name;
    private int age;
    public Person(String name, int age){
	    this.name = name;
	    this.age = age;
    }
    public String getName() {
	    return this.name;
    }
    public int getAge() {
	    return this.age;
    }
    public String toString(){
	    return "person:(" + name + ':' + age + ')';
    }
}

class Motorcycle {
    private Person person; //agregacao
    private int power;
    private int time;
    
    //Inicia o atributo power, time com zero e person com null
    public Motorcycle(int power){
	    this.power = power;
	    this.time = 0;
	    this.person = null;
    }
    public int getPower() {
	    return this.power;
    }
    public int getTime() {
	    return this.time;
    }
    public Person getPerson() {
	    return this.person;
    }

    
    //Se estiver vazio, coloca a pessoa na moto e retorna true
    public boolean enter(Person person) {
	    if(this.person == null){
		    this.person = person;
		    return true;
	    }else{
		    System.out.println("fail: busy motorcycle");
		    return false;


    }
    
    }
    public Person leave() {
	    if(this.person != null) {
                    System.out.println(this.person.getName() + ':' + this.person.getAge());
		            this.person = null;
                    return null;
            }
            else {
                System.out.println("fail: empty motorcycle");
                return this.person;
            }

    }

    public void drive(int value){
	 
	    
	     if(this.getTime()==0){
		    System.out.println("fail: buy time first");
	    }
	    
	    else if(this.person == null){
		    System.out.println("fail: empty motorcycle");
	    }
	    else if(this.person.getAge() > 10){
		    System.out.println("fail: too old to drive");
	   }

	    else if(value < this.time){
		    this.time -= value;
	    }else{
		    int sobra;
		    sobra = value - this.time;
		    this.time = 0;
		    System.out.println("fail: time finished after " + sobra + " minutes");
	    }




    }

    public void buy (int tempo){
	    if(this.time == 0){
		    this.time = tempo;
	    }else{
		    this.time += tempo;
	    }
    }

    public void honk (){
	    String buzinar = new String();
	    buzinar += "P";

	    for(int i = 0; i < this.power; i++ ){
		    buzinar += "e";
	    }
	    buzinar += 'm';

	    System.out.println(buzinar);
    }


    public String toString(){
	    if(this.person == null){
             return "power:" + this.power + ", time:" + this.time + ", " + "person:(empty)";
         }else {
               return "power:" + this.power + ", time:" + this.time + ", " + this.person.toString();   
            }
    }
}
class motoca{
    
    static Motorcycle moto = new Motorcycle(1);

    public static void main(String[] args) {    
        while(true) {
            String line = input();
            args = line.split(" ");
            write('$' + line);

            if      (args[0].equals("show"))     { System.out.println(moto);                         }
            else if (args[0].equals("init"))     { moto = new Motorcycle(number(args[1]));           }  
            else if (args[0].equals("enter"))    { moto.enter(new Person(args[1], number(args[2]))); }
            else if (args[0].equals("end"))      { break;                                              }
            else if (args[0].equals("leave"))    { moto.leave();
	    
        } 
    else if(args[0].equals("drive")){moto.drive(number(args[1])); }
	else if(args[0].equals("buy")){moto.buy(number(args[1])); }
	else if(args[0].equals("honk")){moto.honk();
	   }
    }
}
    static Scanner scanner = new Scanner(System.in);
    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static int number(String str)   { return Integer.parseInt(str); }
    
}
