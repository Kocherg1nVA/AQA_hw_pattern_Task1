package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PlanDeliveryTest {

    @BeforeEach
    void setUp(){
        Selenide.open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulPlanMeeting(){
        var firstMeetingDay = DataGenerator.generateDate(5);
        var secondMeetingDay = DataGenerator.generateDate(4);
        var getCity = DataGenerator.generateCity();
        var getName = DataGenerator.generateName();
        var getPhone = DataGenerator.generatePhone();

        $("[data-test-id='city'] input").setValue(getCity);
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT,
                Keys.HOME), Keys.DELETE).setValue(firstMeetingDay);
        $("[data-test-id='name'] input").setValue(getName);
        $("[data-test-id='phone'] input").setValue(getPhone);
        $("[data-test-id='agreement'].checkbox").click();
        $$("button.button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(Condition.text("Успешно!"))
                .shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на "
                        + firstMeetingDay))
                .shouldBe(Condition.visible);
        //replan meeting day
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT,
                Keys.HOME), Keys.DELETE).setValue(secondMeetingDay);
        $$("button.button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__title")
                .shouldHave(Condition.text("Необходимо подтверждение"))
                .shouldBe(Condition.visible);
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. " +
                        "Перепланировать?"))
                .shouldBe(Condition.visible);
        $$("button.button").find(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(Condition.text("Успешно!"))
                .shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDay))
                .shouldBe(Condition.visible);
    }
}
