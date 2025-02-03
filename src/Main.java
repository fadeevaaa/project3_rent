import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static List<Premis> premises = new ArrayList<>();

    public static void main(String[] args) {
        menu(new Scanner(System.in));

    }

    public static void menu(Scanner sc) {
        System.out.println("""
                
                Выберите пункт меню (введите цифру):
                1 - Добавить квартиру в список для аренды
                2 - Удалить квартиру из списка для аренды
                3 - Отсортировать квартиры по цене (по возрастанию) и вывести
                4 - Отсортировать квартиры по цене (по убыванию) и вывести
                5 - Сгруппировать квартиры по станции метро и вывести
                6 - Выбрать лучшую квартиру по оценке и вывести
                7 - Вывести изначальный список квартир для аренды
                
                8 - Добавить коммерческое помещение в список для аренды
                9 - Удалить коммерческое помещение из списка для аренды
                10 - Отсортировать коммерческие помещения по цене (по возрастанию) и вывести
                11 - Отсортировать коммерческие помещения по цене (по убыванию) и вывести
                12 - Сгруппировать коммерческие помещения по станции метро и вывести
                13 - Выбрать лучшее коммерческое помещение по оценке и вывести
                14 - Вывести изначальный список коммерческоих помещений для аренды
                
                15 - Удалить всё из всех списков
                16 - Вывести сгруппированные списки
                
                """);
        int point = sc.nextInt();
        sc.nextLine();
        switch (point) {
            case 1 -> addPremise(sc, 1);
            case 8 -> addPremise(sc, 2);
            case 2 -> removePremise(sc, 1);
            case 9 -> removePremise(sc, 2);
            case 3 -> sortPremisesASC(sc, 1);
            case 10 -> sortPremisesASC(sc, 2);
            case 4-> sortPremisesDESC(sc, 1);
            case 11 -> sortPremisesDESC(sc, 2);
            case 5 -> groupByMetroStation(sc, 1);
            case 12 -> groupByMetroStation(sc, 2);
            case 6 -> chooseTheBestPremis(sc, 1);
            case 13 -> chooseTheBestPremis(sc, 2);
            case 7 -> printListOfPremises(sc, 1);
            case 14 -> printListOfPremises(sc, 2);
            case 15 -> clear(sc);
            case 16 -> groupByPremis(sc);
            default -> {
                System.out.println("\nВыбранный пункт отсутствует, попробуйте снова\n");
                menu(sc);
            }
        }
    }

    public static void addPremise(Scanner sc, int type) {

        System.out.println("Введите название помещения: \n");
        String name = fillField(sc);

        System.out.println("Введите станцию метро: \n");
        String metroStation = fillField(sc);

        System.out.println("Введите адрес: \n");
        String address = fillField(sc);

        System.out.println("Введите метраж помещения: \n");
        int square = Integer.parseInt(fillField(sc));

        System.out.println("Введите цену: \n");
        int price = Integer.parseInt(fillField(sc));

        System.out.println("Введите среднюю оценку: \n");
        float rating = Float.valueOf(fillField(sc));

        switch (type) {
            case 1 -> {
                System.out.println("Введите количество комнат: \n");
                int roomCount = Integer.parseInt(fillField(sc));
                premises.add(new Flat(name, metroStation, address, square, price, rating, roomCount));
            }
            case 2 -> {
                System.out.println("Введите тип помещения \n");
                String roomType = fillField(sc);
                premises.add(
                        new CommercialPremises(name, metroStation, address, square, price, rating, roomType));
            }
        }
        menu(sc);
    }

    public static void removePremise(Scanner sc, int type) {
        System.out.println("Введите название помещения: \n");
        String name = fillField(sc);

        System.out.println("Введите адрес: \n");
        String address = fillField(sc);

        System.out.println("Введите цену: \n");
        int price = Integer.parseInt(fillField(sc));

        switch (type) {
            case 1 -> {
                for (Premis premis : premises) {
                    if (premis instanceof Flat && premis.getName().equals(name) &&
                            premis.getAddress().equals(address) && premis.getPrice() == price)
                        premises.remove(premis);
                }
            }
            case 2 -> {
                for (Premis premis : premises) {
                    if (premis instanceof CommercialPremises && premis.getName().equals(name) &&
                            premis.getAddress().equals(address) && premis.getPrice() == price)
                        premises.remove(premis);
                }
            }
        }
        menu(sc);
    }

    public static void sortPremisesASC(Scanner sc, int type) {
        switch (type) {
            case 1 -> premises.stream().filter(e -> e instanceof Flat).
                    sorted(Comparator.comparing(Premis::getPrice)).forEach(System.out::println);
            case 2 -> premises.stream().filter(e -> e instanceof CommercialPremises).
                    sorted(Comparator.comparing(Premis::getPrice)).forEach(System.out::println);
        }
        menu(sc);
    }

    public static void sortPremisesDESC(Scanner sc, int type) {
        switch (type) {
            case 1 -> premises.stream().filter(e -> e instanceof Flat).
                    sorted(Comparator.comparing(Premis::getPrice).reversed()).
                    forEach(System.out::println);
            case 2 -> premises.stream().filter(e -> e instanceof CommercialPremises).
                    sorted(Comparator.comparing(Premis::getPrice).reversed()).
                    forEach(System.out::println);
        }
        menu(sc);
    }

    public static void groupByMetroStation(Scanner sc, int type) {
        switch (type) {
            case 1 -> premises.stream().filter(e -> e instanceof Flat).
                    collect(Collectors.groupingBy(Premis::getMetroStation)).
                    forEach((metroStation, flat) -> {
                        System.out.println(metroStation);
                        flat.forEach(System.out::println);
                    });
            case 2 -> premises.stream().filter(e -> e instanceof CommercialPremises).
                    collect(Collectors.groupingBy(Premis::getMetroStation)).
                    forEach((metroStation, commercialPremis) -> {
                        System.out.println(metroStation);
                        commercialPremis.forEach(System.out::println);
                    });
        }
        menu(sc);
    }

    public static void chooseTheBestPremis(Scanner sc, int type) {
        switch (type) {
            case 1 -> premises.stream().filter(e -> e instanceof Flat).
                    max(Comparator.comparing(Premis::getRating)).ifPresent(System.out::println);

            case 2 -> premises.stream().filter(e -> e instanceof CommercialPremises).
                    max(Comparator.comparing(Premis::getRating)).ifPresent(System.out::println);
        }
        menu(sc);
    }

    public static void printListOfPremises(Scanner sc, int type) {
        switch (type) {
            case 1 -> premises.stream().filter(e -> e instanceof Flat).
                    forEach(System.out::println);
            case 2 -> premises.stream().filter(e -> e instanceof CommercialPremises).
                    forEach(System.out::println);
        }
        menu(sc);
    }

    public static String fillField(Scanner sc) {
        String field = sc.nextLine();
        while (field.isEmpty()) {
            System.out.println("\nПоле не может быть пустым! Введите снова: \n");
            field = sc.nextLine().trim();
        }
        return field;
    }


    public static void clear(Scanner sc) {
        premises.clear();
        menu(sc);
    }

    public static void groupByPremis(Scanner sc) {
        Map<String, List<Premis>> groupByPremis = premises.stream().
                collect(Collectors.groupingBy(e -> e instanceof Flat ? "Квартиры" : "Коммерческие помещения"));
        groupByPremis.forEach((type, premises) -> {
            System.out.println(type);
            premises.forEach(System.out::println);
        } );
        menu(sc);
    }

    public static void groupBy(Scanner sc) {

    }
}
