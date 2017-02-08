
public class LinkedListQueue<AnyType> implements Queue<AnyType> {
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> {
		private AnyType data;
		private Node next;

		public Node(AnyType data, Node next) {
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node<AnyType> getNext() {
			return next;
		}

		public AnyType getData() {
			return data;
		}
	}

	private int size = 0; // Nombre d'elements dans la file.
	private Node<AnyType> last; // Dernier element de la liste
	private Node<AnyType> first; // Premier element de la liste
	
	public LinkedListQueue(){
		this.first = new Node(null,null);
		this.last = first;
	}

	// Indique si la file est vide
	public boolean empty() {
		return size == 0;
	}

	// Retourne la taille de la file
	public int size() {
		return size;
	}

	// Retourne l'element en tete de file
	// Retourne null si la file est vide
	// complexité asymptotique: O(1)
	public AnyType peek() {
		if (this.empty())
			return null;
		else
			return this.first.getData();
	}

	// Retire l'element en tete de file
	// complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		this.first = this.first.getNext();
		size--;
	}

	// Ajoute un element a la fin de la file
	// complexité asymptotique: O(1)
	public void push(AnyType item) {
		if(this.empty()){
			this.first = new Node(item,null);
			this.last = this.first;			
		} else {
			Node nouvNoeud = new Node(item,null);
			last.setNext(nouvNoeud);
			this.last = nouvNoeud;
		}
		this.size++;
	}
}
