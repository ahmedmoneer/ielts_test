/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ielts.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author awage
 */
@Entity
@Table(name = "candidate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c")
    , @NamedQuery(name = "Candidate.findById", query = "SELECT c FROM Candidate c WHERE c.id = :id")
    , @NamedQuery(name = "Candidate.findByCandiateName", query = "SELECT c FROM Candidate c WHERE c.candiateName = :candiateName")
    , @NamedQuery(name = "Candidate.findByCandidateNum", query = "SELECT c FROM Candidate c WHERE c.candidateNum = :candidateNum")
    , @NamedQuery(name = "Candidate.findByTestDate", query = "SELECT c FROM Candidate c WHERE c.testDate = :testDate")
    , @NamedQuery(name = "Candidate.findByBirthDate", query = "SELECT c FROM Candidate c WHERE c.birthDate = :birthDate")
    , @NamedQuery(name = "Candidate.findByDocNum", query = "SELECT c FROM Candidate c WHERE c.docNum = :docNum")
    , @NamedQuery(name = "Candidate.findByListening", query = "SELECT c FROM Candidate c WHERE c.listening = :listening")
    , @NamedQuery(name = "Candidate.findBySpeaking", query = "SELECT c FROM Candidate c WHERE c.speaking = :speaking")
    , @NamedQuery(name = "Candidate.findByReading", query = "SELECT c FROM Candidate c WHERE c.reading = :reading")
    , @NamedQuery(name = "Candidate.findByWriting", query = "SELECT c FROM Candidate c WHERE c.writing = :writing")
    , @NamedQuery(name = "Candidate.findByOverall", query = "SELECT c FROM Candidate c WHERE c.overall = :overall")})
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CANDIATE_NAME")
    private String candiateName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CANDIDATE_NUM")
    private String candidateNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TEST_DATE")
    private String testDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BIRTH_DATE")
    private String birthDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DOC_NUM")
    private String docNum;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "LISTENING")
    private String listening;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "SPEAKING")
    private String speaking;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "READING")
    private String reading;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "WRITING")
    private String writing;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "OVERALL")
    private String overall;

    public Candidate() {

    }

    public Candidate(Integer id) {
        this.id = id;
    }

    public Candidate(Integer id, String candiateName, String candidateNum, String testDate, String birthDate, String docNum, String listening, String speaking, String reading, String writing, String overall) {
        this.id = id;
        this.candiateName = candiateName;
        this.candidateNum = candidateNum;
        this.testDate = testDate;
        this.birthDate = birthDate;
        this.docNum = docNum;
        this.listening = listening;
        this.speaking = speaking;
        this.reading = reading;
        this.writing = writing;
        this.overall = overall;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandiateName() {
        return candiateName;
    }

    public void setCandiateName(String candiateName) {
        this.candiateName = candiateName;
    }

    public String getCandidateNum() {
        return candidateNum;
    }

    public void setCandidateNum(String candidateNum) {
        this.candidateNum = candidateNum;
    }

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

    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ielts.entity.Candidate[ id=" + id + " ]";
    }

}
