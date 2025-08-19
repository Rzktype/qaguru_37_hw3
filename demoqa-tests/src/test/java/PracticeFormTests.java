import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        $("#firstName").setValue("Artur");
        $("#lastName").setValue("Legenda");
        $("#userEmail").setValue("alex@egorov.com");
        $("#genterWrapper").$(".custom-control-label").click();
        $("#userNumber").setValue("7999333444");
        $(".react-datepicker-wrapper").click();
        $(byText("April")).click();
        $(byText("1941")).click();
        $(byText("20")).click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#currentAddress").setValue("Some street 1");
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("testImage.jpg");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        $(".modal-content").shouldHave(text("Thanks for submitting the form")); // проверим что открылась модалка
        // проверим что в модалке данные из теста
        $(".table-responsive").shouldHave(text("Student Name"), text("Artur Legenda"));
        $(".table-responsive").shouldHave(text("Student Email"), text("alex@egorov.com"));
        $(".table-responsive").shouldHave(text("Gender"), text("Male"));
        $(".table-responsive").shouldHave(text("Mobile"), text("7999333444"));
        $(".table-responsive").shouldHave(text("Date of Birth"), text("20 April,1941"));
        $(".table-responsive").shouldHave(text("Subjects"), text("Economics"));
        $(".table-responsive").shouldHave(text("Hobbies"), text("Sports"));
        $(".table-responsive").shouldHave(text("Picture"), text("testImage.jpg"));
        $(".table-responsive").shouldHave(text("Address"), text("Some street 1"));
        $(".table-responsive").shouldHave(text("State and City"), text("Haryana Karnal"));
    }
}