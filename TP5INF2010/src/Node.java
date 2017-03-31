
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {
    	if (this.ordre == autre.ordre && this.parent == null && autre.parent == null){
    		Node nouvNoeud, nTemp;
    		if (this.valeur<autre.valeur) {
    			nouvNoeud = new Node(this.valeur);
    			nouvNoeud.enfants = new ArrayList<Node>(this.enfants);
    			nouvNoeud.ordre = this.ordre;
    			nTemp = new Node(autre.valeur, nouvNoeud);
    			nTemp.enfants = new ArrayList<Node>(autre.enfants);
    			nTemp.ordre = autre.ordre;
				nouvNoeud.enfants.add(nTemp);
			} else {
				nouvNoeud = new Node(autre.valeur);
    			nouvNoeud.enfants = new ArrayList<Node>(autre.enfants);
    			nouvNoeud.ordre = autre.ordre;
    			nTemp = new Node(this.valeur, nouvNoeud);
    			nTemp.enfants = new ArrayList<Node>(this.enfants);
    			nTemp.ordre = this.ordre;
				nouvNoeud.enfants.add(nTemp);
			}
    		nouvNoeud.ordre++;
			return nouvNoeud;
    	}
		throw new DifferentOrderTrees();
    }

    private void moveUp() {
        while(parent!=null){
        	Node temp = new Node(parent.valeur, parent.parent);
        	temp.enfants = parent.enfants;
        	temp.enfants.remove(this);
        	for(Node enfant: enfants){
        		enfant.parent = parent;
        	}
        	parent.enfants = enfants;
        	enfants = temp.enfants;
        	enfants.add(parent);
        	parent = temp.parent;
        	for(Node enfant: enfants){
        		enfant.parent = this;
        		//enfant.print("  ");        		
        	}
        	//print("  ");
        }
    }

    public ArrayList<Node> delete() {
        moveUp();
        for (Node enfant: enfants){
        	enfant.parent = null;
        }
        return enfants;
    }

    public void print(String tabulation) {
        System.out.print(valeur);
        for(Node enfant: enfants){
        	enfant.print(tabulation, 1);
        	System.out.print("\n ");
        }
        System.out.println("\n");
    }
    
    private void print(String tabulation, int niveauActuel) {
    	System.out.print(tabulation);
    	System.out.print(valeur);
        for(Node enfant: enfants){
        	enfant.print(tabulation, niveauActuel+1);
        	if(enfants.indexOf(enfant) < enfants.size() - 1){
        		System.out.print("\n ");
        		for (int i = 0; i < niveauActuel; i++){
        			System.out.print(tabulation + " ");
        		}
        	}
        }
	}

	public Node findValue(int valeur) {
        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
        if(valeur == n.valeur){
        	return n;
        } else {
        	Node nTemp = null;
        	for (Node enfant: n.enfants){
        		if (valeur >= enfant.valeur){
        			nTemp = findValue(enfant, valeur);
        			if ( nTemp != null){
        				return nTemp;
        			}
        		}
        	}
        	return null;
        }
    }
    
}
