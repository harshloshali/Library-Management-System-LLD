package org.assignment.entities;

public interface PatronObserver {

        void notifyBookAvailable(String branchId, Book book);

}
