package kemasJmartAK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
//import java.util.*;
import java.util.List;

public class Algorithm {

	private <T> Algorithm() {
		// do nothing
	}

	public static <T> Iterator<T> toIterator(T[] array) {
		return Arrays.asList(array).iterator();
	}

	public static <T> Iterator<T> toIterator(Iterable<T> iterable) {
		return iterable.iterator();
	}

	// collect
	public static <T> List<T> collect(T[] array, T value) {
		List<T> list = new ArrayList<T>();
		for (T arr : array) {
			if (arr.equals(value)) {
				list.add(arr);
			}
		}

		return list;

	}

	public static <T> List<T> collect(Iterable<T> iterable, T value) {
		List<T> list = new ArrayList<T>();
		for (T arr : iterable) {
			if (arr.equals(value)) {
				list.add(arr);
			}
		}

		return list;

	}

	public static <T> List<T> collect(Iterator<T> iterator, T value) {
		List<T> list = new ArrayList<T>();
		while (iterator.hasNext()) {
			T arr = iterator.next();
			if (arr.equals(value)) {
				list.add(arr);
			}
		}

		return list;

	}

	public static <T> List<T> collect(T[] array, Predicate<T> pred) {
		List<T> list = new ArrayList<T>();
		for (T arr : array) {
			if (pred.predicate(arr)) {
				list.add(arr);
			}
		}

		return list;

	}

	public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
		List<T> list = new ArrayList<T>();
		for (T arr : iterable) {
			if (pred.predicate(arr)) {
				list.add(arr);
			}
		}

		return list;

	}

	public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
		List<T> list = new ArrayList<T>();
		while (iterator.hasNext()) {
			T arr = iterator.next();
			if (pred.predicate(arr)) {
				list.add(arr);
			}
		}

		return list;

	}

	// count
	public static <T> int count(T[] array, T value) {
		return count(toIterator(array), value);
	}

	public static <T> int count(Iterable<T> iterable, T value) {
		return count(toIterator(iterable), value);
	}

	public static <T> int count(Iterator<T> iterator, T value) {
		int result = 0;
		while (iterator.hasNext()) {
			if (value.equals(iterator.next())) {
				result++;
			}
		}
		return result;
	}

	public static <T> int count(T[] array, Predicate<T> pred) {
		Iterator<T> iterator = toIterator(array);
		return count(iterator, pred);
	}

	public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
		return count(toIterator(iterable), pred);
	}

	public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
		return count(iterator, find(iterator, pred));
	}

	// exists
	public static <T> boolean exists(T[] array, T value) {
		return exists(toIterator(array), value);
	}

	public static <T> boolean exists(Iterable<T> iterable, T value) {
		return exists(toIterator(iterable), value);
	}

	public static <T> boolean exists(Iterator<T> iterator, T value) {
		return find(iterator, value) != null;
	}

	public static <T> boolean exists(T[] array, Predicate<T> pred) {
		Iterator<T> iterator = toIterator(array);
		return exists(iterator, pred);
	}

	public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
		return exists(toIterator(iterable), pred);
	}

	public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
		return exists(iterator, find(iterator, pred));
	}

	// find
	public static <T> T find(T[] array, T value) {
		return find(toIterator(array), value);
	}

	public static <T> T find(Iterable<T> iterable, T value) {
		return find(toIterator(iterable), value);
	}

	public static <T> T find(Iterator<T> iterator, T value) {
		while (iterator.hasNext()) {
			if (value.equals(iterator.next())) {
				return value;
			}
		}
		return null;
	}

	public static <T> T find(T[] array, Predicate<T> pred) {
		Iterator<T> iterator = toIterator(array);
		return find(iterator, pred);
	}

	public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
		return find(toIterator(iterable), pred);
	}

	public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
		while (iterator.hasNext()) {
			if (pred.predicate(iterator.next())) {
				return iterator.next();
			}
		}
		return null;
	}

	// max
	public static <T> T max(T first, T second) {
		if (first.hashCode() > second.hashCode()) {
			return first;
		} else {
			return second;
		}
	}

	public static <T extends Comparable<? super T>> T max(T[] array) {
		T maximum = null;

		for (T arr : array) {
			if (arr.compareTo(maximum) >= 0) {
				maximum = arr;
			}
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
		final Iterator<T> arr = iterable.iterator();
		T maximum = null;
		while (arr.hasNext()) {
			if (arr.next().compareTo(maximum) >= 0) {
				maximum = arr.next();
			}
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
		final Iterator<T> arr = iterable.iterator();
		T maximum = null;
		while (arr.hasNext()) {
			if (comparator.compare(arr.next(), maximum) >= 0) {
				maximum = arr.next();
			}
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator) {
		T maximum;
		if (comparator.compare(first, second) >= 0) {
			maximum = first;
		} else {
			maximum = second;
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator) {
		T maximum = null;

		for (T arr : array) {
			if (comparator.compare(arr, maximum) >= 0) {
				maximum = arr;
			}
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
		T maximum = null;
		while (iterator.hasNext()) {
			if (comparator.compare(iterator.next(), maximum) >= 0) {
				maximum = iterator.next();
			}
		}

		return maximum;

	}

	public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
		T maximum = null;
		while (iterator.hasNext()) {
			if (iterator.next().compareTo(maximum) >= 0) {
				maximum = iterator.next();
			}
		}

		return maximum;

	}

	// min
	public static <T extends Comparable<T>> T min(T first, T second) {
		return max(second, first);
	}

	public static <T extends Comparable<? super T>> T min(T[] array) {
		T minimum = null;

		for (T arr : array) {
			if (arr.compareTo(minimum) <= 0) {
				minimum = arr;
			}
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
		final Iterator<T> arr = iterable.iterator();
		T minimum = null;
		while (arr.hasNext()) {
			if (arr.next().compareTo(minimum) <= 0) {
				minimum = arr.next();
			}
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
		final Iterator<T> arr = iterable.iterator();
		T minimum = null;
		while (arr.hasNext()) {
			if (comparator.compare(arr.next(), minimum) <= 0) {
				minimum = arr.next();
			}
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator) {
		T minimum;
		if (comparator.compare(first, second) >= 0) {
			minimum = first;
		} else {
			minimum = second;
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator) {
		T minimum = null;

		for (T arr : array) {
			if (comparator.compare(arr, minimum) <= 0) {
				minimum = arr;
			}
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
		T minimum = null;
		while (iterator.hasNext()) {
			if (comparator.compare(iterator.next(), minimum) >= 0) {
				minimum = iterator.next();
			}
		}

		return minimum;

	}

	public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
		T minimum = null;
		while (iterator.hasNext()) {
			if (iterator.next().compareTo(minimum) >= 0) {
				minimum = iterator.next();
			}
		}

		return minimum;

	}
}
