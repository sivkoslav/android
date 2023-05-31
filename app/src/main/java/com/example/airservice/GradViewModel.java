package com.example.airservice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GradViewModel extends AndroidViewModel {

    private GradRepository repository;
    private LiveData<List<Grad>> gradovi;

    public GradViewModel(@NonNull Application application) {
        super(application);
        repository=new GradRepository(application);
        gradovi=repository.getGradovi();
    }
    public void insert(Grad grad){
        repository.insert(grad);
    }

    public void update(Grad grad){
        repository.update(grad);
    }

    public void delete(Grad grad){
        repository.delete(grad);
    }

    public void deleteAllGrads(){
        repository.deleteAll();
    }

    public LiveData<List<Grad>> getGradovi(){
        return gradovi;
    }

}
