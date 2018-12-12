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
import com.ielts.util.Utils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author awage
 */
@Named(value = "adminPanelController")
@ViewScoped
public class AdminPanelController implements Serializable {

    @EJB
    private CandidateFacade candidateFacade;

    @EJB
    private DatesFacade datesFacade;

    private Date candidateBirthDate;
    private Date candidateTestDate;

    private List<Candidate> allCandidates;
    private Candidate selectedCandidate;
    private Candidate currentCandidate;

    private List<Dates> allDates;
    private Dates currentDate;
    private Dates oldDate;

    private boolean addRecord = true;
    private boolean editingRow;

    /**
     * Creates a new instance of CandidateController
     */
    public AdminPanelController() {

    }

    @PostConstruct
    public void init() {

        currentCandidate = new Candidate();
        setCandidateBirthDate(Utils.getToDayDate());
        setCandidateTestDate(Utils.getToDayDate());

        allCandidates = candidateFacade.findAll();
        if (allCandidates == null || allCandidates.isEmpty()) {
            allCandidates = new ArrayList<>();
        }

        allDates = datesFacade.findAll();
        if (allDates == null || allDates.isEmpty()) {
            allDates = new ArrayList<>();
        }

        currentDate = new Dates();

    }

    public void save() {

        currentCandidate.setBirthDate(Utils.SDF.format(getCandidateBirthDate()));
        currentCandidate.setTestDate(Utils.SDF.format(getCandidateTestDate()));

        try {
            if (currentCandidate.getId() == null) {
                // Create Mode
                candidateFacade.create(currentCandidate);
                allCandidates.add(currentCandidate);
            } else {
                // Edit Mode
                candidateFacade.edit(currentCandidate);
            }
            Utils.showMsg("Saved Successfully", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Utils.showMsg("Save Failed", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void delete() {
        try {
            candidateFacade.remove(currentCandidate);

            allCandidates.remove(selectedCandidate);

            currentCandidate = new Candidate();
            setCandidateBirthDate(Utils.getToDayDate());
            setCandidateTestDate(Utils.getToDayDate());

            Utils.showMsg("Deleted Successfully", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Utils.showMsg("Delete Failed", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void refresh() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("adminPanelController");
    }

    public void onCandidateSelect() {
        currentCandidate = selectedCandidate;
        try {
            setCandidateBirthDate(Utils.SDF.parse(currentCandidate.getBirthDate()));
            setCandidateTestDate(Utils.SDF.parse(currentCandidate.getTestDate()));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    public void edit(RowEditEvent event) {

        addRecord = true;
        currentDate = (Dates) event.getObject();

        currentDate.setIdate(Utils.SDF.format(currentDate.getIeltsDate()));

        //Check Duplication
        if (Utils.listQuery("select * from com.ielts.entity.Dates where ieltsDate = '" + currentDate.getIeltsDate() + "'", allDates).size() > 1) {
            Utils.showMsg("Date already exists !!!", FacesMessage.SEVERITY_WARN);
            editingRow = true;
            return;
        }
        editingRow = false;

        try {
            if (currentDate.getId() != null) {
                // -------------- Editing Mode --------------
                datesFacade.edit(currentDate);
            } else {
                // -------------- Creating Mode --------------
                datesFacade.create(currentDate);
            }
            Utils.showMsg("Saved Successfully", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            Utils.showMsg("Save Failed", FacesMessage.SEVERITY_ERROR);
        }

    }

    public void onRowEditInit(Dates obj) {
        if (obj.getId() != null) {
            try {
                oldDate = obj.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("Error In Cloning Obj :" + e.getMessage());
            }
        } else {
            oldDate = null;
        }
    }

    public void onRowEditCancel(Dates obj) {
        addRecord = true;
        editingRow = false;
        if (obj.getId() == null) {
            allDates.remove(obj);
        } else {
            obj.setIeltsDate(oldDate.getIeltsDate());
        }
    }

    public void addRecord() {
        if (addRecord) {
            addRecord = false;
            currentDate = new Dates();
            allDates.add(currentDate);
            Utils.resetDatatableFilter(":secondForm:dt2");
            Utils.getLastPagination(":secondForm:dt2");
        }
    }

    public void delete(Dates d) {
        addRecord = true;
        if (d.getId() != null) {
            try {
                datesFacade.remove(d);
                allDates.remove(d);
                Utils.showMsg("Deleted Successfully", FacesMessage.SEVERITY_INFO);
            } catch (Exception e) {
                Utils.showMsg("Delete Failed", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            allDates.remove(d);
        }
    }

    // -------------------------------------------------------------------------
    // -------------------------- Getters and Setters --------------------------
    public List<Candidate> getAllCandidates() {
        return allCandidates;
    }

    public void setAllCandidates(List<Candidate> allCandidates) {
        this.allCandidates = allCandidates;
    }

    public Candidate getSelectedCandidate() {
        return selectedCandidate;
    }

    public void setSelectedCandidate(Candidate selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
    }

    public Candidate getCurrentCandidate() {
        return currentCandidate;
    }

    public void setCurrentCandidate(Candidate currentCandidate) {
        this.currentCandidate = currentCandidate;
    }

    public Date getCandidateBirthDate() {
        return candidateBirthDate;
    }

    public void setCandidateBirthDate(Date candidateBirthDate) {
        this.candidateBirthDate = candidateBirthDate;
    }

    public Date getCandidateTestDate() {
        return candidateTestDate;
    }

    public void setCandidateTestDate(Date candidateTestDate) {
        this.candidateTestDate = candidateTestDate;
    }

    public List<Dates> getAllDates() {
        return allDates;
    }

    public void setAllDates(List<Dates> allDates) {
        this.allDates = allDates;
    }

    public Dates getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Dates currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isEditingRow() {
        return editingRow;
    }

    public void setEditingRow(boolean editingRow) {
        this.editingRow = editingRow;
    }

    public Date getTodayDate() {
        return new Date();
    }

}
