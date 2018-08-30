package api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import pojo.MediumRoom;

@Dao
public interface MediumDao {
    @Query("SELECT * FROM mediumroom")
    List<MediumRoom> getAll();

    @Query("SELECT * FROM mediumroom WHERE id = :id")
    MediumRoom getById(long id);

    @Insert
    void insert(MediumRoom mediumRoom);

    @Update
    void update(MediumRoom mediumRoom);

    @Delete
    void delete(MediumRoom mediumRoom);
}

