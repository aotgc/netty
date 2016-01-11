
package io.netty.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicIntRotator {
	private final AtomicInteger value;
	private final int slotCount;
	
	public AtomicIntRotator(int slotCount){
		this.slotCount=slotCount;
		this.value=new AtomicInteger(0);
	}

	public final int getSlotCount(){
		return slotCount;
	}
	
	
	public final int get() {
		return value.get();
	}

	public int getAndRotate() {

		for (;;) {
			int current = value.get();
			int next = current + 1;
			if(next==slotCount)
				next=0;
			if (value.compareAndSet(current, next))
				return current;
		}
	}


}
