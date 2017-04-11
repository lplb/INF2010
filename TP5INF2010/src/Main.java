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
		
		
//		
//		noeud1.print("  ");
//		System.out.println("_________________________________________");
		try {
			noeud1.fusion(noeud2);
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
//		noeud1.print("  ");
//		System.out.println("_________________________________________");
		
		try {
			noeud1.fusion(noeud3);
		} catch (DifferentOrderTrees e) {
//			System.out.println("Arbres de differents ordres");
		}
		
		try {
			noeud1.fusion(noeud3.fusion(noeud4));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
//		noeud1.print("  ");
//		System.out.println("_________________________________________");
//		
//		noeud1.findValue(3).print("  ");
//		System.out.println("_________________________________________");
//		
//		noeud1.findValue(2).print("  ");
//		System.out.println("_________________________________________");
		/*try {
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
		*/
		
		Monceau monceau = new Monceau();
		monceau.print();
		
		//test insertion
		//monceau.insert(0);
		//monceau.insert(1);
		monceau.arbres = noeud1.findValue(3).delete();
		//monceau.insert(4);
		
		monceau.print();
		Monceau monceau2 = new Monceau();
		try {
			monceau2.arbres.add(noeud5.fusion(noeud6));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		monceau.fusion(monceau2);
		monceau.print();

		Monceau monceau3 = new Monceau();
		try {
			monceau3.arbres.add(noeud7.fusion(noeud8));
		} catch (DifferentOrderTrees e) {
			e.printStackTrace();
		}
		//test fusion
		monceau.fusion(monceau3);
		
		monceau.print();
				
		monceau.insert(10);
		monceau.print();
		monceau.insert(4);
		monceau.print();
		monceau.insert(75);
		monceau.print();
		monceau.insert(3);
		monceau.print();
		monceau.insert(9);
		monceau.print();
		monceau.insert(11);
		monceau.print();
		monceau.insert(4);
		monceau.print();
		monceau.insert(75);
		monceau.print();
		monceau.insert(3);
		monceau.print();
		monceau.insert(9);
		monceau.print();

		monceau.delete(11);
		monceau.print();
		
	}

}
