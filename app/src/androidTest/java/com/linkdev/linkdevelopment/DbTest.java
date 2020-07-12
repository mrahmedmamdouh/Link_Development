package com.linkdev.linkdevelopment;

import com.linkdev.linkdevelopment.presistance.ArticleDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

@RunWith(AndroidJUnit4.class)
public class DbTest {
    protected ArticleDatabase database;

    @Before
    public void initDb() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                ArticleDatabase.class).allowMainThreadQueries().build();

    }

    @After
    public void closeDb() {
        database.close();
    }
}
