package com.mitrais.carrot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.carrot.models.BazaarItem;
import com.mitrais.carrot.payload.ApiResponse;
import com.mitrais.carrot.services.BazaarItemService;

/**
 * BazarItemController is a rest full controller for BazaarItem entity
 * 
 * @author Ryan Mario
 *
 */

@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class BazaarItemController {

	public BazaarItemService bazaarItemService;

    public BazaarItemController(@Autowired BazaarItemService bazaarItemService) {
        this.bazaarItemService = bazaarItemService;
    }

    /**
	 * get all data
	 * 
	 * @return BazaarItem all BazaarItem record from database
	 */
    @GetMapping("/bazaars-items")
    @ResponseBody
    public Iterable<BazaarItem> all() {
        return bazaarItemService.findAll();
    }
    
    /**
	 * create new data
	 * 
	 * @param body BazaarItem send from front-end
	 * @return ResponseEntity response status created
	 */
    @PostMapping("/bazaars-items")
    @ResponseBody
    public ResponseEntity<Void> save(@Valid @RequestBody BazaarItem body) {  
        bazaarItemService.save(body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.CREATED);
    }

    /**
	 * get detail by id
	 * 
	 * @param id id selected from front-end
	 * @return bazaarItem matched BazaarItem record from database with response status
	 */
    @GetMapping("/bazaars-items/{id}")
    @ResponseBody
    public ResponseEntity<BazaarItem> detail(@PathVariable Integer id) {
        return new ResponseEntity<BazaarItem>(bazaarItemService.findById(id), HttpStatus.OK);
    }

    /**
	 * update data
	 * 
	 * @param id id selected from front-end
	 * @param body udpated BazaarItem send from front-end
	 * @return ResponseEntity response status created
	 */
    @PutMapping("/bazaars-items/{id}")
    @ResponseBody
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody BazaarItem body) {        
        BazaarItem model = bazaarItemService.update(id, body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
    }

    /**
	 * action delete to set is deleted = true
	 * 
	 * @param id id selected from front-end
	 * @return ResponseEntity response status created
	 */
    @DeleteMapping("/bazaars-items/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Integer id) {      
        bazaarItemService.delete(id);
        return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
    }
}