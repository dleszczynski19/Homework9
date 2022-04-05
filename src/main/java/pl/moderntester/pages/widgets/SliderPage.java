package pl.moderntester.pages.widgets;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.BasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SliderPage extends BasePage {
    private static Logger log = LoggerFactory.getLogger(SliderPage.class);

    @FindBy(css = "#custom-handle")
    private WebElement button_slider;

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    public SliderPage moveSlider(int value) {
        int currentValue = Integer.parseInt(button_slider.getText());
        int slideValue = value - currentValue;
        Keys direction;
        if (value != currentValue) {
            if (slideValue > 0) direction = Keys.ARROW_RIGHT;
            else direction = Keys.ARROW_LEFT;
            for (int i = 0; i < Math.abs(slideValue); i++) {
                button_slider.sendKeys(direction);
            }
        }
        log.info("Slider moved to: " + value);
        assertThat("Wrong slider value!", value, equalTo(Integer.parseInt(button_slider.getText())));
        return this;
    }
}
