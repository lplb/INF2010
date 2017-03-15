package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType> {
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing() {
		a = b = 0;
		data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array) {
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array) {
		Random generator = new Random(System.nanoTime());

		if (array == null || array.size() == 0) {
			a = b = 0;
			data = null;
			return;
		}
		if (array.size() == 1) {
			a = b = 0;
			data = new QuadraticSpacePerfectHashing[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}
		data = new QuadraticSpacePerfectHashing[array.size()];
		a = generator.nextInt(p+1) + 1;
		b = generator.nextInt(p);
		ArrayList<AnyType>[] listeArrays = new ArrayList[array.size()];
		ArrayList<Integer> listeCles = new ArrayList<Integer>();
		for (AnyType element: array){
			int cle = getKey(element);
			ArrayList<AnyType> arrayTemp;
			arrayTemp = (listeArrays[cle]==null? new ArrayList<AnyType>(): listeArrays[cle]);
			arrayTemp.add(element);
			listeArrays[cle] = arrayTemp;
			listeCles.add(cle);
		}
		for (Integer cle: listeCles){
			data[cle] = new QuadraticSpacePerfectHashing<AnyType>(listeArrays[cle]);
		}
	}

	public int Size() {
		if (data == null)
			return 0;

		int size = 0;
		for (int i = 0; i < data.length; ++i) {
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key) {
		return data[key] != null;
	}

	public int getKey(AnyType x) {
		return ((a*x.hashCode() + b) % p) % data.length;
	}

	public boolean containsValue(AnyType x) {
		int cle = getKey(x);
		return data[cle]!=null && data[cle].containsValue(x);
	}

	public void remove(AnyType x) {
		int cle = getKey(x);
		if (data[cle] != null) data[cle].remove(x);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < Size(); i++){
			if(data[i] != null){
				result += "[clé_" + i + "] -> " + data[i];
			}
		}
		return result;
	}

}
