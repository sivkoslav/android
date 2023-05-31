package com.example.airservice;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={Grad.class},version=1)
public abstract class AppDatabase extends RoomDatabase {


    private static volatile AppDatabase instanca;

    public abstract GradDao gradDao();

    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static  AppDatabase getInstance(Context context){
        if(instanca==null){
            synchronized (AppDatabase.class){
                if(instanca==null){
                    instanca=Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"grad_baza").addCallback(sRoomDatabaseCallback).build();
                }
            }


        }

        return instanca;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                GradDao dao=instanca.gradDao();
                dao.deleteAllGrads();

                Grad grad=new Grad("Madagaskar","Antananarivo",500);
                dao.insert(grad);
            });
        }
    };
}
