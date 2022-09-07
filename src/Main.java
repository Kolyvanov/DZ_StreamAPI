import java.util.*;
import java.util.stream.Collectors;

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
        // считаем число несовершеннолетних

        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних жителей: " + count);

        //получаем список фамилий призывников и для наглядности выводим их количество и фамилию первого призывника

        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Количество призывников: " + conscripts.size());
        System.out.println("Фамилия первого призывника из списка: " + conscripts.get(0));

        //получаем отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        List<Person> workers = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() < 65) || (person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + workers.size());
        System.out.println("Первый человек из списка работоспособных с высшим образованием: " + workers.get(0));


    }
}
