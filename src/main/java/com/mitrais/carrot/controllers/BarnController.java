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

import com.mitrais.carrot.models.Barn;
import com.mitrais.carrot.payload.ApiResponse;
import com.mitrais.carrot.services.BarnService;


/**
 * 
 * Class BarnController controlling incoming API request
 *
 */
@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class BarnController {

    public BarnService barnService;

    /**
     *  Class Constructor specifying the barn service.
     * @param barnService Service class to get the data based on business logic
     */
    public BarnController(@Autowired BarnService barnService){
        this.barnService = barnService;
    }
    
    /**
     * Get all barn data
     * @return 		the barn data
     */
    @GetMapping("/barns")
    @ResponseBody
    public Iterable<Barn> all() {
        return barnService.findAll();
    }

    /**
     * Create new data
     * @param 	body 	the object of barn to save
     * @return	the API response with HTTP CREATED status 			
     */
    @PostMapping("/barns")
    @ResponseBody
    public ResponseEntity<Barn> save(@Valid @RequestBody Barn body) {
        barnService.save(body);
        return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.CREATED);
    }

    /**
     * Get data by id
     * @param 	id	the barn identifier
     * @return 	the barn data 
     */
    @GetMapping("/barns/{id}")
    @ResponseBody
    public Barn detail(@PathVariable Integer id) {
        return barnService.findById(id);
    }

    /**
     * Update data by id
     * @param id	the barn identifier
     * @param body	the barn with updated data
     * @return The model with HTTP status OK
     */
    @PutMapping("/barns/{id}")
    @ResponseBody
    public ResponseEntity<Barn> update(@PathVariable Integer id, @Valid @RequestBody Barn body) {
        Barn model = barnService.findById(id);
        BeanUtils.copyProperties(model, body);
        model.setId(id);
        barnService.save(model);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * delete data by id
     * @param id	the barn identifier
     * @return Api response with status OK
     */
    @DeleteMapping("/barns/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Barn sl = barnService.findById(id);
        sl.setDeleted(true);
        barnService.save(sl);
//        barnRepository.delete(sl.get());
        return new ResponseEntity(
                new ApiResponse(true, "Data id : " + id + "deleted successfully"),
                HttpStatus.OK
        );
    }
}
