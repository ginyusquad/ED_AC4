package exercicio02;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import exercicio01.c_d.sources.HeapPriorityQueue;

public class HeapPriorityQueueTest {

	@Test
	public void Ex_02() {
		
		HeapPriorityQueue<Integer, Character> heap;
		heap = new HeapPriorityQueue<Integer, Character>();
		
		heap.insert(5, 'A');
		heap.insert(4, 'B');
		heap.insert(7, 'I');
		heap.insert(1, 'D');
		// Opera��o
		assertEquals("(1,D)", heap.removeMin().toString());
		
		heap.insert(3, 'J');
		heap.insert(6, 'L');
		// Opera��o
		assertEquals("(3,J)", heap.removeMin().toString());
		// Opera��o
		assertEquals("(4,B)", heap.removeMin().toString());
		
		heap.insert(8, 'G');
		// Opera��o
		assertEquals("(5,A)", heap.removeMin().toString());

		heap.insert(2, 'H');
		// Opera��o
		assertEquals("(2,H)", heap.removeMin().toString());
		// Opera��o
		assertEquals("(6,L)", heap.removeMin().toString());

		// Resultado Final!!!!
		assertEquals("[null, [(7,I),1], [(8,G),2]]", heap.toString());
	}
}
