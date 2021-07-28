package ru.netology.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[placeholder=Город]").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = newDate.format(formatter);
        $("[data-test-id=date] input").setValue(dateText);
        $(("[data-test-id=name] input")).setValue(DataGenerator.generateName("ru"));
        $(("[data-test-id=phone] input")).setValue(DataGenerator.generatePhone("ru"));
        $("span.checkbox__box").click();
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);

        LocalDate newDate2 = newDate.plusDays(4);
        String actualDate = newDate2.format(formatter);

        $("[data-test-id=date] input").setValue(actualDate);
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована на"))
                .shouldBe(visible, Duration.ofSeconds(4))
                .shouldHave(exactText("Встреча успешно запланирована на  " + actualDate));
    }
}