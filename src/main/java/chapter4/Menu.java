package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Menu {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        System.out.println( lowCaloricDishes(menu,400));
        System.out.println(dishesByTpe(menu));
        System.out.println(threeHighCaloricDishes(menu,400));
        getMenu(menu);
        System.out.println(vegeterianDishes(menu));
        System.out.println(getTotalDishes(menu));
        System.out.println(getHighCaloricDishName(menu));
        System.out.println(getFirstTwoMeatDishes(menu));
        System.out.println(dishNameLength(menu));
        isVegeterian(menu);
        isHealthy(menu);

    }
    private  static void  isHealthy(List<Dish> menu) {
        if(menu.stream().noneMatch(dish ->dish.getCalories()>1000)){
            System.out.println("yes!");
        }
        if(menu.stream().allMatch(dish -> dish.getCalories()<500)){
            System.out.println("yes!");
        }else{
            System.out.println("no");
        }
    }
    private  static void  isVegeterian(List<Dish> menu) {
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("yes!");
        }
        Optional<Dish> first = menu.stream().filter(dish -> dish.isVegetarian()).findFirst();
        Optional<Dish> first1 = menu.stream().filter(dish -> dish.isVegetarian()).findAny();
        System.out.println(first);
        System.out.println(first1);
        countDishes(menu);
    }

    private static Integer countDishes(List<Dish> menu) {
        long count1 =  menu.stream().count();
        System.out.println(count1);
        return  menu.stream().map(d->1).reduce(0, Integer::sum);
    }

    private static List<Integer> dishNameLength(List<Dish> menu) {
        return menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
    }

    private static List<Dish> getFirstTwoMeatDishes(List<Dish> menu) {
        return menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());
    }

    private static List<Dish> getHighCaloricDishName(List<Dish> menu) {
        return  menu.stream().filter(dish -> dish.getCalories() >400).skip(3).collect(Collectors.toList());
    }

    private static List<Dish> vegeterianDishes(List<Dish> menu) {
        return menu.stream().filter(dish -> dish.isVegetarian()).collect(Collectors.toList());
    }

    private static long getTotalDishes(List<Dish> menu) {
        return menu.stream().distinct().count();
    }

    private static void getMenu(List<Dish> menu) {
        menu.stream().forEach(System.out::println);
    }

    private static List<String> threeHighCaloricDishes(List<Dish> menu , int calorie) {
        return menu.stream().filter(dish -> dish.getCalories()> 400 )
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
    }

    private static Map<Dish.Type,List<Dish>> dishesByTpe(List<Dish> menu) {
        return  menu.stream().collect(groupingBy(Dish::getType));

    }

    private static List<String> lowCaloricDishes(List<Dish> menu, int calories) {
        return  menu.stream()
                .filter(dish-> dish.getCalories() <400)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
