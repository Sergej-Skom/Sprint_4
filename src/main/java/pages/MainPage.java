package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    // Локатор кнопки заказа сверху на странице
    private final String locatorButtonOrderUP = ".//button[@class='Button_Button__ra12g']";
    // Локатор кнопки заказа снизу на странице
    private final String locatorButtonOrderDown = ".//div[@class='Home_FinishButton__1_cWm']/button";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Открыть главную страницу сайта
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Кликнуть по заголовку в "Вопросы о важном"
    public void clickAccordeonHeading(String locatorAccordeonHeading) {
        WebElement element = driver.findElement(By.id(locatorAccordeonHeading));
        // Прокрутить до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Ожидание, пока элемент станет видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.id(locatorAccordeonHeading)));
        // Кликнуть по заголовку
        element.click();
    }

    // Получить текст из заголовка в "Вопросы о важном"
    public String getTextAccordeonHeading(String locatorAccordeonHeading) {
        WebElement element = driver.findElement(By.id(String.valueOf(locatorAccordeonHeading)));
        String actualTextAccordeonHeader = element.getText();
        return actualTextAccordeonHeader;
    }

    // Получить текст из выпавшей панели (после клика по заголовку в "Вопросы о важном)
    public String getTextAccordeonPanel(String locatorAccordeonPanel) {
        // Ожидание, пока элемент станет видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorAccordeonPanel)));
        String actualTextAccordeonPanel = driver.findElement(By.id(locatorAccordeonPanel)).getText();
        return actualTextAccordeonPanel;
    }

    // Кликнуть по кнопке "Заказать"
    public void clickOrder(int n) {
        if (n == 1) {
            driver.findElement(By.xpath(locatorButtonOrderUP)).click();
        } else {
            // Прокрутить до элемента
            WebElement element = driver.findElement(By.xpath(locatorButtonOrderDown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(locatorButtonOrderDown)).click();
        }
    }
}