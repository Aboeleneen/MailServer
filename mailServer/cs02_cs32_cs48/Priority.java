/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.datastructure.mailServer.cs32;

import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;
/**
 *
 * @author Geek
 */
public class Priority implements IPriorityQueue {
    /**
* head of the list.
*/
    public DoupleNode head = null;
     /**
* tail of the list.
*/
    public DoupleNode tail = null;
     /**
* size of the list.
*/
    int size;
    @Override
    public void insert(final Object item, final int key) {
if (key < 1) {
    throw new RuntimeException("empty queue");
}
        if (size == 0) {
head = new DoupleNode(item, key);
tail = head;
size++;
 } else {
 DoupleNode c = tail;
 int index = size;
 if (key < head.key) {
DoupleNode x = new DoupleNode(item, key);
head.previous = x;
x.next = head;
head = x;
size++;
 } else {
  while (key < c.key && index > 0) {
  index--;
  c = c.previous;
  }
DoupleNode x = new DoupleNode(item, key);
x.next = c.next;
x.previous = c.previous;
c.next = x;
if (c == head) {
    x.previous = head;
}
if (index != size) {
    x.next.previous = c;
}
size++;
  if (c == tail) {
      tail = tail.next;
  }
 }
 }
 }
    @Override
    public Object removeMin() {
        if (size == 0) {
            throw new RuntimeException("empty queue");
        }
        DoupleNode i = head;
        Object c = head.value;
        head = i.next;
        size--;
        return c;
    }
    @Override
    public Object min() {
 if (size == 0) {
            throw new RuntimeException("empty queue");
        }
    return head.value;
    }
    @Override
    public boolean isEmpty() {
return size == 0;
    }
    @Override
    public int size() {
return size;    }
}
