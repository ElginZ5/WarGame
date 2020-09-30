import java.util.AbstractList;
import java.util.Scanner;

public class Card<T> extends AbstractList<T>{
	
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
	
	public boolean add(T info) {
		
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
	
	public boolean addNode(int i, T info) {
		
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
	public T get(int index) {
		// TODO Auto-generated method stub
		Node curr = head;
		
		for (int i = 0; i < index; i++) {
			
			curr = curr.next;
			
		}
		
		return curr.info;
		
	}
	
	// index must be within our list
	public T remove (int index) {
		
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
	public int size() {

		int size = 0;
		
		Node curr = head;
		
		while(curr.next != null) {
			
			curr = curr.next;
			size++;
			
		}
		
		return size;
	}
	
	public Card<Integer> shuffle (Card<Integer> initialDeck) {
		
		for (int i = 1; i <= 52; i++) {
			
			int random1 = (int)(Math.random()*52);
			int random2 = (int)(Math.random()*52);
			
			int temp = initialDeck.get(random1);
			int temp1 = initialDeck.get(random2);
			int temp3 = 0;
			
			temp3 = temp;
			temp = temp1;
			temp1 = temp3;
			
			initialDeck.remove(random1);
			initialDeck.addNode(random1, temp);
			
			initialDeck.remove(random2);
			initialDeck.addNode(random2, temp1);
			
		}
		
		
		return initialDeck;
		
	}
	
	public void playGame (Card<Integer> cardDeck) {
		
		Card<Integer> player1 = new Card<Integer>();
		Card<Integer> player2 = new Card<Integer>();
		
		boolean winGame = false;
		
		boolean start = true;
		
		
			for (int i = 0; i < 26; i++) {
			
				player1.add(cardDeck.get(i));
			
			}
		
			for (int i = 26; i < 52; i++) {
			
				player2.add(cardDeck.get(i));
			
			}
			
		
		
		//System.out.println(player1);
		//System.out.println(player2);
		
		Scanner in = new Scanner(System.in);
		
		while (!winGame) {
				
			System.out.println("Player 1 has "+(player1.size()+1)+" cards, and Player 2 has "+(player2.size()+1)+" cards.");
			System.out.println("Press enter to flip cards");
			String enter = in.nextLine();
			
			if (enter.equals("")) {
					
				System.out.println("Player 1 played a "+player1.get(0));
				System.out.println("Player 2 played a "+player2.get(0));
					
				if (player1.get(0) > player2.get(0)) {
						
					player1.add(player1.get(0));
					player1.add(player2.get(0));
					player1.remove(0);
					player2.remove(0);
					
					//System.out.println(player1.get(0));
					System.out.println("Player 1 Wins the Round!");
						
				} else if (player2.get(0) > player1.get(0)) {
						
					player2.add(player2.get(0));
					player2.add(player1.get(0));
					player2.remove(0);
					player1.remove(0);
					
					//System.out.println(player2.get(0));
					System.out.println("Player 2 Wins the Round!");
						
				} else if (player1.get(0) == player2.get(0)) {
					
					System.out.println("It is War!");
					System.out.println("The next two cards in the deck for player 1 are an "+player1.get(1)
					+" and an "+player1.get(2));
					System.out.println("The next two cards in the deck for player 2 are an "+player2.get(1)
					+" and an "+player2.get(2));
					
					if ((player1.get(1)+player1.get(2)) > (player2.get(1)+player2.get(2))) {
						
						System.out.println("Player 1 wins the war!");
						
						player1.add(player1.get(0));
						player1.add(player1.get(1));
						player1.add(player1.get(2));
						player1.add(player2.get(0));
						player1.add(player2.get(1));
						player1.add(player2.get(2));
						
						player1.remove(0);
						player1.remove(1);
						player1.remove(2);
						
						player2.remove(0);
						player2.remove(1);
						player2.remove(2);
						
					} else if ((player1.get(1)+player1.get(2)) < (player2.get(1)+player2.get(2))) {
						
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
		
		Card<Integer> initialDeck = new Card<Integer>();
		Card<Integer> testDeck = new Card<Integer>();
		
		for (int i = 1; i <= 13; i++) {
			
			for (int j = 1; j <= 4; j++) {
				
				initialDeck.add(i);
				
			}
			
		}
		
		initialDeck.shuffle(initialDeck);
		initialDeck.shuffle(initialDeck);
		initialDeck.shuffle(initialDeck);
		
		//System.out.println(initialDeck);
		initialDeck.playGame(initialDeck);
		
	}
	
}
