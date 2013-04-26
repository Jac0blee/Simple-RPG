import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;


class Player{
	String name;
	int[] inventory = new int[10];
	private int health;
	private int healthmax;
	private int attack;
	private int money;
	//int dexterity;
	//int wisdom;
	private int hit;
	
	//setters
	
	void setHealth(int hp) {
		health = hp;
	}
	void setHealthMax(int hm) {
		healthmax = hm;
	}
	void setAttack(int att) {
		attack = att;
	}
	void setMoney(int mn) {
		money = mn;
	}
	void setHit(int ht) {
		hit = ht;
	}
	
	//getters
	
	int getHealth() {
		return health;
	}
	int getHealthMax() {
		return healthmax;
	}
	int getAttack() {
		return attack;
	}
	int getMoney() {
		return money;
	}
	int getHit() {
		return hit;
	}
	
	int attack(){
		hit = (int) ((Math.random() * 60) + attack);
		return hit;
	}
	void heal() {
		if (health == healthmax) {
			System.out.println("You are already healed.");
		}
		else {
			int cost = (healthmax - health) / 5;
			
			if (money >= cost) {
				health = healthmax;
				money = money - cost;
			}
			else {
				System.out.println("You dont have enough money to heal to your max health.");
			}
		}
	}
	
}



class RandomMonster {
	String name;
	private int health;
	private int attack;
	private int hit;
	
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
		
		switch (two) { 
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
		
		/*switch (three) {
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
			default:
				System.out.println("ERM... somethings wrong here");
				break;
		}*/
		
		return randname;
		
	}
	
	//Setters:
	public void setHealth(int hp) {
		health = hp;
	}
	public void setAttack(int att) {
		attack = att;
	}
	public void setHit(int ht) {
		hit = ht;
	}
	public void setName(String nm) {
		name = nm;
	}
	
	//Getters:
	public int getHealth() {
		return health;
	}
	public int getAttack() {
		return attack;
	}
	public int getHit() {
		return hit;
	}
	public String getName() {
		return name;
	}
	
	//Normal methods:
	
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
	RandomMonster LMB;
	
	public static void wait (int n) {
	    long t0,t1;
	    t0=System.currentTimeMillis();
	    do{
	        t1=System.currentTimeMillis();
	    }
	    while (t1-t0<1000);
	}
	void Battle (Player player) throws IOException {
		RandomMonster LMB = new RandomMonster();
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String readline;
		boolean end = false;
		while(end == false) {
			System.out.println("player health: " + player.getHealth() + "/" + player.getHealthMax() + "       " +  LMB.name + " Health: " + LMB.getHealth());
			attack(player, LMB);
			//LMB.setHealth(getMhp());
			//player.setHealth(getPhp());
			if (LMB.getHealth() <= 0 || player.getHealth() <= 0) {
				end = true;
			}
			else{
				System.out.println("player health: " + player.getHealth() + "/" + player.getHealthMax() + "       " +  LMB.name + " Health: " + LMB.getHealth());
				System.out.println("Do you want to continue attacking?");
				readline = bufferRead.readLine();
				if (readline.equals("no") || readline.equals("N") || readline.equals("No")) {
					end = true;
				}
			}
		}
	}
	void attack(Player player, RandomMonster monster ) {
		System.out.println("Attacking!");
		int mhpb = monster.getHealth();
		monster.setHealth(monster.getHealth() - player.attack());
		wait(6);
		System.out.println("You hit him for " + (mhpb - monster.getHealth()) + "HP");
		if (monster.getHealth() <= 0) {
			System.out.println("The monster is dead!");
		}
		else {
			System.out.println("The monster attacks back!");
			wait(6);
			int phpb = player.getHealth();
			player.setHealth(player.getHealth() - monster.attack());
			System.out.println("He hits you for " + (phpb - player.getHealth()) + "HP");
			wait(6);
			if (player.getHealth() <= 0) {
				System.out.println("you are dead!");
			}
		}
	}
}



public class RPGmain {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String readline;
		boolean end = false;
		
		GameMethods Gamemethods = new GameMethods();
		Player player = new Player();
		player.setHealth(100);
		player.setHealthMax(player.getHealth());
		player.setAttack(70);
		player.inventory[0] = 1;
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
				while (end == false) {
					Gamemethods.Battle(player);
					System.out.println("That was a crazy Battle you had out there");
					System.out.println("Would you like to heal yourself?");
					readline = in.readLine();
					if (readline.equals("yes") || readline.equals("y") || readline.equals("Yes")) {
						player.heal();					
					}
					else{
						if (player.getHealth() <= player.getHealth() / 2) {
							System.out.println("Are you sure? You look pretty beat up...");
						}
						else {
							System.out.println("Okay bye!");
						}
					}
					System.out.println("Would you like to battle again");
					readline = in.readLine();
					if(readline.equals("no") || readline.equals("n") || readline.equals("No")) {
						end = true;
					}
				}
			}
			else if(readline.equals("no") || readline.equals("n") || readline.equals("No")) {
				System.out.println("Okay bye!");
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

