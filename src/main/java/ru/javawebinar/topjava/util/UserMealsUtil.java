package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        int dayOfMonth = 0;
        int sumCalories = 0;
        Map<LocalDate, Boolean> limitCalories = new HashMap<>(); //Key day, Value exceed;
        List<UserMealWithExceed> listFilter = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if(dayOfMonth == 0) dayOfMonth = userMeal.getDateTime().getDayOfMonth();
            if(dayOfMonth == userMeal.getDateTime().getDayOfMonth()) {
                sumCalories += userMeal.getCalories();
            } else {
                sumCalories = userMeal.getCalories();
                dayOfMonth = userMeal.getDateTime().getDayOfMonth();
            }
            if(sumCalories > caloriesPerDay) {
                limitCalories.put(userMeal.getDateTime().toLocalDate(), true);
            }
            else {
                limitCalories.put(userMeal.getDateTime().toLocalDate(), false);
            }
        }
        for (UserMeal userMeal : mealList) {
            if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)){
                LocalDateTime localDateTime = userMeal.getDateTime();
                String description = userMeal.getDescription();
                int calories = userMeal.getCalories();
                boolean exceed = limitCalories.get(localDateTime.toLocalDate());
                listFilter.add(new UserMealWithExceed(localDateTime, description, calories, exceed));
            }
        }
        return listFilter;
    }
}
