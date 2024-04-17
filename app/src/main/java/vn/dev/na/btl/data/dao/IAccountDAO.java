package vn.dev.na.btl.data.dao;


import static androidx.room.OnConflictStrategy.*;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import vn.dev.na.btl.data.entity.Account;

@Dao
public interface IAccountDAO {

    @PrimaryKey(autoGenerate = true)
    @Insert(onConflict = IGNORE)
    void insertAll(Account account);
    @Query("SELECT * FROM account where user_name = :username and password =:password ")
    Account login(String username, String password);
    @Query("SELECT count(1) FROM account ")
    int checkExitAccount();
}
