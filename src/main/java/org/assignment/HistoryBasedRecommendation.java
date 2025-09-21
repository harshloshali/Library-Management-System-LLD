package org.assignment;

import org.assignment.entities.Patron;
import org.assignment.enums.Genre;

import java.util.Map;

public class HistoryBasedRecommendation implements RecommendationStrategy{

    @Override
    public Genre recommend(Patron patron) {
        Map<Genre, Integer> freq= patron.getBorrowingHistory();
        Integer max = freq.get(Genre.LOVE);
        Genre genre = Genre.LOVE;
        if(freq.get(Genre.MYSTERY)>max){
            max= freq.get(Genre.MYSTERY);
            genre = Genre.MYSTERY;
        }if(freq.get(Genre.NONFICTION)>max){

            genre = Genre.NONFICTION;
        }
        return genre;

    }
}
