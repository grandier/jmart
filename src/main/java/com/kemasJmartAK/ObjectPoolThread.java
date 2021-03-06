package com.kemasJmartAK;

import java.util.Vector;
import java.util.function.Function;

/**
 * Object Pool Thread is a pool of thread that will be running
 * @param <T> generic of any class could be run as a thread
 * @author Kemas Rafly Omar Thoriq
 */
public class ObjectPoolThread<T> extends Thread {
	private boolean exitSignal;
	private Vector<T> objectPool;
	private Function<T, Boolean> routine;

	public ObjectPoolThread(String name, Function<T, Boolean> routine) {
		super(name);
		this.routine = routine;
	}

	public ObjectPoolThread(Function<T, Boolean> routine) {
		this.routine = routine;
	}

	public synchronized void add(T object) {
		objectPool.add(object);
	}

	public synchronized void exit() {
		exitSignal = true;
	}

	public void run() {
		exitSignal = false;

		for (int i = 0; i < this.size(); i++) {
			T object = objectPool.get(i);
			boolean temp = routine.apply(object);
			if (!temp)
				this.objectPool.add(object);
			while (this.objectPool == null) {
				try {
					routine.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (exitSignal) {
				break;
			}
		}
	}

	public int size() {
		return objectPool.size();

	}
}
