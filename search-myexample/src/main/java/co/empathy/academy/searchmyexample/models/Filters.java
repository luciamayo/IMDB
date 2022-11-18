package co.empathy.academy.searchmyexample.models;


import lombok.Builder;
import lombok.Value;

@Builder
@Value

public class Filters {

    String[] type;
    String[] genres;
    Integer minYear;
    Integer maxYear;
    Integer maxMinutes;
    Integer minMinutes;

}
