package br.com.ismaelcardosojr.vacancy;

import lombok.Getter;

@Getter
public class Vacancy {
    
    private final String TITLE;
    private final String COMPANY;
    private final String SALARY;
    private final String LOCATION;
    private final String URL;

    public Vacancy(String title, String company, String salary, String location, String url) {
        this.TITLE = title;
        this.COMPANY = company;
        this.SALARY = salary;
        this.LOCATION = location;
        this.URL = url;
    }

    public boolean containsNullAttribute() {
        if (this.TITLE == null || this.COMPANY == null || this.SALARY == null
                || this.LOCATION == null || this.URL == null) {
            return true;
        }

        return false;
    }

}
