package com.test.widgets;

import com.configuration.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.moderntester.pages.widgets.SliderPage;

public class SliderTest extends TestBase {
    private static Logger log = LoggerFactory.getLogger(SliderTest.class);

    @Test
    public void manageSlider() {
        SliderPage sliderPage = new SliderPage(driver);

        driver.get("https://seleniumui.moderntester.pl/slider.php");
        sliderPage
                .moveSlider(50)
                .moveSlider(80)
                .moveSlider(80)
                .moveSlider(20)
                .moveSlider(0);
        log.info(passed, passedMessage);
    }
}