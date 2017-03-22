import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<? super T> > 
{
	enum ChildType{ left, right }
	private RBNode<T> root;  // Racine de l'arbre

	public RedBlackTree(){ 
		root = null;
	}

	public void printFancyTree(){
		printFancyTree( root, "", ChildType.right);
	}
	private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType){
		//
		System.out.print( prefix + "|__"); // un | et trois _  
		if( t != null ){
			boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
			System.out.println( t );
			String _prefix = prefix;
			if( myChildType == ChildType.left )
				_prefix += "|   "; // un | et trois espaces
			else
				_prefix += "   " ; // trois espace
			if( !isLeaf ){
				printFancyTree( t.leftChild, _prefix, ChildType.left );
				printFancyTree( t.rightChild, _prefix, ChildType.right );
			}
		}
		else
			System.out.print("null\n");
	}
	/*
	 * recherche d'une cl�
	 */
	public T find(int key){
		return find(root, key);
	}
	private T find(RBNode<T> current, int key){
		// � completer 
	}
	/*
	 * insertion d'une valeur 
	 */
	public void insert(T val){
		insertNode( new RBNode<T>( val ) );
	}

	private void insertNode( RBNode<T> newNode ){ 
		if (root == null)  // Si arbre vide
			root = newNode;
		else{
			RBNode<T> position = root; // On se place a la racine     
			while( true ) // on insere le noeud (ABR standard)
			{
				int newKey = newNode.value.hashCode();
				int posKey = position.value.hashCode();
				if (newKey < posKey ){
					if ( position.leftChild.isNil()){
						position.leftChild = newNode;
						newNode.parent = position;
						break;
					} 
					else 
						position = position.leftChild;
				}else if ( newKey > posKey ){
					if ( position.rightChild.isNil()){
						position.rightChild = newNode;
						newNode.parent = position;
						break;
					}
					else 
						position = position.rightChild;
				}else // pas de doublons
					return;
			}
		}
		insertionCases( newNode );
	}
	/*
	 * les cas d'insertion 
	 */
	private void insertionCases( RBNode<T> X ){
		if (X==root)
			insertionCase1( X );
		else if(X.parent.isBlack())
			insertionCase2(X);
		else if(X.parent.isRed() && X.uncle().isRed())
			insertionCase3(X);
		else if(X.parent.isRed() && X.uncle().isBlack() && X == X.parent.leftChild && X.parent == X.grandParent().rightChild)
			insertionCase4(X, ChildType.left);
		else if(X.parent.isRed() && X.uncle().isBlack() && X == X.parent.rightChild && X.parent == X.grandParent().leftChild)
			insertionCase4(X, ChildType.right);
		else
			insertionCase5(X);

	}
	private void insertionCase1 ( RBNode<T> X ){
		X.setToBlack();
	}

	private void insertionCase2( RBNode<T> X ){
	}

	private void insertionCase3( RBNode<T> X ){
		X.parent.setToBlack();
		X.uncle().setToBlack();
		X.grandParent().setToRed();
		insertionCases(X.grandParent());
	}

	private void insertionCase4( RBNode<T> X, ChildType type){
		if (type == ChildType.left){
			rotateRight(X.parent);
			insertionCase5(X.rightChild);
		} else{
			rotateLeft(X.parent);
			insertionCase5(X.leftChild);
		}

	}

	private void insertionCase5( RBNode<T> X){
		if(X.parent.isRed() && X.uncle().isBlack() && X == X.parent.rightChild && X.parent == X.grandParent().rightChild){
			X.grandParent().switchColor();
			X.parent.switchColor();
			this.rotateLeft(X.grandParent());
		}else if(X.parent.isRed() && X.uncle().isBlack() && X == X.parent.leftChild && X.parent == X.grandParent().leftChild){
			X.grandParent().switchColor();
			X.parent.switchColor();
			this.rotateRight(X.grandParent());
		}
	}

	private void rotateLeft( RBNode<T> P )
	{
		RBNode<T> X = P.rightChild;

		P.rightChild		= X.leftChild;
		X.leftChild			= P;
		P.rightChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.leftChild	= X;
	}

	private void rotateRight( RBNode<T> P)
	{
		RBNode<T> X = P.leftChild;

		P.leftChild			= X.rightChild;
		X.rightChild		= P;
		P.leftChild.parent	= P;
		X.parent 			= P.parent;
		P.parent 			= X;
		X.parent.rightChild	= X;
	}

	public void printTreePreOrder()
	{
		if(root == null)
			System.out.println( "Empty tree" );
		else
		{
			System.out.print( "PreOrdre [ ");
			printTreePreOrder( root );
			System.out.println( " ]");
		}
		return;
	}
	public void printTreePostOrder()
	{
		if(root == null)
			System.out.println( "Empty tree" );
		else
		{
			System.out.print( "PostOrdre [ ");
			printTreePostOrder( root );
			System.out.println( " ]");
		}
		return;
	}
	public void printTreeAscendingOrder()
	{
		if(root == null)
			System.out.println( "Empty tree" );
		else
		{
			System.out.print( "AscendingOrdre [ ");
			printTreeAscendingOrder( root );
			System.out.println( " ]");
		}
		return;
	}

	public void printTreeDescendingOrder()
	{
		if(root == null)
			System.out.println( "Empty tree" );
		else
		{
			System.out.print( "DescendingOrdre [ ");
			printTreeDescendingOrder( root );
			System.out.println( " ]");
		}
		return;
	}


	private void printTreePreOrder( RBNode<T> P )
	{
		// A MODIFIER/COMPLETER
		//pour ne pas afficher la virgule apres le dernier element
	}
	private void printTreePostOrder( RBNode<T> P )
	{
		// A MODIFIER/COMPLETER
	}

	private void printTreeAscendingOrder( RBNode<T> P )
	{
		// A MODIFIER/COMPLETER

	}

	private void printTreeDescendingOrder( RBNode<T> P )
	{
		// A MODIFIER/COMPLETER

	}

	public void printTreeLevelOrder()
	{
		if(root == null)
			System.out.println( "Empty tree" );
		else
		{
			System.out.print( "LevelOrdre [ ");

			Queue<RBNode<T>> q = new LinkedList<RBNode<T>>();
			q.add(root);

			// A COMPLETER


			System.out.println( " ]");
		}
		return;
	}

	private static class RBNode<T extends Comparable<? super T> > 
	{
		enum RB_COLOR { BLACK, RED }  // Couleur possible 
		RBNode<T>  parent;      // Noeud parent
		RBNode<T>  leftChild;   // Feuille gauche
		RBNode<T>  rightChild;  // Feuille droite
		RB_COLOR   color;       // Couleur du noeud
		T          value;       // Valeur du noeud
		// Constructeur (NIL)   
		RBNode() { setToBlack(); }
		// Constructeur (feuille)   
		RBNode(T val){
			setToRed();
			value = val;
			leftChild = new RBNode<T>();
			leftChild.parent = this;
			rightChild = new RBNode<T>();
			rightChild.parent = this;
		}

		RBNode<T> grandParent(){
			return this.parent.parent;
		}

		RBNode<T> uncle(){
			if(this.parent.parent.leftChild.equals(this.parent))
				return this.parent.parent.rightChild;
			else
				return this.parent.parent.leftChild;
		}

		RBNode<T> sibling(){
			if(this.parent.leftChild.equals(this))
				return this.parent.rightChild;
			else
				return this.parent.leftChild; 
		}

		public String toString()
		{
			return value + " , " + (color == RB_COLOR.BLACK ? "black" : "red"); 
		}

		boolean isBlack(){ return (color == RB_COLOR.BLACK); }
		boolean isRed(){ return (color == RB_COLOR.RED); }
		boolean isNil(){ return (leftChild == null) && (rightChild == null); }

		void switchColor(){
			if (this.isBlack())
				this.setToRed();
			else
				this.setToBlack();
		}
		void setToBlack(){ color = RB_COLOR.BLACK; }
		void setToRed(){ color = RB_COLOR.RED; }
	}
}
