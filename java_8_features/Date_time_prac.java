package java_8_features;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Date_time_prac {
    public static void main(String[] args) {
    //     LocalDate now = LocalDate.now();
    //     System.out.println(now);

    //     LocalDate myDate = LocalDate.of(2025, 6, 9);

    //     System.out.println(now.getDayOfMonth());
    //     System.out.println(now.getMonthValue());
    //     System.out.println(now.getYear());

    //    System.out.println( now.minusDays(1));

    //    if(now.isLeapYear()){
    //         System.out.println("yes leap");
    //    }
    //    else{
    //     System.out.println("no");
    //    }


       //Local Time

    //    LocalTime noww = LocalTime.now();
    //    System.out.println(noww);

    //    String time = "10:15:45";
    //    System.out.println(LocalTime.parse(time).getHour());

    //Local Date Time

        LocalDateTime curr = LocalDateTime.now();
        System.out.println(curr);
        //T is seperator 

    //Zoned Date time

        ZonedDateTime currr = ZonedDateTime.now();
        System.out.println(currr);

    // Date time formatter
        LocalDate check = LocalDate.now();
        DateTimeFormatter myFor = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String format = myFor.format(check);
        System.out.println(format);
    }
}
