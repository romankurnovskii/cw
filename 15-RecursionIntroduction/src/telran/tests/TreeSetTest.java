package telran.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Set;
import telran.util.TreeSet;

import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTest {

	private static final Predicate<Integer> CHECK_ODD = (a) -> (a % 2 != 0);

	private TreeSet<Integer> tree1, tree2;

	@BeforeEach
	void setUp() {
		tree1 = new TreeSet<>();
		tree1.add(10);
		tree1.add(5);
		tree1.add(1);
		tree1.add(3);
		tree1.add(15);
		tree1.add(12);
		tree1.add(20);

		tree2 = new TreeSet<>();
		tree2.add(10);
		tree2.add(1);
		tree2.add(3);
		tree2.add(9);
		tree2.add(2);
		tree2.add(4);
	}

	@Test
	void testAddExistentObject() {
		TreeSet<Integer> tree = new TreeSet<>();

		assertTrue(tree.add(10));
		assertEquals(1, tree.size());

		assertFalse(tree.add(10));
		assertEquals(1, tree.size());

		assertTrue(tree.add(20));
		assertEquals(2, tree.size());

		assertTrue(tree.add(30));
		assertEquals(3, tree.size());

		assertFalse(tree.add(20));
		assertEquals(3, tree.size());
	}

	@Test
	void testIteratorRemove() {
		Iterator<Integer> it;

		assertEquals(7, tree1.size());
		it = tree1.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		assertEquals(0, tree1.size());

		assertEquals(6, tree2.size());
		it = tree2.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		assertEquals(0, tree2.size());
	}

	@Test
	void testFilter() {
		Set<Integer> filtered;

		filtered = tree1.filter(CHECK_ODD);
		assertEquals(4, filtered.size());
		assertTrue(filtered.contains(1));
		assertTrue(filtered.contains(3));
		assertTrue(filtered.contains(5));
		assertTrue(filtered.contains(15));

		filtered = tree2.filter(CHECK_ODD);
		assertEquals(3, filtered.size());
		assertTrue(filtered.contains(1));
		assertTrue(filtered.contains(3));
		assertTrue(filtered.contains(9));
	}

	@Test
	void testRemove() {
		assertEquals(1, tree1.remove(1));
		assertEquals(6, tree1.size());
		assertTrue(tree1.contains(10));
		assertTrue(tree1.contains(5));
		assertTrue(tree1.contains(3));
		assertTrue(tree1.contains(15));
		assertTrue(tree1.contains(12));
		assertTrue(tree1.contains(20));

		assertEquals(3, tree2.remove(3));
		assertEquals(5, tree2.size());
		assertTrue(tree2.contains(10));
		assertTrue(tree2.contains(1));
		assertTrue(tree2.contains(9));
		assertTrue(tree2.contains(2));
		assertTrue(tree2.contains(4));
	}

	@Test
	void testRemoveIfOdd() {
		tree1.removeIf(CHECK_ODD);
		assertEquals(3, tree1.size());
		assertTrue(tree1.contains(10));
		assertTrue(tree1.contains(12));
		assertTrue(tree1.contains(20));

		tree2.removeIf(CHECK_ODD);
		assertEquals(3, tree2.size());
		assertTrue(tree2.contains(10));
		assertTrue(tree2.contains(2));
		assertTrue(tree2.contains(4));
	}

	@Test
	void testRemoveIfAll() {
		tree1.removeIf(x -> true);
		assertEquals(0, tree1.size());
		assertFalse(tree1.contains(10));

		tree2.removeIf(x -> true);
		assertEquals(0, tree2.size());
		assertFalse(tree2.contains(10));
	}

	@Test
	void testRemoveWithHugeRandomSet() {
		Set<Integer> set = new TreeSet<>();

		for (int i = 0; i < 10000; i++) {
			set.add((int) (Math.random() * Integer.MAX_VALUE));
		}

		assertTrue(set.removeIf(new EvenNumbersPredicate())); // num % 2 == 0

		for (int num : set) {
			assertTrue(num % 2 == 1);
		}
	}

	@Test
	void testRemoveCustomCase() {
		Set<Integer> set = new TreeSet<>();
		set.add(10);
		set.add(5);
		set.add(15);
		set.add(12);
		set.add(20);
		set.add(16);

		assertTrue(set.removeIf(x -> (x == 16 || x == 15)));

		assertEquals(4, set.size());
	}
}
