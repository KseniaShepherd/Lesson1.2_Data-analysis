import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 16, Sex.MAN, Education.SECONDARY),
                new Person("Connor", "Young", 23, Sex.MAN, Education.FURTHER),
                new Person("Emily", "Harris", 42, Sex.WOMEN, Education.HIGHER),
                new Person("Harry", "Wilson", 69, Sex.MAN, Education.HIGHER),
                new Person("George", "Davies", 35, Sex.MAN, Education.FURTHER),
                new Person("Samuel", "Adamson", 40, Sex.MAN, Education.HIGHER),
                new Person("John", "Brown", 44, Sex.MAN, Education.HIGHER),
                new Person("John", "Winson", 26, Sex.MAN, Education.HIGHER),
                new Person("Jack", "Harris", 17, Sex.MAN, Education.SECONDARY),
                new Person("Mary", "Bob", 61, Sex.WOMEN, Education.HIGHER)
        );

        long countUnderage = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних: " + countUnderage);
        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + conscripts);

        List<Person> workers = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getSex().equals(Sex.WOMEN) && person.getAge() > 18 && person.getAge() < 60
                        || person.getSex().equals(Sex.MAN) && person.getAge() > 18 && person.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке:");

        for (Person person : workers) {
            System.out.println(person.getFamily());
        }
    }
}

