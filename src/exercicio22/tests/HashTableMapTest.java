package exercicio22.tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import exercicio22.sources.Entry;
import exercicio22.sources.HashTableMap;
import exercicio22.sources.HeapPriorityQueue;

public class HashTableMapTest {

	@Test
	public void test() {
		HashTableMap<Integer, Character> mapa = new HashTableMap<Integer, Character>();
		assertEquals(true, mapa.isEmpty());
		assertNull(mapa.put(1, 'X'));
		assertEquals("{(1,X)}", ordenar(mapa.entrySet()));
		assertNull(mapa.put(6, 'Z'));
		assertEquals("{1, 6}", ordenarKeys(mapa.keySet()));
		assertNull(mapa.put(4, 'W'));
		assertEquals("{(1,X), (4,W), (6,Z)}", ordenar(mapa.entrySet()));
		assertNull(mapa.put(7, 'V'));
		assertNull(mapa.put(9, 'H'));
		assertNull(mapa.put(11, 'K'));
		assertEquals("{(1,X), (4,W), (6,Z), (7,V), (9,H), (11,K)}", ordenar(mapa.entrySet()));
		assertNull(mapa.put(8, 'N'));
		assertNull(mapa.put(2, 'B'));
		assertNull(mapa.put(12, 'S'));
		assertEquals("{B, H, K, N, S, V, W, X, Z}", ordenarValues(mapa.values()));
		assertFalse(mapa.isEmpty());
		assertNotNull(mapa.put(2, 'E'));
		assertNotNull(mapa.get(9));
		assertNotNull(mapa.put(7, 'A'));
		assertEquals("{(1,X), (2,E), (4,W), (6,Z), (7,A), (8,N), (9,H), (11,K), (12,S)}", ordenar(mapa.entrySet()));
		assertNotNull(mapa.remove(8));
		assertNotNull(mapa.remove(11));
		assertNull(mapa.get(5));
		assertNull(mapa.put(5, 'U'));
		assertNull(mapa.put(3, 'O'));
		assertNull(mapa.put(13, 'C'));
		assertEquals("{A, C, E, H, O, S, U, W, X, Z}", ordenarValues(mapa.values()));
		assertNotNull(mapa.hashValue(13));
		assertNull(mapa.put(17, 'J'));
		assertNull(mapa.put(20, 'P'));
		assertEquals("{(1,X), (2,E), (3,O), (4,W), (5,U), (6,Z), "
				+ "(7,A), (9,H), (12,S), (13,C), (17,J), (20,P)}", ordenar(mapa.entrySet()));
		assertEquals("{1, 2, 3, 4, 5, 6, 7, 9, 12, 13, 17, 20}", ordenarKeys(mapa.keySet()));
	}

	private Object ordenarValues(Iterable<Character> values) {
		String saida = "{";

		HeapPriorityQueue<String, Character> P = new HeapPriorityQueue<String, Character>();

		for (Character ent : values) {
			P.insert(ent.toString(), ent);
		}

		Entry<String, Character> e;
		
		while (!P.isEmpty()) {
			e = P.removeMin();
			saida += e.getKey() + ", ";
		}
		return saida.substring(0, saida.length() - 2) + "}";
	}

	private Object ordenarKeys(Iterable<Integer> keySet) {
		String saida = "{";

		HeapPriorityQueue<Integer, Integer> P = new HeapPriorityQueue<Integer, Integer>();

		for (Integer ent : keySet) {
			P.insert(ent, ent);
		}

		Entry<Integer, Integer> e;
		while (!P.isEmpty()) {
			e = P.removeMin();
			saida += e.getKey() + ", ";
		}
		return saida.substring(0, saida.length() - 2) + "}";
	}

	protected String ordenar(Iterable<Entry<Integer, Character>> iterable) {
		String saida = "{";
		HeapPriorityQueue<Integer, Character> P = new HeapPriorityQueue<Integer, Character>();
		for (Entry<Integer, Character> ent : iterable) {
			P.insert(ent.getKey(), ent.getValue());
		}
		Entry<Integer, Character> e;
		while (!P.isEmpty()) {
			e = P.removeMin();
			saida += "(" + e.getKey() + "," + e.getValue() + "), ";
		}
		return saida.substring(0, saida.length() - 2) + "}";
	}
}
