
public class BinaryTree<AnyType extends Comparable<AnyType>> {
	private Node<AnyType> root = null; // Racine de l'arbre

	// insert element in arbre 
	public void insert (AnyType elem) {
	        if(root==null){
	        	this.root= new Node<AnyType>(elem);
	        }else	
	        	insert(root, elem);      
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void insert(Node<AnyType> node, AnyType elem) {
		Node nouvNoeud = new Node<AnyType>(elem);
		if (node == null) {
			node = nouvNoeud;
		} else {
			if (elem.compareTo(node.val) < 0) {
				if (node.left == null) {
					node.left = nouvNoeud;
				} else { 
					insert(node.left, elem);
				}
			} else {
				if (node.right == null) {
					node.right = nouvNoeud;
				} else { 
					insert(node.right, elem);
				}
			} 			
		}
	}
    
	
	public int getHauteur () {
		return this.getHauteur(this.root);
	}
 
	public String printPrefixe() {
		return "{ " + this.printPrefixe(this.root) + " }";
	}
	public String printInFixe() {
		return "{ " + this.printInfixe(this.root) + " }";
	}
	
	public String printPostFixe() {
		return "{ " + this.printPostfixe(this.root) + " }";
	}
	
	@SuppressWarnings("unchecked")
	private int getHauteur(Node<AnyType> tree) {
		int hauteur = 1;
		if (tree.left != null){
			if (tree.right != null){
				hauteur += Math.max(getHauteur(tree.left), getHauteur(tree.right));
			} else {
				hauteur += getHauteur(tree.left);
			}
		} else {
			if (tree.right != null)
				hauteur += getHauteur(tree.right);
		}
		return hauteur;
	}	
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		String result = node.val + ", ";
		if(node.left != null)
			result += printPrefixe(node.left);
		if(node.right != null) 
			result += printPrefixe(node.right);
		return result;
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		String result = "";
		if(node.left != null)
			result += printInfixe(node.left);
		result += node.val + ", ";
		if(node.right != null) 
			result += printInfixe(node.right);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		String result = "";
		if(node.left != null)
			result += printPostfixe(node.left);
		if(node.right != null) 
			result += printPostfixe(node.right);
		result += node.val + ", ";
		return result;
	}
	
	private class Node<AnyType> {
		AnyType val; // Valeur du noeud
		Node right; // fils droite
		Node left; // fils gauche

		public Node (AnyType val) {
			this.val = val;
			right = null;
			left = null;
		}

	}


}

