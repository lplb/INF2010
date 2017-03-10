package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> {
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing() {
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	public int Size(){
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key) {
		return items[key] != null;
	}

	public boolean containsValue(AnyType x ) {
		int cle = getKey(x);
		return items[cle]!=null && items[cle].equals(x);
	}

	public void remove (AnyType x) {
		items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		return ((a*x.hashCode() + b) % p) % items.length;
		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array) {
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0) {
			a = b = 0; items = null;
			return;
		}
		if(array.size() == 1) {
			a = b = 0;
			
			items = (AnyType[]) new Object[1];
			items[0] = array.get(0);
			
			return;
		}

		do {
			items = (AnyType[]) new Object[array.size()*array.size()];

			a = generator.nextInt(p+1) + 1;
			b = generator.nextInt(p);
		}
		while( collisionExists( array ) );
	}

	private boolean collisionExists(ArrayList<AnyType> array) {
		int cle;
		for(int i = 0; i < array.size(); i++){
			cle = getKey(array.get(i));
			if (items[cle]!=null) return true;
			items[cle] = array.get(i);
		}
		return false;
	}
	
	public String toString () {
		String result = "";
		
		for (int i = 0; i < items.length; i++){
			if (items[i]!=null) {
				result += ("( " + i + ", " + items[i] + "), ");
			}
		}
		
		
		return result; 
	}
}
