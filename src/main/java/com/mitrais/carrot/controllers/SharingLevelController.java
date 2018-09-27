package com.mitrais.carrot.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.mitrais.carrot.models.SharingLevel;
import com.mitrais.carrot.payload.ApiResponse;
import com.mitrais.carrot.services.interfaces.ISharingLevelService;
import com.mitrais.carrot.validation.exception.ResourceNotFoundException;

/**
 * SharingLevelController is a rest full controller for SharingLevel entity
 * 
 * @author Medianto
 *
 */
@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class SharingLevelController {

	@Autowired
	public ISharingLevelService sharingLevelService;

	/**
	 * get all data
	 *
	 * @return SharingLevel all SharingLevel record from database
	 */
	@GetMapping("/sharing-levels")
	@ResponseBody
	public Iterable<SharingLevel> all() {
		return sharingLevelService.findAllBydeletedIsNull();
	}

	/**
	 * create new data
	 *
	 * @param body SharingLevel send from front-end
	 * @return ResponseEntity response status created
	 */
	@PostMapping("/sharing-levels")
	@ResponseBody
	public ResponseEntity save(@Valid @RequestBody SharingLevel body) {
		sharingLevelService.save(body);
		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.CREATED);
	}

	/**
	 * get detail by id
	 * 
	 * @param id id selected from front-end
	 * @return SharingLevel matched SharingLevel record from database with response
	 *         status
	 */
	@GetMapping("/sharing-levels/{id}")
	@ResponseBody
	public ResponseEntity<SharingLevel> show(@PathVariable Integer id) {
		SharingLevel sharingLevel = sharingLevelService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data", "id", id));
		return new ResponseEntity<SharingLevel>(sharingLevel, HttpStatus.OK);
	}

	/**
	 * update data
	 * 
	 * @param id   id selected from front-end
	 * @param body udpated SharingLevel send from front-end
	 * @return ResponseEntity response status created
	 */
	@PutMapping("/sharing-levels/{id}")
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody SharingLevel body) {
		SharingLevel shareLevel = sharingLevelService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data", "id", id));
		BeanUtils.copyProperties(body, shareLevel);
		shareLevel.setId(id);
		sharingLevelService.save(shareLevel);

		return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
	}

	/**
	 * action delete to set is deleted = true
	 * 
	 * @param id id selected from front-end
	 * @return ResponseEntity response status created
	 */
	@DeleteMapping("/sharing-levels/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		SharingLevel sl = sharingLevelService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data", "id", id));
		sl.setDeleted(true);
		sharingLevelService.save(sl);
		return new ResponseEntity(new ApiResponse(true, "Data is Deleted!"), HttpStatus.OK);
	}
}
