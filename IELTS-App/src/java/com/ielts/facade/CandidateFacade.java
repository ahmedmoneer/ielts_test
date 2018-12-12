/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.facade;

import com.ielts.entity.Candidate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author awage
 */
@Stateless
public class CandidateFacade extends AbstractFacade<Candidate> {

    @PersistenceContext(unitName = "IELTS-AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateFacade() {
        super(Candidate.class);
    }

    public Candidate getCandidate(String testDate, String birthDate, String docNum, String candidateNum) {
        String jpql = "SELECT c FROM Candidate c WHERE c.testDate = :testDate AND"
                + " c.birthDate = :birthDate AND c.docNum = :docNum AND c.candidateNum = :candidateNum";
        List resultList = em.createQuery(jpql)
                .setParameter("testDate", testDate)
                .setParameter("birthDate", birthDate)
                .setParameter("docNum", docNum)
                .setParameter("candidateNum", candidateNum)
                .getResultList();
        return (resultList == null || resultList.isEmpty()) ? null : (Candidate) resultList.get(0);
    }

}
