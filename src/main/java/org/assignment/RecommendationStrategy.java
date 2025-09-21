package org.assignment;

import org.assignment.entities.Patron;
import org.assignment.enums.Genre;

public interface RecommendationStrategy {
    Genre recommend(Patron patron);
}
