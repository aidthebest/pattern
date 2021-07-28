package ru.netology.delivery;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        String date = LocalDate.now().toString();
        return date;
    }

    public static String generateCity(String locale) {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        Faker faker = new Faker(new Locale("ru"));
        String city = faker.address().cityName();
        return city;
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().toString();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

//
//        public static String[] GenerationUser generateUser(String locale) {
//            Faker faker = new Faker(new Locale("ru"));
//            return new GenerationUser(
//                    faker.name().fullName(),
//                    faker.phoneNumber(),
//                    faker.address().cityName()
//            );


        public static String[] generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            String user[] = {generateCity("ru"), generateName("ru"), generatePhone("ru")};
            return user;
        }
}
    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
