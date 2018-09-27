package com.mitrais.carrot.controllers;

import com.mitrais.carrot.models.Transactions;
import com.mitrais.carrot.payload.ApiResponse;

import com.mitrais.carrot.services.TransactionsService;
import com.mitrais.carrot.utils.CustomErrorMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("${spring.data.rest.basePath}")
public class TransactionsController {

    public TransactionsService transactionsService;

    public TransactionsController(@Autowired TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    /**
     * get all transactions, method Get
     *
     * @return collection of transactions with response status
     */
    @GetMapping("/transactions")
    @ResponseBody

    public Iterable<Transactions> all() {
        return transactionsService.findAll();
    }

    /**
     * create new transactions, method POST
     *
     * @param body model of Transactions
     * @return ResponseEntity it Contain Collection of transactions with response status
     */
    @PostMapping("/transactions")
    @ResponseBody
    public ResponseEntity<Transactions> save(@RequestBody @Valid Transactions body) {
        body = transactionsService.save(body);
        return new ResponseEntity(new ApiResponse( true, ""), HttpStatus.CREATED);
    }


    /**
     * get transaction by id
     *
     * @param id the transaction id
     * @return ResponseEntity, it contains collection of transaction
     */
    @GetMapping("/transactions/{id}")
    @ResponseBody
    public Transactions detail(@PathVariable Integer id ){return  transactionsService.findById(id);}


    /**
     * update transaction by id, method PUT
     *
     * @param id           transaction id
     * @param body model of Transactions
     * @return The model with HTTP status OK
     */
    @PutMapping("/transactions/{id}")
    @ResponseBody
    public ResponseEntity<Transactions> update(@PathVariable Integer id , @Valid @RequestBody Transactions body){
        Transactions model = transactionsService.findById(id);
        BeanUtils.copyProperties(model, body);
        model.setId(id);
        transactionsService.save(model);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * delete a transaction by id, method DELETE
     *
     * @param id transaction id
     * @return ResponseEntity it contain Collection of transaction with response status
     */
    @DeleteMapping("/transactions/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Transactions trans = transactionsService.findById(id);
    trans.setDeleted(true);
    transactionsService.save(trans);
    return new ResponseEntity(
            new ApiResponse(true, "Data id : " + id + "deleted successfully"),
            HttpStatus.OK
    );
    }


}

