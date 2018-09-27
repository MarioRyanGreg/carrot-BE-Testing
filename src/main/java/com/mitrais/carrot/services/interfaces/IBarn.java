/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitrais.carrot.services.interfaces;

import com.mitrais.carrot.models.Barn;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Barn Service as Interface for accessing JPA Repository of Barn
 * @author Deta_M557
 */
@ControllerAdvice
public interface IBarn {
    public Iterable<Barn> findAll();
        
	public Barn findById(Integer id);

	public Barn save(Barn barn);

	public Barn update(Barn barn);

	public Barn delete(Integer id);
}
