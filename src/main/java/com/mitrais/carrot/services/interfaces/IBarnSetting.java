/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitrais.carrot.services.interfaces;

import com.mitrais.carrot.models.BarnSetting;

/**
 *
 * @author Deta_M557
 */
public interface IBarnSetting {
    public Iterable<BarnSetting> findAll();
        
	public BarnSetting findById(Integer id);

	public BarnSetting save(BarnSetting barnSetting);

	public BarnSetting update(BarnSetting barnSetting);

	public BarnSetting delete(Integer id);
}
