package com.example.podsstore.data.local.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.podsstore.data.local.entity.ProductEntity;
import com.example.podsstore.data.local.repo.QuantityRepo;

import java.util.List;

import io.reactivex.Single;

public class QuantityViewModel  extends AndroidViewModel {


    private QuantityRepo repository;
    public QuantityViewModel(@NonNull Application application) {
        super(application);

        repository = new QuantityRepo(application);
    }

    public void insert(String productid, String qty,String userid) {
        repository.insert(productid, qty,userid);
    }
    public Single<List<ProductEntity>> getSelectedList(){
        return repository.getSelectedList();
    }
    public void update( String qty,String productid) {
        repository.update( qty,productid);
    }

    public String isexist(String productid) {
        return   repository.isexist(productid);
    }
    public String getqty(String productid) {
        return   repository.getqty(productid);
    }
}
