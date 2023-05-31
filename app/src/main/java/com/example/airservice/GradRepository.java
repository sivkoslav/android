package com.example.airservice;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class GradRepository {

    private GradDao gradDao;
    private LiveData<List<Grad>> gradovi;



    public GradRepository(Application application){
        AppDatabase baza=AppDatabase.getInstance(application);
        gradDao=baza.gradDao();
        gradovi=gradDao.getAllGrads();
    }

    public void insert (Grad grad){
        AppDatabase.databaseWriteExecutor.execute(()->{
            gradDao.insert(grad);
        });
    }

    public void update(Grad grad){
        AppDatabase.databaseWriteExecutor.execute(()->{
            gradDao.update(grad);
        });
    }

    public void delete(Grad grad){
        AppDatabase.databaseWriteExecutor.execute(()->{
            gradDao.delete(grad);
        });

    }

    public void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(()->{
            gradDao.deleteAllGrads();
        });

    }

    public LiveData<List<Grad>> getGradovi(){
        return gradovi;
    }



}
