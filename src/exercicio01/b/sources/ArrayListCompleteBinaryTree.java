package exercicio01.b.sources;

import java.util.ArrayList;
import java.util.Iterator;

import utils.exceptions.BoundaryViolationException;
import utils.exceptions.EmptyTreeException;
import utils.exceptions.InvalidPositionException;
import utils.tree.CompleteBinaryTree;
import utils.tab_lista_de_nodos.NodePositionList;
import utils.tab_lista_de_nodos.Position;
import utils.tab_lista_de_nodos.PositionList;


public class ArrayListCompleteBinaryTree<T> implements CompleteBinaryTree<T> {

	protected ArrayList<BTPos<T>> Tree; 
	// Classe  interna
	protected static class BTPos<E> implements Position<E> {
		E element;
		int index;
		public BTPos(E elm, int i) {
			element = elm;
			index = i;
		}
		public E element() {
			return element;
		}
		public int index() {
			return index;
		}
		public E setElement(E elm) {
			E tmp = element;
			element = elm;
			return tmp;
		}
		public String toString() {
			return ("[" + element + "," + index + "]");
		}
	}
	public ArrayListCompleteBinaryTree() {
		Tree = new ArrayList<BTPos<T>>();
		Tree.add(0, null);
	}
	public int size() {
		return Tree.size() - 1;
	}
	public boolean isEmpty() {
		
		return (size() == 0);
	}
	public boolean isInternal(Position<T> v) throws InvalidPositionException {
		
		return hasLeft(v);
	}
	public boolean isExternal(Position<T> v) throws InvalidPositionException {
		
		return !isInternal(v);
	}
	protected BTPos<T> checkPosition(Position<T> v) throws InvalidPositionException {
		
		if (v == null || !(v instanceof BTPos))
			throw new InvalidPositionException("Position is invalid");
		return (BTPos<T>) v;
	}
	public boolean isRoot(Position<T> v) throws InvalidPositionException {
		
		BTPos<T> vv = checkPosition(v);
		return vv.index() == 1;
	}
	public boolean hasLeft(Position<T> v) throws InvalidPositionException {
		
		BTPos<T> vv = checkPosition(v);
		return 2 * vv.index() <= size();
	}
	public boolean hasRight(Position<T> v) throws InvalidPositionException {
		
		BTPos<T> vv = checkPosition(v);
		return 2 * vv.index() + 1 <= size();
	}
	public Position<T> root() throws EmptyTreeException {
		if (isEmpty())throw new EmptyTreeException("Arvore vazia!");
		
		return Tree.get(1);
	}
	public Position<T> left(Position<T> v) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(v))throw new BoundaryViolationException("Não existe a esquerda");
		
		BTPos<T> vv = checkPosition(v);
		
		return Tree.get(2 * vv.index());
	}
	public Position<T> right(Position<T> v) throws InvalidPositionException {
		if (!hasRight(v))throw new BoundaryViolationException("Não existe a direita");
		
		BTPos<T> vv = checkPosition(v);
		
		return Tree.get(2 * vv.index() + 1);
	}
	public Position<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException {
		if (isRoot(v))throw new BoundaryViolationException("Não existe parente");

		BTPos<T> vv = checkPosition(v);
		
		return Tree.get(vv.index() / 2);
	}
	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException {
		PositionList<Position<T>> children = new NodePositionList<Position<T>>();
		if (hasLeft(v))
			children.addLast(left(v));
		if (hasRight(v))
			children.addLast(right(v));
		return children;
	}
	public Iterable<Position<T>> positions() {
		ArrayList<Position<T>> P = new ArrayList<Position<T>>();
		Iterator<BTPos<T>> iter = Tree.iterator();
		iter.next();
		while (iter.hasNext())
			P.add(iter.next());
		return (Iterable<Position<T>>) P;
	}
	public T replace(Position<T> v, T o) throws InvalidPositionException {
		BTPos<T> vv = checkPosition(v);
		return vv.setElement(o);
	}
	public Position<T> add(T e) {
		int i = size() + 1;
		BTPos<T> p = new BTPos<T>(e, i);
		Tree.add(i, p);
		return p;
	}
	public T remove() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("Tree is empty");
		return Tree.remove(size()).element();
	}
	public Position<T> sibling(Position<T> v) throws InvalidPositionException, BoundaryViolationException {
		try {
			Position<T> p = parent(v);
			Position<T> lc = left(p);
			if (v == lc)
				return right(p);
			else
				return lc;
		} catch (BoundaryViolationException e) {
			throw new BoundaryViolationException("Node has no sibling");
		}
	}
	public void swapElements(Position<T> v, Position<T> w) throws InvalidPositionException {
		BTPos<T> vv = checkPosition(v);
		BTPos<T> ww = checkPosition(w);
		T tmp = vv.element();
		vv.setElement(ww.element());
		ww.setElement(tmp);
	}
	public Iterator<T> iterator() {
		
		ArrayList<T> lista = new ArrayList<T>();
		Iterator<BTPos<T>> interador = Tree.iterator();
		// Pula para o proximo
		interador.next();
		while (interador.hasNext()){
			lista.add(interador.next().element());
		}
		return lista.iterator();
	}
	public String toString() {
		return Tree.toString();
	}
}