package api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import pojo.MediaMetadatumRoom;
import pojo.MediumRoom;
import pojo.ResultRoom;

@Database(entities = {ResultRoom.class, MediumRoom.class, MediaMetadatumRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResultDao resultDao();
    public abstract MediumDao mediumRoom();
    public abstract MediaMetadatumRoomDao mediaMetadatumRoom();
}
