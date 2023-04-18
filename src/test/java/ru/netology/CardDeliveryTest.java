package ru.netology;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.*;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
     void shouldSendFormIfItOk() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);
        $("[data-test-id=city] input").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Сычев Андрей");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
     void shouldSendFormIfNameOfCityWithYo() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
     void shouldMessageIfInvalidCityTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Moscow");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='city']//child::span[@class='input__sub']").should(visible, text("Доставка в выбранный город недоступна"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
     void shouldMessageIfInvalidCityTest1() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Зея");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='city']//child::span[@class='input__sub']").should(visible, text("Доставка в выбранный город недоступна"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMessageIfInvalidDateTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(1);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='date']//child::span[@class='input__sub']").should(visible, text("Заказ на выбранную дату невозможен"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
    @Test
    void shouldMessageIfInvalidDateTest1() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(0);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='date']//child::span[@class='input__sub']").should(visible, text("Заказ на выбранную дату невозможен"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
    @Test
    void shouldMessageIfInvalidDateTest2() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(-1);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='date']//child::span[@class='input__sub']").should(visible, text("Заказ на выбранную дату невозможен"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfItHaveDoubleLastname() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    //BUG
    @Test
    void shouldSendFormWithoutName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='name']//child::span[@class='input__sub']").should(visible, text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    //BUG
    @Test
    void shouldSendFormIfNameOrLastnameWithYo() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Семёнов Семён");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfNameInEnglish() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Pupkin Pupok");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='name']//child::span[@class='input__sub']").should(visible, text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfNameWithSymbol() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок)");
        $("[data-test-id=phone] input").setValue("+79819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='name']//child::span[@class='input__sub']").should(visible, text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfPhoneWithoutOneFigure() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок");
        $("[data-test-id=phone] input").setValue("+7981981990");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").should(visible, text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfPhoneWithTwelveFigure() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок");
        $("[data-test-id=phone] input").setValue("+798198199000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").should(visible, text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfPhoneAnotherFormat() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок");
        $("[data-test-id=phone] input").setValue("89819819900");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").should(visible, text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendFormIfPhoneWithSymbol() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);

        $("[data-test-id=city] input").setValue("Орёл");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупкин-Пуп Пупок");
        $("[data-test-id=phone] input").setValue("+7981981990@");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").should(visible, text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMessageIfEmptyCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);
        //$("[data-test-id=city] input").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Сычев Андрей");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='city']//child::span[@class='input__sub']").should(visible, text("Поле обязательно для заполнения"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMessageIfEmptyName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);
        $("[data-test-id=city] input").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        //$("[data-test-id=name] input").setValue("Сычев Андрей");
        $("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='name']//child::span[@class='input__sub']").should(visible, text("Поле обязательно для заполнения"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMessageIfEmptyPhone() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);
        $("[data-test-id=city] input").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Сычев Андрей");
        //$("[data-test-id=phone] input").setValue("+78005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").should(visible, text("Поле обязательно для заполнения"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldMessageIfCheckboxEmpty() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String planningDate = generateDate(3);
        $("[data-test-id=city] input").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Сычев Андрей");
        $("[data-test-id=phone] input").setValue("+78005553535");
        //$("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $x(".//label[@data-test-id='agreement']").should(cssClass("input_invalid"));
        //$x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        //$(".notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
