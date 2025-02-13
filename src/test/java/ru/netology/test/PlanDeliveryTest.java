package ru.netology.test;

import com.codeborne.selenide.CollectionCondition;
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
    public void shouldSuccessfulPlanMeeting(){ //visible
        $("[data-test-id='city'].input [placeholder='Город']").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input [placeholder='Дата встречи']").press(Keys.chord(Keys.SHIFT,
                Keys.HOME), Keys.DELETE).setValue(DataGenerator.generateDate(5));
        $("[data-test-id='name'].input [name='name']").setValue(DataGenerator.generateName());
        $("[data-test-id='phone'].input [name='phone']").setValue(DataGenerator.generatePhone("#"));
        $("[data-test-id='agreement'].checkbox").click();
        $$("button.button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(Condition.text("Успешно!")).shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на "
                        + DataGenerator.generateDate(5))).shouldBe(Condition.visible);
    }

    @Test
    public void shouldSuccessfulRePlanMeeting(){
        $("[data-test-id='city'].input [placeholder='Город']").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] .input [placeholder='Дата встречи']").press(Keys.chord(Keys.SHIFT,
                Keys.HOME), Keys.DELETE).setValue(DataGenerator.generateDate(5));
        $("[data-test-id='name'].input [name='name']").setValue(DataGenerator.generateName());
        $("[data-test-id='phone'].input [name='phone']").setValue(DataGenerator.generatePhone("#"));
        $("[data-test-id='agreement'].checkbox").click();
        $$("button.button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(Condition.text("Успешно!")).shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на "
                        + DataGenerator.generateDate(5))).shouldBe(Condition.visible);
        //rePlan date
        $("[data-test-id='date'] .input [placeholder='Дата встречи']").press(Keys.chord(Keys.SHIFT,
                Keys.HOME), Keys.DELETE).setValue(DataGenerator.generateDate(4));
        $$("button.button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__title")
                .shouldHave(Condition.text("Необходимо подтверждение")).shouldBe(Condition.visible);
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. " +
                        "Перепланировать?")).shouldBe(Condition.visible);
        $$("button.button").find(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(Condition.text("Успешно!")).shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на "
                        + DataGenerator.generateDate(4))).shouldBe(Condition.visible);
    }
}
