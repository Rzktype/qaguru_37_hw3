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
        Configuration.holdBrowserOpen = false;
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
        $(".custom-control-label").click();
        $("#userNumber").setValue("7999333444");
        $(".react-datepicker-wrapper").click();
        $(byText("April")).click();
        $(byText("1941")).click();
        $(byText("20")).click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#currentAddress").setValue("Some street 1");
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("testImage.jpg");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();
        //sleep(3000);

        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(text("Artur Legenda")); // проверим что ввели в модалку ФИ
    }
}