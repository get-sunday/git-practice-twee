package com.facotr.video.gitdemos

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facotr.video.gitdemos.mock.User
import com.facotr.video.gitdemos.utils.DensityUtilsd

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.facotr.video.gitdemos", appContext.packageName)
    }

    fun getUsrName(){
        var appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val name = DensityUtilsd.getName(appContext)
        Log.e("test","values: " + name)

//        var user: User = Mockito.mock(User)
    }

//    fun getNames(): String{
//
//        return "Sunday"
//    }
}
