package api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pojo.MediaMetadatumRoom;
import pojo.MediumRoom;
import pojo.Result;

@Dao
public interface MediaMetadatumRoomDao {
    @Query("SELECT * FROM mediametadatumroom")
    List<MediaMetadatumRoom> getAll();

    @Query("SELECT * FROM mediametadatumroom WHERE id = :id")
    MediaMetadatumRoom getById(long id);

    @Insert
    void insert(MediaMetadatumRoom mediametadatumroom);

    @Update
    void update(MediaMetadatumRoom mediumediametadatumroommRoom);

    @Delete
    void delete(MediaMetadatumRoom mediametadatumroom);
}
