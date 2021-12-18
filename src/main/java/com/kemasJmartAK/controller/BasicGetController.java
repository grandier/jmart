package com.kemasJmartAK.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.Algorithm;
import com.kemasJmartAK.dbjson.*;

/**
 * BasicGetController is used as a universal get by id, get by page
 * @param <T> generic can be used with all type of data.
 * @author Kemas Rafly Omar Thoriq
 *
 */
@RestController
public interface BasicGetController <T extends Serializable> {
	/**
	 * 
	 * @param id input untuk yang ingin dicari
	 * @return hasil percarian bedasarkan id yang diinputkan
	 */
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(),e -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable ();

    /**
     * 
     * @param page keberapa yang ingin dilihat
     * @param pageSize ukuran dariapda page yang sedang dilihat
     * @return
     */
    @GetMapping("/page")
    public default List<T> getPage(int page, int pageSize){
        final JsonTable<T> table = getJsonTable();
        return Algorithm.paginate(table,page,pageSize,o->true);
    }

}
