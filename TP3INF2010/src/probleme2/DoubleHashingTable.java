package probleme2;

public class DoubleHashingTable<AnyType> {

	private static final int DEFAULT_SIZE = 101;
	private int currentSize;
	private HashEntry<AnyType>[] array;


	public DoubleHashingTable(){
		this(DEFAULT_SIZE);
	}

	public DoubleHashingTable(int length){
		allocateArray(length);
	}

	public void makeEmpty(){
		currentSize = 0;
		for(int i = 0; i < array.length; i++)
			array[i] = null;
	}

	public void insert(AnyType obj){

		int currentPos = findPos(obj);
		if( isActive( currentPos ) )
			return;

		array[currentPos] = new HashEntry<AnyType>(obj, true);

		if(++currentSize > array.length / 2)
			reHash( );

	}

	public AnyType get(AnyType obj){

		int currentPos = findPos(obj);
		
		if(isActive(currentPos))
			return array[currentPos].element;
		else
			return null;
	}

	public int nbElement(){
		return currentSize;
	}

	private int findPos(AnyType obj){
		int collisions = 0;
		int currentPos = hash(obj, collisions);
		
		while(array[currentPos] != null && !array[currentPos].element.equals(obj)){
			collisions++;
			currentPos = hash(obj, collisions);
			if( currentPos >= array.length )
				currentPos %= array.length;
		}
		
		return currentPos;
	}

	private int hash(AnyType obj, int collisions){
		int hashVal = Math.abs(obj.hashCode());

		int h1 = hashVal % array.length;

		final int R = previousPrime(array.length);
		int h2 = R - (hashVal % R);
		hashVal = h1 + collisions * h2;
		return hashVal;
	}

	private void reHash(){
		HashEntry<AnyType>[] temp = array;

		this.allocateArray(nextPrime(temp.length*2));
		currentSize = 0;

		for(int i = 0; i < temp.length; i++){
			if(temp[i] != null && temp[i].isActive)
				insert(temp[i].element);
		}

	}

	@SuppressWarnings("unchecked")
	private void allocateArray(int length){
		array = new HashEntry[nextPrime(length)];
	}

	private static int previousPrime(int n){
		n--;
		if( n <= 3 )
			return 3;

		while (!isPrime(n)){
			n--;
		}
		return n;
	}

	private static int nextPrime(int n){
		if( n <= 0 )
			n = 3;

		if( n % 2 == 0 )
			n++;

		for( ; !isPrime( n ); n += 2 );

		return n;
	}

	private boolean isActive( int currentPos ){
		return array[currentPos] != null && array[currentPos].isActive;
	}

	private static boolean isPrime(int n){
		if( n == 2 || n == 3 )
			return true;

		if( n == 1 || n % 2 == 0 )
			return false;

		for( int i = 3; i * i <= n; i += 2 )
			if( n % i == 0 )
				return false;

		return true;
	}

	private static class HashEntry<AnyType>{

		public AnyType  element;
		public boolean isActive;

		@SuppressWarnings("unused")
		public HashEntry( AnyType e ){
			this( e, true );
		}

		public HashEntry( AnyType e, boolean i ){
			element  = e;
			isActive = i;
		}
	}


}
