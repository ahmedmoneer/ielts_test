/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author awage
 */
public class Utils {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd MMMM yyyy");

    public static Date getToDayDate() {
        return new Date();
    }

    public static List listQuery(String query, List list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        Query q = new Query();
        QueryResults resultList;
        try {
            q.parse(query);
            resultList = q.execute(list);
            if (resultList != null && resultList.getResults() != null) {
                return resultList.getResults();
            } else {
                return new ArrayList();

            }
        } catch (QueryParseException | QueryExecutionException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList();
        }
    }

    public static void showMsg(String msg, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(severity);
        message.setSummary(msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void getLastPagination(String datatableId) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(datatableId);
        dataTable.setFirst(dataTable.getRowCount());
    }

    public static void resetDatatableFilter(String id) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        if (dataTable != null) {
            dataTable.reset();
        }
    }

    public static void resetDatatable(String datatableId) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(datatableId);
        if (dataTable != null) {
            dataTable.setSortOrder("ascending");  // reset sortOrder
            dataTable.setFirst(0);                // reset page
            dataTable.setFilteredValue(null);     // reset filter
            dataTable.setFilters(null);
            dataTable.setSortBy(null);
            dataTable.setValueExpression("filterValue", null);
            dataTable.setValueExpression("filterBy", null);
            dataTable.setValueExpression("sortBy", null);
        } else {
            System.out.println("failed to find datatable");
        }
    }

}
