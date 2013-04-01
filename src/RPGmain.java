import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;




class Player{
	String name;
	int inventory[];
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
	
	String nameGenerator() {
		String first[] = {"Death", "Evil", "Wispy", "Suspicious", "Crazy", "Ancient", "Crafty", "Devious", "Spooky", "Senile"};
		String second[] = {" Goblin", " Cobal", " Ogre", " Orc", " Shade", " Baby Dragon", " Drake", " Air", " Water", " Earth", " Fire"};
		String third[] = {" Slave", " Soldier", " Alchemist", " Miner", " Mage", " Necromancer", "", " Elemental"};
		
		int one = (int) (Math.random() * first.length);
		int two = (int) (Math.random() * second.length);
		int three = (int) (Math.random() * third.length);
		
		String randname = (first[one] + second[two] + third[three]);
		return randname;
		
	}
	
	RandomMonster() {
		name = nameGenerator();
		health = (int) ((Math.random() * 120 ) + 3);
		attack = (int) ((Math.random() * 40) + 1);
		
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
	void Battle (RandomMonster Monster1, Player player) throws IOException {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String readline;
		boolean end = false;
		while(end == false) {
			System.out.println("player health: " + player.health + "       " +  Monster1.name + " Health: " + Monster1.health);
			attack(player.attack(), player.health, Monster1.attack(), Monster1.health);
			Monster1.health = getMhp();
			player.health = getPhp();
			if (Monster1.health <= 0) {
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
		player.name = "Jacob";
		player.attack = 4;
		player.health = 100;
		
		RandomMonster Monster1 = new RandomMonster();
		System.out.println(Monster1.name);
		System.out.println("Start Screen");
		System.out.println("");
		System.out.println("1. Start temp battle test");
		System.out.println("2. Exit");
		readline = in.readLine();
		
		if (readline.equals("1")) {
			Gamemethods.Battle(Monster1, player);
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
