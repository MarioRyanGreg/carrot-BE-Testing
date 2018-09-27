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

import com.mitrais.carrot.models.ShareType;
import com.mitrais.carrot.payload.ApiResponse;
import com.mitrais.carrot.services.SharingTypeService;

/**
 * ShareTypeController is a rest full controller for ShareType entity
 * 
 * @author Medianto
 *
 */

@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class ShareTypeController {

	@Autowired
	public SharingTypeService shareTypeService;

	/**
	 * get all data
	 * 
	 * @return ShareType all ShareType record from database
	 */
	@GetMapping("/sharing-types")
	@ResponseBody
	public Iterable<ShareType> all() {
		return shareTypeService.findAll();
	}

	/**
	 * create new data
	 * 
	 * @param body ShareType send from front-end
	 * @return ResponseEntity response status created
	 */
	@PostMapping("/sharing-types")
	@ResponseBody
	public ResponseEntity<Void> save(@Valid @RequestBody ShareType body) {
		shareTypeService.save(body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.CREATED);
	}

	/**
	 * get detail by id
	 * 
	 * @param id id selected from front-end
	 * @return shareType matched ShareType record from database with response status
	 */
	@GetMapping("/sharing-types/{id}")
	@ResponseBody
	public ResponseEntity<ShareType> detail(@PathVariable Integer id) {
		return new ResponseEntity<ShareType>(shareTypeService.findById(id), HttpStatus.OK);
	}

	/**
	 * update data
	 * 
	 * @param id   id selected from front-end
	 * @param body udpated ShareType send from front-end
	 * @return ResponseEntity response status created
	 */
	@PutMapping("/sharing-types/{id}")
	@ResponseBody
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ShareType body) {
		ShareType model = shareTypeService.update(id, body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
	}

	/**
	 * action delete to set is deleted = true
	 * 
	 * @param id id selected from front-end
	 * @return ResponseEntity response status created
	 */
	@DeleteMapping("/sharing-types/{id}")
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		shareTypeService.delete(id);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
	}
}
