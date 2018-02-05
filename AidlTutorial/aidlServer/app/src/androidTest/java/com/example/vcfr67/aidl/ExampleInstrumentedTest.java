package com.example.vcfr67.aidl;

import android.content.Context;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Server.MyImpl MyImplObj;
    @Before
    public void Setup()
    {
        Server serverObj = new Server();
        MyImplObj = serverObj.new MyImpl();

    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.vcfr67.aidl", appContext.getPackageName());
    }

    @Test
    public void TestInnerClass()
    {
        try {
            assertEquals(2, MyImplObj.cal(2, 1));
        }
        catch (RemoteException ex)
        {

        }
    }


}
