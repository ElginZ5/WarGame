import java.util.AbstractList;
import java.util.Scanner;

public class War<T> extends AbstractList<T>{
	
	private Node head;
	
	private class Node {
		
		private Node next;
		private T info;
		
		public Node(T info) {
			
			this.info = info;
			
		}
		
		public Node (T info, Node next) {
			
			this.next = next;
			this.info = info;
			
		}
		
	}
	
	public boolean add(T info) { // adds a node at the end of the linked list
		
		if (head == null) {
			
			head = new Node(info);
			return true;
			
		}
		
		Node curr = head;
		
		while (curr.next != null) {
			
			curr = curr.next;
			
		}
		
		curr.next = new Node(info);
		
		return true;
		
	}
	
	public boolean addNode(int i, T info) { // adds a node at a specific index
		
		if (head == null) {
			
			head = new Node(info);
			return true;
			
		}
		
		Node curr = head;
		Node prev = null;
		
		int index = 0;
		
		while (index < i && curr.next != null) {
			
			prev = curr;
			curr = curr.next;
			
			index++;
			
		}
		
		Node temp = new Node(info);
		
		if (i == 0) {
			
			temp.next = head;
			head = temp;
			
			return true;
			
		}
		
		temp.next = curr;
		prev.next = temp;
		
		return true;
		
	}
	
	@Override
	public T get(int index) { // gets the value of the node at an index in the linked list
		
		Node curr = head;
		
		for (int i = 0; i < index; i++) {
			
			curr = curr.next;
			
		}
		
		return curr.info;
		
	}
	
	// index must be within our list
	public T remove (int index) { // removes the node at the specific index
		
		if (index == 0) {
			
			head = head.next;
			return head.info;
			
		} else {
			
			Node curr = head;
		
			for (int i = 0; i < index-1; i++) {
			
				curr = curr.next;
			
			}
		
			Node temp = curr.next;
			curr.next = curr.next.next;
		
			return temp.info;
			
		}
		
	}

	@Override
	public int size() { // gets the size of the entire linked list

		int size = 0;
		
		Node curr = head;
		
		while(curr.next != null) {
			
			curr = curr.next;
			size++;
			
		}
		
		return size;
	}
	
	public War<Integer> shuffle (War<Integer> initialDeck) { // shuffle method to shuffle the deck
		
		for (int i = 1; i <= 52; i++) { // for loop through the entire deck
			
			int random1 = (int)(Math.random()*52); // two random numbers 1 - 52
			int random2 = (int)(Math.random()*52);
			
			int temp = initialDeck.get(random1); // temp integer that gets the value at initial deck with index random 1
			int temp1 = initialDeck.get(random2); // same thing with index of random 2
			int temp3 = 0;
			
			temp3 = temp; //swaps the values at random 1 and random 2
			temp = temp1;
			temp1 = temp3;
			
			initialDeck.remove(random1); // removes the value at random 1
			initialDeck.addNode(random1, temp); // adds the value at random 1 with the value of temp
			
			initialDeck.remove(random2); // removes the value at random 2
			initialDeck.addNode(random2, temp1); // adds the value at random 2 with the value of temp1
			
		}
		
		
		return initialDeck; // returns the shuffled deck
		
	}
	
	// play game method
	public void playGame (War<Integer> cardDeck) { // cardDeck is the initial deck
		
		War<Integer> player1 = new War<Integer>(); // decks for player 1 and 2
		War<Integer> player2 = new War<Integer>();
		
		boolean winGame = false; // boolean to check if player1/2 won or not
		
		for (int i = 0; i < 26; i++) {
			
			player1.add(cardDeck.get(i)); // splits the first half of the initial deck into the player 1 deck
			
		}
		
		for (int i = 26; i < 52; i++) {
			
			player2.add(cardDeck.get(i)); // splits the second half of the initial deck into the player 2 deck
			
		}
			
		//System.out.println(player1);
		//System.out.println(player2);
		
		Scanner in = new Scanner(System.in); // scanner for user input
		
		while (!winGame) {
				
			System.out.println("Player 1 has "+(player1.size()+1)+" cards, and Player 2 has "+(player2.size()+1)+" cards.");
			System.out.println("Press enter to flip cards");
			String enter = in.nextLine(); // gets the user input
			
			if (enter.equals("")) { // if the user presses the enter key
					
				System.out.println("Player 1 played a "+player1.get(0)); // playing the first card in each deck
				System.out.println("Player 2 played a "+player2.get(0));
					
				if (player1.get(0) > player2.get(0)) { // if the first card in player 1 decks is greater than the one in player 2's deck
						
					player1.add(player1.get(0)); // player 1 will add player 1's first card to the bottom of the deck
					player1.add(player2.get(0)); // player 1 will add player 2's first card to the bottom of the deck
					player1.remove(0); // player 1 removes the 1st card in the deck
					player2.remove(0); // player 2 removes the 1st card in the deck
					
					//System.out.println(player1.get(0));
					System.out.println("Player 1 Wins the Round!"); 
						
				} else if (player2.get(0) > player1.get(0)) { // same thing but with p2
						
					player2.add(player2.get(0));
					player2.add(player1.get(0));
					player2.remove(0);
					player1.remove(0);
					
					//System.out.println(player2.get(0));
					System.out.println("Player 2 Wins the Round!");
						
				} else if (player1.get(0) == player2.get(0)) { // if the card in player 1 and player 2's decks are the same
					
					System.out.println("It is War!");
					System.out.println("The next two cards in the deck for player 1 are an "+player1.get(1)
					+" and an "+player1.get(2)); // gets the next two cards in the deck for player 1
					System.out.println("The next two cards in the deck for player 2 are an "+player2.get(1)
					+" and an "+player2.get(2)); // gets the next two cards in the deck for player 2 
					
					if ((player1.get(1)+player1.get(2)) > (player2.get(1)+player2.get(2))) { // if those two cards in p1 deck are greater than the two cards in p2 deck
						
						System.out.println("Player 1 wins the war!");
						
						player1.add(player1.get(0)); // p1 adds all of the cards played
						player1.add(player1.get(1));
						player1.add(player1.get(2));
						player1.add(player2.get(0));
						player1.add(player2.get(1));
						player1.add(player2.get(2));
						
						player1.remove(0); // p1 removes their first 3 cards
						player1.remove(1);
						player1.remove(2);
						
						player2.remove(0); // p2 removes their first 3 cards
						player2.remove(1);
						player2.remove(2);
						
					} else if ((player1.get(1)+player1.get(2)) < (player2.get(1)+player2.get(2))) { // same thing but with p2
						
						System.out.println("Player 2 wins the war!");
						
						player2.add(player1.get(0));
						player2.add(player1.get(1));
						player2.add(player1.get(2));
						player2.add(player2.get(0));
						player2.add(player2.get(1));
						player2.add(player2.get(2));
						
						player2.remove(0);
						player2.remove(1);
						player2.remove(2);
						
						player1.remove(0);
						player1.remove(1);
						player1.remove(2);
						
					}
					
				} 
					
			} 
		}
			
		if (player1.size() == 0) {
				
			System.out.println("Player 1 Wins!");				
			winGame = true;
				
			
		} else if (player2.size() == 0) {
				
			System.out.println("Player 2 Wins");
			winGame = true;
				
		}
				
	}
		
	
	
	public String toString() {
		
		if (size() == 0) return "[ ]";
		String output = "[";
		Node curr = head;
		while (curr != null) {
			output += curr.info.toString() + ", ";
			curr = curr.next;
		}
		return output.substring(0,output.length()-2) +"]";
		
	}
	
	
	public static void main (String[] args) {
		
		// the main deck/ the initial deck with the 52 cards
		War<Integer> initialDeck = new War<Integer>();
		
		for (int i = 1; i <= 13; i++) {
			
			for (int j = 1; j <= 4; j++) {
				
				// two for loops to add the 52 cards in order into the deck
				initialDeck.add(i);
				
			}
			
		}
		
		// shuffling the deck
		initialDeck.shuffle(initialDeck);
		initialDeck.shuffle(initialDeck);
		initialDeck.shuffle(initialDeck);
		
		//System.out.println(initialDeck);
		
		// play the game
		initialDeck.playGame(initialDeck);
		
	}
	
}
