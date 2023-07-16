import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        System.out.println(persons);

        Stream<Person> minorStream = persons.stream();
        long minorCount = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
//        System.out.println("Количество совершеннолетних: " + minorCount);

        Stream<Person> recruitList = persons.stream();
        List<String> recruitListList = persons.stream()
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getAge() <= 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
//        System.out.println("Список подлежащих призыву: " + recruitListList);

        Stream<Person> topWorkers = persons.stream();

        List<String> topWorkersList = persons.stream()
                .filter(x -> x.getAge() > 17)
                .filter(x -> (x.getSex() == Sex.MEN && x.getAge() < 66) || (x.getSex() == Sex.WOMEN && x.getAge() < 61))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
//        System.out.println("Список подлежащих призыву: " + topWorkersList);
    }
}