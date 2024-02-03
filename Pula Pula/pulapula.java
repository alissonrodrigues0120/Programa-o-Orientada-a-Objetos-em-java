import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;



class Kid {
    private int age;
    private String name;

    public Kid(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return name + ":" + age;
    }
}

class Trampoline{
    private LinkedList<Kid> waiting = new LinkedList<>();
    private LinkedList<Kid> playing = new LinkedList<>();
    
    public Trampoline() {

    }

    private static Kid removeFromList(String name, LinkedList<Kid> list) {
        Kid temp = null;
        for(int i=0;i < (int) list.size(); i++){
            temp = list.get(i);
            if(name.equals(temp.getName())){
                list.remove(temp);
                return temp;
            }
        }
        
        return null;
    }

    public void arrive(Kid kid) {
	    this.waiting.addFirst(kid);
    }

    public void enter() {
	    if(this.waiting.isEmpty()){
		    return;
	    }

	    if(this.playing.isEmpty()){
		    this.playing.addFirst(this.waiting.get(this.waiting.size()-1));
		    this.waiting.remove(this.waiting.size()- 1);
	    }else {
		    this.playing.addFirst(this.waiting.get(this.waiting.size()-1));
		    this.waiting.remove(this.waiting.size()- 1);


	    }

    }

    public void leave() {
	    if(this.playing.isEmpty()){
		    return;
	    }else {
		    this.waiting.addFirst(this.playing.get(this.playing.size()- 1));
		    this.playing.remove(this.playing.size()- 1);

	    }
    }

    public Kid remoteKid(String name) {
	    Kid crianca = null;
	    crianca = removeFromList(name, this.waiting);
	    if(crianca == null){
	        crianca = removeFromList(name, this.playing);
	        return crianca;
	    }
	    
	    return crianca;

    }
    public String toString() {
        return   "[" + waiting.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]" + " => "
               + "[" + playing.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]";
    }
}


class pulapula {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trampoline tramp = new Trampoline();
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$"+ line);
            String[] ui = line.split(" ");
            if(ui[0].equals("end")) {
                break;
            } else if(ui[0].equals("arrive")) { // name age
                tramp.arrive(new Kid(ui[1], Integer.parseInt(ui[2]))) ;
            } else if(ui[0].equals("enter")) {
                tramp.enter();
            } else if(ui[0].equals("leave")) {
                tramp.leave();
            } else if(ui[0].equals("remove")) {//name
                tramp.remoteKid(ui[1]);
            } else if(ui[0].equals("show")) {
                System.out.println(tramp);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
