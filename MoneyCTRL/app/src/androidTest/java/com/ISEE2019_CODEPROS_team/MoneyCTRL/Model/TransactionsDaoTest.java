package com.ISEE2019_CODEPROS_team.MoneyCTRL.Model;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.ISEE2019_CODEPROS_team.MoneyCTRL.XTR_CLASSES_Constants.LiveDataTestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)

public class TransactionsDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Database mDatabase;
    private TransactionsDao transDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                Database.class).allowMainThreadQueries().build();
        transDao = mDatabase.transactionsDao();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetching_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List<Transactions> transList = LiveDataTestUtil.getValue(transDao.getAllTransOrderByID());
        assertTrue(transList.isEmpty());
    }


}