package org.odk.collect.android.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;

import java.io.*;

/**
 * Integration test that runs through FormHierarchy behavior.
 */
@RunWith(AndroidJUnit4.class)
public class FormHierarchyActivityTest {

    private static final String NESTED_REPEATS_FORM = "nested-repeats.xml";
    private static final String FORMS_DIRECTORY = "/odk/forms/";

    @Rule
    public FormEntryActivityTestRule activityTestRule = new FormEntryActivityTestRule();

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    @BeforeClass
    public static void copyFormToSdCard() throws IOException {
        String pathname = getFormPath();
        if (new File(pathname).exists()) {
            return;
        }

        AssetManager assetManager = InstrumentationRegistry.getContext().getAssets();
        InputStream inputStream = assetManager.open(NESTED_REPEATS_FORM);

        File outFile = new File(pathname);
        OutputStream outputStream = new FileOutputStream(outFile);

        IOUtils.copy(inputStream, outputStream);
    }

    @BeforeClass
    public static void beforeAll() {
        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
    }

    @Test
    public void testSomething() {
        // TODO: 1. Load the form (launch FormEntryActivity first with EXTRA_TESTING_PATH?).
        // TODO: 2. Navigate around using helpers.
        // TODO: 3. Test with screenshots and assertions.
    }

    // --- Helpers

    private static String getFormPath() {
        return Environment.getExternalStorageDirectory().getPath()
                + FORMS_DIRECTORY
                + NESTED_REPEATS_FORM;
    }

    private class FormEntryActivityTestRule extends IntentsTestRule<FormEntryActivity> {

        FormEntryActivityTestRule() {
            super(FormEntryActivity.class);
        }

        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            return new Intent(context, FormEntryActivity.class);
        }
    }
}
