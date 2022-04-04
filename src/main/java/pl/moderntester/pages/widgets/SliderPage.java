package pl.moderntester.pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SliderPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(SliderPage.class);
    private String button_slider = "#custom-handle";

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    public SliderPage moveSlider(int value) {
        int currentValue = Integer.parseInt(driver.findElement(By.cssSelector(button_slider)).getText());
        int slideValue = value - currentValue;
        Keys direction;
        if (value != currentValue) {
            if (slideValue > 0) direction = Keys.ARROW_RIGHT;
            else direction = Keys.ARROW_LEFT;
            for (int i = 0; i < Math.abs(slideValue); i++) {
                driver.findElement(By.cssSelector(button_slider)).sendKeys(direction);
            }
        }
        log.info("Slider moved to: " + value);
        assertThat("Wrong slider value!", value, equalTo(Integer.parseInt(driver.findElement(By.cssSelector(button_slider)).getText())));
        return this;
    }
}
