public class ArrayQueue<AnyType extends Object> implements Queue<AnyType> {
	private final int INIT_SIZE = 10;
	private int size = 0; // Nombre d'elements dans la file.
	private int startIndex = 0; // Index du premier element de la file
	private AnyType[] table;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		table = (AnyType[]) new Object[INIT_SIZE];
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
		if(!this.empty())
			return table[startIndex];
		else
			return null;
	}

	// Retire l'element en tete de file
	// complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		if(!this.empty()){
			startIndex++;
			size--;
		}
	}

	// Ajoute un element a la fin de la file
	// Double la taille de la file si necessaire
	// complexité asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est
	// necessaire )
	public void push(AnyType item) {
		try{
			table[size+startIndex] = item;
			size++;
		} catch(ArrayIndexOutOfBoundsException e){
			resize(2);
			table[size+startIndex] = item;
			size++;
		}
	}

	// Redimensionne la file. La capacite est multipliee par un facteur de
	// resizeFactor.
	// Replace les elements de la file au debut du tableau
	// complexité asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor) {
		AnyType[] temp = (AnyType[]) new Object[size*resizeFactor + 1];
		for(int i = startIndex, j = 0; i<size+startIndex; i++, j++){
			temp[i] = table[j];
		}
		table = temp;
		startIndex = 0;

	}
}
