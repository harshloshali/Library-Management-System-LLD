package org.assignment;

import org.assignment.entities.Branch;
import org.assignment.entities.Patron;


public class LibraryFactory {

        public static Patron createPatron(String id, String name){
            return new Patron(id, name);
        }
        public static Branch createBranch(String id, String name){
            return new Branch(id, name);
        }

}
