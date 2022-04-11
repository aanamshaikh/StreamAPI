package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise {
    public static void main(String[] args) {
        int[] num ={1,2,3,4,5};
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5,2,4,6);
        List<Integer> number2 = Arrays.asList(1, 2);
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");

        fibonacci(10);
        System.out.println(wordCount(words));
        System.out.println(uniqueChar(words).toString());
        System.out.println(squareNumbers(numbers));
        System.out.println(sumNum(numbers));
        maxAndMinNum(numbers);
        randomRandomValue();

        List<int[]> e = numberPairDivisibleBy3(numbers,number2,3);
        for(int[] w: e){
            System.out.println(Arrays.toString(w));
        }
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        int sum = Arrays.stream(num).sum();
        System.out.println(sum);

    }

    private static void randomRandomValue() {
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    private static void fibonacci(int num) {
        Stream.iterate(new int[] {0,1},t->new int[] {t[0],t[0]+t[1]}).limit(num).map(t->t[0]).forEach(System.out::print);
        Stream.iterate(new int[] {0,1},t->new int[] {t[0],t[0]+t[1]}).limit(num).forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }

    private static void  maxAndMinNum(List<Integer> numbers) {
        System.out.println( numbers.stream().reduce(Integer::max));
        System.out.println( numbers.stream().reduce((x,y)->x>y ? x:y));
        System.out.println( numbers.stream().reduce(Integer::min));
    }

    private static Optional<Integer> sumNum(List<Integer> numbers) {
//       return  numbers.stream().reduce(0,(a,b)->a+b);
//        return  numbers.stream().reduce(0,Integer::sum);
        return  numbers.stream().reduce(Integer::sum);
    }

    private static List<int[]> numberPairDivisibleBy3(List<Integer> numbers, List<Integer> number2, int n) {
        return numbers.stream()
                .flatMap(i->number2.stream()
                        .filter(j->(i+j) % n==0)
                        .map(j->new int[]{i,j}))
                .collect(Collectors.toList());
    }

//    public static List<int[]> numberPairs(List<Integer> numbers, List<Integer> number2) {
//        return   numbers.stream().flatMap(n -> number2.stream().map(n2 -> new int[]{n, n2})).map(n,n2->).collect(Collectors.toList());
//
//    }

    private static List<Integer> squareNumbers(List<Integer> numbers) {
       return  numbers.stream().map(n->n*n).collect(Collectors.toList());
    }

    private static List<String> uniqueChar(List<String> words) {
       return  words.stream().map(w-> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    private static List<Integer> wordCount(List<String> words) {
        return words.stream().map(String::length).collect(Collectors.toList());
    }

//    private static List<Integer> uniqueElements(List<Integer> numbers) {
//         return  numbers.stream().distinct().collect(toList());
//    }

}
