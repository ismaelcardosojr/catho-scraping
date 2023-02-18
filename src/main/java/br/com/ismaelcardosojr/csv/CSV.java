package br.com.ismaelcardosojr.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.ismaelcardosojr.vacancy.Vacancy;

public class CSV {
    
    private final FileWriter WRITER;

    public CSV(String directory) throws IOException {
        this.WRITER = new FileWriter(directory + "/Vacancies.csv", true);
    }

    public void writeFile(List<Vacancy> allVacancies) throws IOException {
        this.WRITER.write("Title, Company, Salary, Location, URL \n");

        for (Vacancy currentVacancy : allVacancies) {
            this.WRITER.write(this.format(currentVacancy.getTITLE()) + ", ");
            this.WRITER.write(this.format(currentVacancy.getCOMPANY()) + ", ");
            this.WRITER.write(this.formatSalary(currentVacancy.getSALARY()) + ", ");
            this.WRITER.write(this.formatLocation(currentVacancy.getLOCATION()) + ", ");
            this.WRITER.write(this.formatURL(currentVacancy.getURL()) + "\n");
        }
    }

    private String format(String attribute) {
        return attribute.replace(",", "");
    }

    private String formatSalary(String salary) {
        return salary.replace(",", ".");
    }

    private String formatLocation(String location) {
        int unwantedInfoIndex = location.indexOf("(");
        return location.substring(0, unwantedInfoIndex).trim();
    }

    private String formatURL(String url) {
        if (!url.contains("?origem")) {
            return url;
        }

        int unwantedInfoIndex = url.indexOf("?origem");
        return url.substring(0, unwantedInfoIndex);
    }

}