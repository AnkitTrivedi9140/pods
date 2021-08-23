package com.example.podsstore.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.podsstore.data.local.entity.ProductEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuantityDao {

    @Query("INSERT INTO product_qty (product_id,quantity,userid)\n" +
            "SELECT :productid ,:qty,:userid\n" /*+
            "WHERE NOT EXISTS (SELECT 1 FROM product_qty WHERE product_id = :productid);"*/)
    long insert(String productid, String qty,String userid);

    @Query("SELECT * FROM product_qty")
    Single<List<ProductEntity>> getSelectedItems();

    @Query("UPDATE product_qty set quantity=:qty WHERE product_id=:productid")
    void update(String qty,String productid);

    @Query("select 1 from product_qty  WHERE product_id=:productid")
    String getexist(String productid);

    @Query("SELECT quantity FROM product_qty where product_id=:productid")
    String getqty(String productid);

}
