import java.util.*;

class Pet{
    private int energyMax, hungryMax, cleanMax;
    private int energy, hungry, clean;
    private int diamonds;
    private int age;
    private boolean alive;

    public Pet(int energy, int hungry, int clean){
            this.energyMax = energy;
            this.hungryMax = hungry;
            this.cleanMax = clean;
            this.energy = energy;
            this.clean = clean;
            this.hungry = hungry;
            this.diamonds = 0;
            this.age = 0;
            this.alive=true;
    }

    void setEnergy(int value){
        if(value <= 0){
            this.energy = 0;
            System.out.println("fail: pet morreu de fraqueza");
            this.alive = false;
            return;
        } 
        if(value > this.energyMax) {
            this.energy = this.energyMax;
            return;
        }
        this.energy = value;
    }


    public void setHungry(int value){
        if(value <= 0){
            this.hungry = 0;
            System.out.println("fail: pet morreu de fome");
            this.alive = false;
            return;
        } 
        if(value > this.hungryMax) {
            this.hungry = this.hungryMax;
            return;
        }
        this.hungry = value;
    }
    
    void setClean(int value){
        if(value <= 0){
            this.clean = 0;
            System.out.println("fail: pet morreu de sujeira");
            this.alive = false;
            return;
        } 
        if(value > this.cleanMax) {
            this.clean = this.cleanMax;
            return;
        }
        this.clean = value;
    }

    private boolean testAlive(){
        if(!this.alive){
            System.out.println("fail: pet esta morto");
        }
        
        return this.alive;
        
    }


    public String toString(){
        String ss = "";
        ss +=  "E:" + energy + "/" + energyMax + ", "
            +  "S:" + hungry + "/" + hungryMax + ", "
            +  "L:" + clean + "/" + cleanMax + ", "
            +  "D:" + diamonds + ", " + "I:"  + age;
        return ss;
    }

    public void play(){
        if(!testAlive())
            return;
        setEnergy(getEnergy() - 2);
        setHungry(getHungry() - 1);
        setClean(getClean() - 3);
        diamonds += 1;
        age += 1;
    }
    public void shower(){
        if(!testAlive())
            return;
        setEnergy(getEnergy() - 3);
        setHungry(getHungry() - 1);
        setClean(getCleanMax());
        age += 2;
        
    }

    public void eat(){
        if(testAlive()!=true)
            return;
        setEnergy(getEnergy() - 1);
        setHungry(getHungry() + 4);
        setClean(getClean() - 2);
        age += 1;
    }

    public void sleep(){
        if(!testAlive())
            return;
            
        if((this.energyMax-this.energy) < 5){
            System.out.println("fail: nao esta com sono");
            return;
}
        
        age += (this.energyMax-this.energy);
        setEnergy(getEnergyMax());
        setHungry(getHungry() - 1);
    }


    int getClean(){
        return clean;
    }
    int getHungry(){
        return hungry;
    }
    int getEnergy(){
        return energy;
    }
    int getEnergyMax(){
        return energyMax;
    }
    int getCleanMax(){
        return cleanMax;
    }
    int getHungryMax(){
        return hungryMax;
    }
}


public class tamagochi {
    public static void main(String[] a) {
        Pet pet = new Pet(0, 0, 0);
        
        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if      (args[0].equals("end"))   { break;                                                                           }
            else if (args[0].equals("show"))  { write(pet.toString());                                                           }
            else if (args[0].equals("init"))  { pet = new Pet((int)number(args[1]), (int)number(args[2]), (int)number(args[3])); }
            else if (args[0].equals("play"))  { pet.play();                                                                      }
            else if (args[0].equals("eat"))   { pet.eat();                                                                       }
            else if (args[0].equals("sleep")) { pet.sleep();                                                                     }
            else if (args[0].equals("shower")){ pet.shower();                                                                    }
            else                              { write("fail: comando invalido");                                                 }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}
