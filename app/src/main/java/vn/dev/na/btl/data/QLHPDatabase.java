package vn.dev.na.btl.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import vn.dev.na.btl.data.dao.IAccountDAO;
import vn.dev.na.btl.data.dao.IMajorDAO;
import vn.dev.na.btl.data.dao.IMajorSubjectDAO;
import vn.dev.na.btl.data.dao.ISubjectDAO;
import vn.dev.na.btl.data.entity.Account;
import vn.dev.na.btl.data.entity.Major;
import vn.dev.na.btl.data.entity.MajorSubject;
import vn.dev.na.btl.data.entity.Subject;

@Database(entities ={Subject.class, Major.class, MajorSubject.class, Account.class}, exportSchema = false, version = 1)
public abstract class QLHPDatabase extends RoomDatabase {

    public abstract ISubjectDAO subjectDAO();
    public abstract IMajorDAO majorDAO();
    public abstract IMajorSubjectDAO majorSubjectDAO();
    public abstract IAccountDAO accountDAO();

    private static volatile QLHPDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static QLHPDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QLHPDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QLHPDatabase.class, "btl_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
