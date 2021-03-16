package com.example.podsstore.data.local.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.example.podsstore.data.local.DatabaseService;
import com.example.podsstore.data.local.dao.QuantityDao;
import com.example.podsstore.data.local.entity.ProductEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Single;

public class QuantityRepo {

    public QuantityDao quantityDao;

    public QuantityRepo(Application application) {

        DatabaseService databaseService = DatabaseService.getInstance(application);
        quantityDao = databaseService.quantityDao();
    }

    public void insert(String productid, String qty,String userid) {
        quantityDao.insert(productid, qty,userid);

    }
    public Single<List<ProductEntity>> getSelectedList() {
        return quantityDao.getSelectedItems();
    }

    public void update(String  qty,String productid) {
        quantityDao.update(qty,productid);

    }

    public String isexist(String productid) {
        try {
            return new Isexist(quantityDao).execute(productid).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new String();
    }
    private static class Isexist extends AsyncTask<String, Void, String> {

        QuantityDao dispatchDao;

        public Isexist(QuantityDao dispatchDao) {
            this.dispatchDao = dispatchDao;
        }

        @Override
        protected String doInBackground(String... d) {
            return dispatchDao.getexist(d[0]);


        }

    }
    public String getqty(String productid) {
        try {
            return new GetQty(quantityDao).execute(productid).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new String();
    }
    private static class GetQty extends AsyncTask<String, Void, String> {

        QuantityDao dispatchDao;

        public GetQty(QuantityDao dispatchDao) {
            this.dispatchDao = dispatchDao;
        }

        @Override
        protected String doInBackground(String... d) {
            return dispatchDao.getqty(d[0]);


        }

    }

}
