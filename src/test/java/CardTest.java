import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @Test
    void shouldTestSuccess() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Артур Салахутдинов");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestNameEnglishLetters() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Arthur");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNameNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Артур3");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestPhoneSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Артур");
        $("[data-test-id=phone] input").setValue("++7929023456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // Новый тест на пустое поле имени
    @Test
    void shouldTestEmptyNameField() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // Новый тест на пустое поле телефона
    @Test
    void shouldTestEmptyPhoneField() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Артур Салахутдинов");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // Новый тест на неотмеченный чекбокс
    @Test
    void shouldTestUncheckedCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Артур Салахутдинов");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $(".button__content").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Необходимо согласие"));
    }
}
