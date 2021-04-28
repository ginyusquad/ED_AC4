package exercicio01.c_d.sources;

import java.util.Comparator;
import utils.tree.CompleteBinaryTree;
import utils.comparators.DefaultComparator;
import utils.exceptions.EmptyPriorityQueueException;
import utils.exceptions.InvalidKeyException;

import exercicio01.b.sources.ArrayListCompleteBinaryTree;

import utils.tab_lista_de_nodos.Position;

public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
	
	protected CompleteBinaryTree<Entry<K, V>> cabeca;
	protected Comparator<K> comparador;

	protected static class MyEntry<K, V> implements Entry<K, V> {

		protected K key;

		protected V value;

		public MyEntry(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public String toString() {
			return "(" + key + "," + value + ")";
		}
	}
	public HeapPriorityQueue() {
		cabeca = new ArrayListCompleteBinaryTree<Entry<K, V>>(); // Usa o ArrayListCompleteBinaryTree
		comparador = new DefaultComparator<K>(); // usa o comparador padrão
	}
	public HeapPriorityQueue(Comparator<K> c) {
		cabeca = new ArrayListCompleteBinaryTree<Entry<K, V>>();
		comparador = c;
	}
	public void setComparator(Comparator<K> c) throws IllegalStateException {
		if (!isEmpty())throw new IllegalStateException("Priority queue is not empty");
		
		comparador = c;
	}
	public int size() {
		return cabeca.size();
	}
	public boolean isEmpty() {
		return cabeca.size() == 0;
	}
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("Priority queue is empty");
		return cabeca.root().element();
	}
	public Entry<K, V> insert(K k, V x) throws InvalidKeyException {
		checkKey(k);
		Entry<K, V> entry = new MyEntry<K, V>(k, x);
		upHeap(cabeca.add(entry));
		return entry;
	}
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("Priority queue is empty");
		Entry<K, V> min = cabeca.root().element();
		if (size() == 1)
			cabeca.remove();
		else {
			cabeca.replace(cabeca.root(), cabeca.remove());
			downHeap(cabeca.root());
		}
		return min;
	}
	protected void checkKey(K key) throws InvalidKeyException {
		
		try {
			comparador.compare(key, key);
		} catch (Exception e) {
			throw new InvalidKeyException("Invalid key");
		}
	}
	protected void upHeap(Position<Entry<K, V>> v) {
		
		Position<Entry<K, V>> u;
		while (!cabeca.isRoot(v)) {
			u = cabeca.parent(v);
			if (comparador.compare(u.element().getKey(), v.element().getKey()) <= 0)
				break;
			swap(u, v);
			v = u;
		}
	}
	protected void downHeap(Position<Entry<K, V>> r) {
		
		while (cabeca.isInternal(r)) {
			Position<Entry<K, V>> s;
			if (!cabeca.hasRight(r))
				s = cabeca.left(r);
			else if (comparador.compare(cabeca.left(r).element().getKey(), cabeca.right(r).element().getKey()) <= 0)
				s = cabeca.left(r);
			else
				s = cabeca.right(r);
			if (comparador.compare(s.element().getKey(), r.element().getKey()) < 0) {
				swap(r, s);
				r = s;
			} else
				break;
		}
	}
	protected void swap(Position<Entry<K, V>> x, Position<Entry<K, V>> y) {
		Entry<K, V> temp = x.element();
		cabeca.replace(x, y.element());
		cabeca.replace(y, temp);
	}
	public String toString() {
		return cabeca.toString();
	}
}
