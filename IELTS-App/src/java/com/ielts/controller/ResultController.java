/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.controller;

import com.ielts.entity.Candidate;
import com.ielts.entity.Dates;
import com.ielts.facade.CandidateFacade;
import com.ielts.facade.DatesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author awage
 */
@Named(value = "resultController")
@ViewScoped
public class ResultController implements Serializable {

    @EJB
    private CandidateFacade candidateFacade;
    @EJB
    private DatesFacade datesFacade;

    private String testDate;
    private String birthDate;
    private String docNum;
    private String candidateNum;

    private List<Dates> ieltsDates;

    private String noResultMsg;
    private boolean enableResultDiv;

    private Candidate currentCandidate;

    /**
     * Creates a new instance of MainController
     */
    public ResultController() {

    }

    @PostConstruct
    public void init() {
        ieltsDates = datesFacade.findAll();

//        noResultMsg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("noResultMsg");
    }

    public void showResult() {

        currentCandidate = candidateFacade.getCandidate(testDate, birthDate, docNum, candidateNum);
        if (currentCandidate != null
                && currentCandidate.getListening() != null && currentCandidate.getSpeaking() != null
                && currentCandidate.getReading() != null && currentCandidate.getWriting() != null
                && currentCandidate.getOverall() != null) {
            noResultMsg = null;
            enableResultDiv = true;
        } else {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("noResultMsg", "No Results Found");
//                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/main.xhtml");
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
            noResultMsg = "No Results Found";
            enableResultDiv = false;
        }
    }

    // -------------------------- Getters and Setters --------------------------
    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getCandidateNum() {
        return candidateNum;
    }

    public void setCandidateNum(String candidateNum) {
        this.candidateNum = candidateNum;
    }

    public List<Dates> getIeltsDates() {
        return ieltsDates;
    }

    public void setIeltsDates(List<Dates> ieltsDates) {
        this.ieltsDates = ieltsDates;
    }

    // Getter fro noResultMsg
    public String getNoResultMsg() {
        return noResultMsg;
    }

    public boolean isEnableResultDiv() {
        return enableResultDiv;
    }

    public void setEnableResultDiv(boolean enableResultDiv) {
        this.enableResultDiv = enableResultDiv;
    }

    public Candidate getCurrentCandidate() {
        return currentCandidate;
    }

    public void setCurrentCandidate(Candidate currentCandidate) {
        this.currentCandidate = currentCandidate;
    }

}
