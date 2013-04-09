import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;



class Player{
	String name;
	int[] inventory = new int[10];
	int health;
	int attack;
	//int dexterity;
	//int wisdom;
	int hit;
	
	int attack(){
		hit = (int) ((Math.random() * 60) + attack);
		return hit;
	}
	
}

class RandomMonster {
	String name;
	int health;
	int attack;
	int hit;
	
	String statAndNameGenerator() {
		String first[] = {"Death", "Evil", "Wispy", "Suspicious", "Crazy", "Ancient", "Crafty", "Devious", "Spooky", "Senile"};
		String second[] = {" Goblin", " Cobal", " Ogre", " Orc", " Shade", " Baby Dragon", " Drake", " Skeleton"};
		String third[] = {" Slave", " Soldier", " Alchemist", " Miner", " Mage", " Necromancer", ""};
		
		int one = (int) (Math.random() * first.length);
		int two = (int) (Math.random() * second.length);
		int three = (int) (Math.random() * third.length);
		
		String randname = (first[one] + second[two] + third[three]);
		switch (one) {
			case 0:
				attack = 60;
				break;
			case 1:
				attack = 50;
				break;
			case 2:
				attack = 30;
				break;
			case 3:
				attack = 40;
				break;
			case 4:
				attack = 20;
				break;
			case 5:
				attack = 40;
				break;
			case 6:
				attack = 30;
				break;
			case 7:
				attack = 50;
				break;
			case 8:
				attack = 40;
				break;
			case 9:
				attack = 10;
				break;
			default:
				System.out.println("ERM... somethings wrong here");
				break;
		}
		
		switch (two) { //" Goblin", " Cobal", " Ogre", " Orc", " Shade", " Baby Dragon", " Drake", " Skeleton"
			case 0:
				health = 80;
				break;
			case 1:
				health = 70;
				break;
			case 2:
				health = 150;
				break;
			case 3:
				health = 130;
				break;
			case 4:
				health = 80;
				attack = attack + 20;
				break;
			case 5:
				health = 190;
				attack = attack - 10;
				break;
			case 6:
				health = 160;
				break;
			case 7:
				health = 90;
				attack = attack + 10;
				break;
			default:
				System.out.println("ERM... somethings wrong here");
				break;
				
		}
		
		//switch (three) {
			
		//}
		
		return randname;
		
	}
	
	RandomMonster() {
		name = statAndNameGenerator();		
	}
	
	int attack(){
		hit = (int) ((Math.random() * 60) + attack);
		return hit;
	}
	
}

class GameMethods {
	int rphp;
	int rmhp;
	
	public static void wait (int n) {
	    long t0,t1;
	    t0=System.currentTimeMillis();
	    do{
	        t1=System.currentTimeMillis();
	    }
	    while (t1-t0<1000);
	}
	void Battle (Player player) throws IOException {
		RandomMonster Monster1 = new RandomMonster();
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String readline;
		boolean end = false;
		while(end == false) {
			System.out.println("player health: " + player.health + "       " +  Monster1.name + " Health: " + Monster1.health);
			attack(player.attack(), player.health, Monster1.attack(), Monster1.health);
			Monster1.health = getMhp();
			player.health = getPhp();
			if (Monster1.health <= 0 || player.health <= 0) {
				end = true;
			}
			else{
				System.out.println("player health: " + player.health + "       " +  Monster1.name + " Health: " + Monster1.health);
				System.out.println("Do you want to continue attacking?");
				readline = bufferRead.readLine();
				if (readline.equals("no") || readline.equals("N") || readline.equals("No")) {
					end = true;
				}
			}
		}
	}
	void attack(int patt,int php,int matt,int mhp ) {
		System.out.println("Attacking!");
		int mhpb = mhp;
		mhp = mhp - patt;
		wait(6);
		System.out.println("You hit him for " + (mhpb - mhp) + "HP");
		if (mhp <= 0) {
			System.out.println("The monster is dead!");
		}
		else {
			System.out.println("The monster attacks back!");
			wait(6);
			int phpb = php;
			php = php - matt;
			System.out.println("He hits you for " + (phpb - php) + "HP");
			wait(6);
			if (php <= 0) {
				System.out.println("you are dead!");
			}
		}
		rphp = php;
		rmhp = mhp;
		
	}
	public int getPhp() {
		return rphp;
	}
	public int getMhp() {
		return rmhp;
	}
	


}

public class RPGmain {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String readline;
		
		GameMethods Gamemethods = new GameMethods();
		
		Player player = new Player();
		player.health = 100;
		player.attack = 40;
		player.inventory[0] = 1;
     	player.openInventory();
		System.out.println("Start Screen");
		System.out.println("");
		System.out.println("1. Start temp battle test");
		System.out.println("2. Exit");
		readline = in.readLine();
		
		if (readline.equals("1")) {
			System.out.print("What is your name?: ");
			player.name = in.readLine();
			System.out.println("");
			System.out.println("Hello " + player.name);
			System.out.println("Are you ready to enter the battle?");
			readline = in.readLine();
			if(readline.equals("yes")) {
				Gamemethods.Battle(player);
			}
			
		}
		
		
		
		/*
		 Start Screen
		 
		 1. Start Game
		 2. Options
		 3. Quit Game
		 
		 Game ideas:
		 Start with Randomly generated monsters then a store with a simple attack function and thats it.
		 move into possible selected fights and after a certain amount of fights do a boss battle
		 random generated creatures getting harder as you go
		 
		 
		 */
		

		
	}
}
