package com.example.moksleivis.knygalaboras;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.moksleivis.knygalaboras.Model.DatabaseHandler;
import com.example.moksleivis.knygalaboras.Model.Knyga;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext;
    DatabaseHandler db;
    List<Knyga> knygatest;
    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
        db = new DatabaseHandler(appContext);
        knygatest = new ArrayList<Knyga>();
            }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Knyga knyga = new Knyga("Angelai","1992/12/15",
                "Dan","Documentary",
                "Common",123,"Hard",0,1,1,0);
        db.addBook(knyga);

        Knyga finalknyga = new Knyga();
        knygatest = db.getAllBooks();
        for(int i = 0; i <knygatest.size();i++){
            if (knygatest.get(i).getName().equals(knyga.getName())){
                finalknyga = knygatest.get(i);
            }
        }
        assertBooksEqual(finalknyga,knyga);
       // assertEquals("com.example.moksleivis.knygalaboras", appContext.getPackageName());
    }
    private void assertBooksEqual(Knyga expected,Knyga actual){
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertEquals(expected.getRelease_year(),actual.getRelease_year());
        Assert.assertEquals(expected.getAuthor(),actual.getAuthor());
        Assert.assertEquals(expected.getGenre(),actual.getGenre());
        Assert.assertEquals(expected.getRarity(),actual.getRarity());
        Assert.assertEquals(expected.getPages(),actual.getPages());
        Assert.assertEquals(expected.getCover(),actual.getCover());
        Assert.assertEquals(expected.getCheck1(),actual.getCheck1());
        Assert.assertEquals(expected.getCheck2(),actual.getCheck2());
        Assert.assertEquals(expected.getCheck3(),actual.getCheck3());
        Assert.assertEquals(expected.getCheck4(),actual.getCheck4());
    }
}
