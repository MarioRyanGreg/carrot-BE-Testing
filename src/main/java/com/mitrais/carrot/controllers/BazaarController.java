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

import com.mitrais.carrot.models.Bazaar;
import com.mitrais.carrot.payload.ApiResponse;
import com.mitrais.carrot.services.BazaarService;

/**
 * BazaarController is a rest full controller for Bazaar entity
 * 
 * @author Ryan Mario
 *
 */

@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class BazaarController {

	public BazaarService bazaarService;

    public BazaarController(@Autowired BazaarService bazaarService) {
        this.bazaarService = bazaarService;
    }

    /**
	 * get all data
	 * 
	 * @return Bazaar all Bazaar record from database
	 */
    @GetMapping("/bazaars")
    @ResponseBody
    public Iterable<Bazaar> all() {
        return bazaarService.findAll();
    }

    /**
	 * create new data
	 * 
	 * @param body Bazaar send from front-end
	 * @return ResponseEntity response status created
	 */
    @PostMapping("/bazaars")
    @ResponseBody
    public ResponseEntity<Void> save(@Valid @RequestBody Bazaar body) {
    	bazaarService.save(body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.CREATED);
    }

    /**
	 * get detail by id
	 * 
	 * @param id id selected from front-end
	 * @return bazaar matched Bazaar record from database with response status
	 */
    @GetMapping("/bazaars/{id}")
    @ResponseBody
    public ResponseEntity<Bazaar> show(@PathVariable Integer id) {
        return new ResponseEntity<Bazaar>(bazaarService.findById(id), HttpStatus.OK);
    }

    /**
	 * update data
	 * 
	 * @param id id selected from front-end
	 * @param body udpated Bazaar send from front-end
	 * @return ResponseEntity response status created
	 */
    @PutMapping("/bazaars/{id}")
    @ResponseBody
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Bazaar body) {
    	Bazaar model = bazaarService.update(id, body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
    }

    /**
	 * action delete to set is deleted = true
	 * 
	 * @param id id selected from front-end
	 * @return ResponseEntity response status created
	 */
    @DeleteMapping("/bazaars/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    	bazaarService.delete(id);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
    }
}
