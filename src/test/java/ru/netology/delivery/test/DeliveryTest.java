package ru.netology.delivery.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        $("[placeholder=Город]").setValue(DataGenerator.generateCity("ru"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $(("[data-test-id=name] input")).setValue(DataGenerator.generateName("ru"));
        $(("[data-test-id=phone] input")).setValue(DataGenerator.generatePhone("ru"));
        $("span.checkbox__box").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на"))
                .shouldBe(visible, Duration.ofSeconds(4))
                .shouldHave(exactText("Встреча успешно запланирована на  " + DataGenerator.generateDate(3)));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(7));
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(9));
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована на"))
                .shouldBe(visible, Duration.ofSeconds(4))
                .shouldHave(exactText("Встреча успешно запланирована на  " + DataGenerator.generateDate(9)));
    }
}