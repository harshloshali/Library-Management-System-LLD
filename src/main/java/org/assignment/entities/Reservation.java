package org.assignment.entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;



public class Reservation {
        private final Map<String, Deque<Patron>> waitlist = new HashMap<>();

        public void reserve(String isbn, Patron patron) {
            waitlist.computeIfAbsent(isbn, k -> new ArrayDeque<>()).addLast(patron);
        }

        public boolean hasWaitlist(String isbn){
            Deque<Patron> q = waitlist.get(isbn);
            return q != null && !q.isEmpty();
        }

        public Patron notifyNext(String isbn, Book book, String branchId) {
            Deque<Patron> q = waitlist.get(isbn);
            if (q == null || q.isEmpty()) return null;
            Patron p = q.pollFirst();
            if (p != null) {
                p.notifyBookAvailable(branchId, book);
            }
            return p;
        }


}
