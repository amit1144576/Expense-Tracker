package com.ISEE2019_CODEPROS_team.MoneyCTRL.View;

import android.content.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import androidx.test.filters.MediumTest;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@MediumTest
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    private static final String FAKE_STRING = "Login was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {

        MainActivity myObjectUnderTest = new MainActivity(mMockContext);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.validate("user","user");
        // ...then the result should be the expected one.
        // assertThat(result, is(FAKE_STRING));


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void openRegister() {
    }

    @Test
    public void openTransView() {
    }

    @Test
    public void onBackPressed1() {
    }

    @Test
    public void openRegister1() {
    }

    @Test
    public void openTransView1() {
    }
}