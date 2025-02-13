package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator(){
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[] {"Белгород", "Брянск", "Владимир", "Воронеж", "Иваново", "Калуга", "Кострома",
                "Курск", "Липецк", "Красногорск", "Орёл", "Рязань", "Смоленск", "Тамбов", "Тверь", "Тула", "Ярославль",
                "Москва", "Петрозаводск", "Сыктывкар", "Архангельск", "Нарьян-Мар", "Вологда", "Калининград", "Гатчина",
                "Мурманск", "Великий Новгород", "Псков", "Санкт-Петербург", "Майкоп", "Элиста", "Симферополь", "Краснодар",
                "Астрахань", "Волгоград", "Ростов-на-Дону", "Севастополь", "Махачкала", "Магас", "Нальчик", "Черкесск",
                "Владикавказ", "Грозный", "Ставрополь", "Уфа", "Йошкар-Ола", "Саранск", "Казань", "Ижевск", "Чебоксары",
                "Пермь", "Киров", "Нижний Новгород", "Оренбург", "Пенза", "Самара", "Саратов", "Ульяновск", "Курган",
                "Екатеринбург", "Тюмень", "Ханты-Мансийск", "Салехард", "Челябинск", "Горно-Алтайск", "Кызыл", "Абакан",
                "Барнаул", "Красноярск", "Иркутск", "Кемерово", "Новосибирск", "Омск", "Томск", "Улан-Удэ", "Якутск",
                "Чита", "Петропавловск-Камчатский", "Владивосток", "Хабаровск", "Благовещенск", "Магадан",
                "Южно-Сахалинск", "Биробиджан", "Анадырь"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru_RU"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone() {
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration(){
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(), generatePhone());
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
