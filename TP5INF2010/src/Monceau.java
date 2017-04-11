
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    public void fusion(Monceau autre) {
        int ordreMax = 0;
    	for(Node arbre: arbres){
        	ordreMax = (arbre.ordre > ordreMax? arbre.ordre: ordreMax);
        }
    	for(Node arbre: autre.arbres){
        	ordreMax = (arbre.ordre > ordreMax? arbre.ordre: ordreMax);
        }
    	ArrayList<Node> listeArbresOrdreJ;
    	Node retenue = null;
    	Monceau monceauFinal = new Monceau();
    	for(int j=0; j<=ordreMax; j++){
    		listeArbresOrdreJ = new ArrayList<Node>();
    		Node arbreTemp;
    		for(Node arbre: arbres){
            	if(arbre.ordre == j){
            		arbreTemp = new Node(arbre.getVal());
            		arbreTemp.ordre = arbre.ordre;
            		for(Node enfant: arbre.getEnfants()){
            			arbreTemp.addEnfant(enfant);
            		}
            		listeArbresOrdreJ.add(arbreTemp);
            		break;
            	}
            }
        	for(Node arbre: autre.arbres){
        		if(arbre.ordre == j){
        			arbreTemp = new Node(arbre.getVal());
            		arbreTemp.ordre = arbre.ordre;
            		for(Node enfant: arbre.getEnfants()){
            			arbreTemp.addEnfant(enfant);
            		}
            		listeArbresOrdreJ.add(arbreTemp);
        			break;
        		}
            }
        	if (retenue != null)
        		listeArbresOrdreJ.add(retenue);
        	switch(listeArbresOrdreJ.size()){
        	case 1:
        		monceauFinal.arbres.add(listeArbresOrdreJ.get(0));
        		retenue = null;
        		break;
        	case 2:
        		try {
					retenue = listeArbresOrdreJ.get(0).fusion(listeArbresOrdreJ.get(1));
				} catch (DifferentOrderTrees e) {
					e.printStackTrace();
				}
        		break;
        	case 3:
        		try {
					retenue = listeArbresOrdreJ.get(0).fusion(listeArbresOrdreJ.get(1));
				} catch (DifferentOrderTrees e) {
					e.printStackTrace();
				}
        		monceauFinal.arbres.add(listeArbresOrdreJ.get(2));
        		break;
        	}
    	}
    	arbres = monceauFinal.arbres;
    	if(retenue!=null)
    		arbres.add(retenue);
    }
    
    public void insert(int val) {
        Node nouvelArbre = new Node(val);
        Monceau nouveauMonceau = new Monceau();
        nouveauMonceau.arbres.add(nouvelArbre);
        fusion(nouveauMonceau);
    }
    
    public boolean delete (int val) {
//    	boolean deleted = false;
//		for(int i=0; i< arbres.size(); i++){
//			Node arbre = arbres.get(i);
//    	//for(Node arbre : arbres){
//			Node noeud = arbre.findValue(val);
//			Monceau nouvMonceau;
//			while(noeud != null){
//				Node parent = noeud;
//				while(parent.parent!=null){
//					parent = parent.parent;
//				}
//				arbres.remove(parent);
//				nouvMonceau = new Monceau();
//				deleted = true;
//				nouvMonceau.arbres = noeud.delete();
//				nouvMonceau.print();
//				print();
//				fusion(nouvMonceau);
//				print();
//				noeud = arbre.findValue(val);
//			}
//		} 
//		return deleted;
    	/**
    	 * 
    	 */
    	ArrayList<Monceau> monceaux = new ArrayList<Monceau>();
    	boolean deleted = false;
//		for(Node arbre : arbres){
    	for(int i=0; i< arbres.size(); i++){
			Node arbre = arbres.get(i);
			Node noeud = arbre.findValue(val);
			Monceau nouvMonceau;
			if(noeud != null){
//				Node parent = noeud;
//				while(parent.parent!=null){
//					parent = parent.parent;
//				}
				arbres.remove(arbre);
				deleted = true;
				nouvMonceau = new Monceau();
				nouvMonceau.arbres = noeud.delete();
				System.out.println(".a.auafkj,.vpejoo--=843");
				nouvMonceau.print();
				monceaux.add(nouvMonceau);
				noeud = arbre.findValue(val);
				System.out.println(".a.auafkj,.vpejoo--=843");
			}
		} 
		for(Monceau monceau: monceaux){
			fusion(monceau);
		}
		return deleted;
    }
    
    public void print() {
        for(Node arbre: arbres){
        	System.out.println("Arbre d'ordre " + arbre.ordre);
        	arbre.print("  ");
        }
        System.out.println("__________________________________\n");
    }
    
}
