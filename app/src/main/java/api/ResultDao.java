package api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pojo.Result;
import pojo.ResultRoom;

@Dao
public interface ResultDao {
    @Query("SELECT * FROM resultroom")
    List<ResultRoom> getAll();

    @Query("SELECT * FROM resultroom WHERE url = :url")
    ResultRoom getById(String url);

    @Insert
    void insert(ResultRoom resultroom);

    @Update
    void update(ResultRoom resultroom);

    @Delete
    void delete(ResultRoom resultroom);

    @Query("DELETE FROM resultroom")
    void deleteAll();
}

