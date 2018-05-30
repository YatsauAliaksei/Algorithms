package com.ttrlalgs.challenge;

public class ListCycle {

    class Node {
        int data;
        Node next;
    }

    boolean hasCycle(Node head) {
        if (head == null) {
            return false;
        }

        Node rabbit = head.next != null ? head.next.next : null;
        Node tortuoze = head.next;

        while (rabbit != null) {
            if (rabbit == tortuoze) {
                return true;
            }
            rabbit = rabbit.next != null ? rabbit.next.next : null;
            tortuoze = tortuoze.next;
        }

        return false;
    }
}
