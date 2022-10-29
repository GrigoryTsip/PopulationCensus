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
                    Gender.values()[new Random().nextInt(Gender.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long teenagers = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.printf("Количество несовершеннолетних в выбранной поппуляции: " + "%,d", teenagers);

        List<String> recruits = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        long num = recruits.size();
        StringBuilder sr = new StringBuilder("\nСписок призывников в выбранной поппуляции: ")
                .append(recruits.toString())
                        .append("\nКоличество призывников: ");

        System.out.printf(sr + "%,d", num);

        List<String> workers = persons.stream()
                .filter(x -> (x.getGender() == Gender.MAN && x.getAge() > 18 && x.getAge() < 65) ||
                             (x.getGender() == Gender.WOMAN && x.getAge() > 18 && x.getAge() < 60))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        num = workers.size();
        StringBuilder sw = new StringBuilder("\nСписок работоспособных в выбранной поппуляции: ")
                .append(workers.toString())
                        .append("\nKоличество работников: ");

        System.out.printf(sw + "%,d", num);
    }
}
