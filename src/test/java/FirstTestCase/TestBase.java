package FirstTestCase;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    WebDriver driver;
    static final String PhoneNumberLogin = "+79969797537";
    static final String TestName = "Test";
    static final String TestEmailAddress = "test@test.ru";
    static final String TestPhoneNumberLogin = "+77777777777";
    static final String AddressStreet = "улица Говорова, Одинцово";
    static final String AddressHome = "85";
    static final String OrderComment = "Тест. Не готовить";
    static final String howMoneyToCourier = "5000";
    static final String HowManyUsers = "3";


    @Before
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("videoName", "SalatBarTestCaseForFirstSendOrder.mp4");
        capabilities.setCapability("name", "SalatBarTestCaseForFirstSendOrder");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "91.0");

        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.17:8080/wd/hub"),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @After
    public void end() {
        closeWebDriver();
    }


    @Rule
    public TextReport report = new TextReport();

    @Step("Открываю сайт")
    public void openURL() {
        open("https://salatbar.ru/");
    }

    @Step("Выбираю рандомный город. Выбор городов реализован через Drop Down")

    public void RestSelect() {
        $x("//button[contains(@class, \"btn-address-selector\")]").click();
        $(By.xpath("//label[@class = \"last\"]")).shouldBe(visible).click();

        $x("//div[@class = \"ymaps-form-container\"]//label[@class = \"first\"]").shouldBe(visible).click();

        List<SelenideElement> terminalSwitch = elements(By.xpath("//div[@class = \"v-self-service-terminals-list\"]/div[contains(@class, \"v-self-service-terminal\") and not (contains(text(), \"Фучика\"))] "));
        int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();

        $x("//button[text() = \"Подтвердить\"]").shouldBe(visible).click();

    }
    /*$(By.xpath("//label[@class = \"last\"]")).shouldBe(visible).click();

        $x("//div[@class = \"ymaps-form-container\"]//label[@class = \"first\"]").shouldBe(visible).click();

        List<SelenideElement> terminalSwitch = elements(By.xpath("//div[@class = \"v-self-service-terminals-list\"]/div[contains(@class, \"v-self-service-terminal\") and not (contains(text(), \"Шипиловская\"))] "));
        int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();

        $x("//button[text() = \"Подтвердить\"]").shouldBe(visible).click();
*/ /*- выбор адреса при входе (Высвечивается при входе на сайт)*/



    /*
        List<SelenideElement> terminalSwitch = elements(By.xpath("//div[contains(@class, \"desktop\")]/div [@class = \"top-rest-select\"]/ul[@class = \"dropdown\"]/li/a"));
        int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();*/ /*- Выбор города через dropDown */

    /*List<SelenideElement> terminalSwitch = elements(By.xpath("//ul[contains(@class, \"city-select-list\")]/li/a[(@href) and not (@href = \"/away?url=http%3A%2F%2Fvidnoe.pizza-sushi.com%2F&RestaurantID=01e55e57-78cf-4d3d-b6f4-0b26691781bd\") and not (@href = \"https://tokapizza.ru/\") and not (@href = \"/away?url=http%3A%2F%2Fpodolsk.pizza-sushi.com%2F&RestaurantID=bede0378-681e-4ab7-8377-95840735eea8\")]"));
    int i = (int) (Math.random() * terminalSwitch.size());
        terminalSwitch.get(i).click();*/ /*- Выбор города через попап*/


    @Step("Тыкаю на переход к главной странице")
    public void GoMainPage() {
        $x("//div[contains(@class, \"h-left\")]/div[@class = \"logo\"]").click();
    }

    @Step("Перехожу в рандомный пункт меню для оформления тестового заказа")
    public void mathRandomHead() {
        List<SelenideElement> mathRandomHead = elements(By.xpath("//a[contains(@class, \"scroll-nav_link\") and not (@href = \"/menu/sushi\") and not (@href = \"/menu/rolly\")]"));
        int i = (int) (Math.random() * mathRandomHead.size());
        mathRandomHead.get(i).click();

    }
//Parma Math Random Head
   /*  //div[contains(@class, "swiper-slide")]//a[contains(@class, "scroll-nav_link") and not (@href = "/menu/combo") and not (@href = "/menu/pasta-rizotto") and not (@href = "/menu/picca")] */



// Чикибамбони mathRandomHead
    /*@Step("Перехожу в рандомный пункт меню для оформления тестового заказа")
    public void mathRandomHead() {
        $x("//div[contains(@class, \"header-dropdown-link\")]/a[@id = \"dropdownMenuButton\"]").click();
        List<SelenideElement> mathRandomHead = elements(By.xpath("//ul[contains(@class, \"dropdown-menu\")]/li/a[contains(@href, \"\") and not (@href = \"/menu/soberi-pizzu-sam\")]"));
        int i = (int) (Math.random() * mathRandomHead.size());
        mathRandomHead.get(i).click();
    }*/

//- кастыль для мясоруба, добавление картошки фри.
    /*$(By.xpath("//a[@href= \"/menu/frityur\"]")).click();*/


    @Step("Добавляем в корзину карточку товара")
    public void PickRandCards() {

        List<SelenideElement> clickRandomCards = elements(By.xpath("//div[contains(@class, \"productBox\") and not(contains(@class, \"in-stop-list\"))]//button[contains(@class, \"add-to-basket\") or (contains(text(), \"В корзину\"))]"));
        int i = (int) (Math.random() * clickRandomCards.size());
        SelenideElement randCard = $(clickRandomCards.get(i));
        /*$x("//a[contains(@class, \"product-link-short\")]").scrollIntoView(true);*/
        /* randCard.shouldBe(visible).scrollIntoView("{block: \"start\"}");*/
        randCard.closest(".item").scrollIntoView(true);
        randCard.click();

       /* clickRandomCards.get(i).isDisplayed();
        clickRandomCards.get(i).click();*/


    }

// - костыль для мясоруба с выбором картошки фри в качестве единственного возможного блюда, которое практически не попадает в стоп лист
/* $x("//a[@href = \"/product/kartofel-fri\"]").click();
        SelenideElement addToBasket = $x("//a[contains(@class, \"add-to-basket\")]").shouldBe(text("В корзину"));
        addToBasket.click();*/

// - Добавление в корзину карточки конструктора (Чикибамбони)
    /*@Step("Выбираю рандомную карточку товара")
    public void pickRandCards() {
        List<SelenideElement> clickRandomCards = elements(By.xpath("//a[contains(@class, \"product-link-short\") and not (@data-discount-time-id = \"\")]"));
        int i = (int) (Math.random() * clickRandomCards.size());
        SelenideElement randCard = $(clickRandomCards.get(i));
        randCard.closest(".item").scrollIntoView(true);
        randCard.click();
    }
    @Step("Добавляю в корзину карточку товара")
    public void addCardToBasket() {
        $x("//a[contains(@class, \"add-to-cart\")]").shouldBe(visible).click();
    }
*/

    @Step("Переходим в корзину")

    public void GoBasket() {

        $x("//a[contains(@class, \"btn-basket\")]").click();
        /*.doubleClick()*/ /*- кастыль для сайтов с попапом, подставить к методу, который падает из-за поапа. */
    }


    @Step("Выбираем тип доставки самовывоз")
    public void SelectDeliveryTypePickUp() {
        $(By.xpath("//label[@class = \"last\"]")).click();
    }


    @Step("Выбираем пункт самовывоза")
    public void SelectTerminalForPickUp() {
        /*$(By.id("order_terminal-no-shipment")).selectOption();*/
        List<SelenideElement> options = elements(By.xpath("//select[@id = \"order_terminal-no-shipment\"]/option"));
        int i = (int) (Math.random() * options.size());
        options.get(i).click();
    }

    @Step("Заполняем поля")
    public void fillInFields() {
        $x("//input[@id = \"order_name\"]").scrollTo().setValue(TestName);
        $x("//input[@id = \"order_phone\"]").scrollTo().setValue(TestPhoneNumberLogin);
        $x("//input[@id = \"order_email\"]").scrollTo().setValue(TestEmailAddress);
        $x("//input[@id = \"order_comment\"]").scrollTo().setValue(OrderComment);
        $x("//input[@id = \"order_person-count\"]").scrollTo().setValue(HowManyUsers);
    }

    @Step("Выбираем способ оплаты (Налик)")
    public void SelectPayType() {
        $x("//div[@class = \"payment-wrapper\"][1]").scrollTo().click();
        $("#change").setValue(howMoneyToCourier);
    }


    @Step("Тыкаем на отправку заказа")
    public void SendOrder() {
        $x("//div[@class = \"item-cart-buttons\" ]/button[contains(@class, \"btn\")]").scrollTo().click();
    }

    @Step("Ждём перехода в статус принят")
    public void waitForComplete() {
        $x("//span[contains(text(),'Принят') or (contains(text(),'Поступил')) ]").shouldBe(visible);
    }


}


