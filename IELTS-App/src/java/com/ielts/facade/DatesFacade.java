/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.facade;

import com.ielts.entity.Dates;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author awage
 */
@Stateless
public class DatesFacade extends AbstractFacade<Dates> {

    @PersistenceContext(unitName = "IELTS-AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatesFacade() {
        super(Dates.class);
    }

}
