package com.wzk.qa.pojo;/*
 @author 吴梓康
 @create 2019/3/16
*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_pl")
public class Pl implements Serializable {
    @Id
    private String problemid;
    @Id
    private String labelid;

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    @Override
    public String toString() {
        return "Pl{" +
                "problemid='" + problemid + '\'' +
                ", labelid='" + labelid + '\'' +
                '}';
    }
}