package datastructure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

public class DequeClass {
    Deque linkedQueue = new LinkedList();
    Queue concurrentLinkedDeque = new ConcurrentLinkedDeque();
    PriorityQueue priorityQueue = new PriorityQueue();
    BlockingQueue linkedBlockingDeque = new LinkedBlockingDeque();
    BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(0);
    BlockingQueue delayQueue = new DelayQueue();
    BlockingQueue synchronousQueue = new SynchronousQueue();
}
