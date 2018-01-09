package arch.wb.retail;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Arun.Kumar04 on 1/4/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppNavigationTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     */
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     * Check for cart screen opening
     */
    @Test
    public void clickOnCartNavigationItem_ShowCartScreen() {
        openCartScreen();

        onView(withId(R.id.cart)).check(matches(isDisplayed()));
    }

    /**
     * Check for profile screen opening
     */
    @Test
    public void clickOnProfileNavigationItem_ShowCartScreen() {
        openProfileScreen();

        onView(withId(R.id.profile)).check(matches(isDisplayed()));
    }

    /**
     * Check for products screen opening
     */
    @Test
    public void clickOnProductsNavigationItem_ShowCartScreen() {
        openProductsScreen();

        onView(withId(R.id.lv_categories)).check(matches(isDisplayed()));
    }

    private void openCartScreen() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());

        onData(anything()).inAdapterView(withId(R.id.left_drawer)).atPosition(2).perform(click());
    }

    private void openProfileScreen() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());

        onData(anything()).inAdapterView(withId(R.id.left_drawer)).atPosition(1).perform(click());
    }

    private void openProductsScreen() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());

        onData(anything()).inAdapterView(withId(R.id.left_drawer)).atPosition(0).perform(click());
    }
}
