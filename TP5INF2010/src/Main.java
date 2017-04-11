import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Node noeud1 = new Node(1);
		Node noeud2 = new Node(2);
		Node noeud3 = new Node(3);
		Node noeud4 = new Node(4);
		Node noeud5 = new Node(5);
		Node noeud6 = new Node(6);
		Node noeud7 = new Node(7);
		Node noeud8 = new Node(8);
		
		
		
		noeud1.print("  ");
		System.out.println("_________________________________________");
		try {
			noeud1.fusion(noeud2);
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		noeud1.print("  ");
		System.out.println("_________________________________________");
		
		try {
			noeud1.fusion(noeud3);
		} catch (DifferentOrderTrees e) {
			System.out.println("Arbres de differents ordres");
		}
		
		try {
			noeud1.fusion(noeud3.fusion(noeud4));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		noeud1.print("  ");
		System.out.println("_________________________________________");
		
		noeud1.findValue(3).print("  ");
		System.out.println("_________________________________________");
		
		noeud1.findValue(2).print("  ");
		System.out.println("_________________________________________");
		try {
			noeud1.fusion(noeud5.fusion(noeud6).fusion(noeud7.fusion(noeud8)));
		} catch (DifferentOrderTrees e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		noeud1.print("  ");
		System.out.println("_________________________________________");
		
		ArrayList<Node> restants = noeud1.findValue(4).delete();
		for(Node n: restants){
			n.print("  ");
		}
		System.out.println(restants);
		
		noeud1.print("  ");
		
		System.out.println("_________________________________________");
		
		restants = noeud4.delete();
		for(Node n: restants){
			n.print("  ");
		}
		System.out.println(restants);
		
		noeud1.print("  ");
		System.out.println("_________________________________________");
		
		restants = noeud5.delete();
		for(Node n: restants){
			n.print("  ");
		}
		System.out.println(restants);
		
		Monceau monceau = new Monceau();
		monceau.print();
		
		//test insertion
		monceau.insert(0);
		monceau.insert(7);
		monceau.insert(9);
		monceau.insert(4);
		monceau.print();
		
		
		monceau.insert(4);
		monceau.print();
		
		monceau.delete(7);
		monceau.delete(4);
		monceau.print();
		
		monceau.delete(3);
		monceau.print();
		
		Monceau monceau2 = new Monceau();
		for(int i = 0; i < 10; i++){
			monceau2.insert((int)(Math.random()*100) + 1);
		}
		monceau2.print();
		
		monceau.fusion(monceau2);
		monceau.print();
	}

}
