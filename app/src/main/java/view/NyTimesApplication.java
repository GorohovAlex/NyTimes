package view;

import android.app.Application;
import android.arch.persistence.room.Room;
import api.AppDatabase;

public class NyTimesApplication extends Application {
    public static NyTimesApplication instance;
    private AppDatabase database;
    public final static String BASE_PATH = "https://api.nytimes.com/svc/mostpopular/v2/";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

    }

    public static NyTimesApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
