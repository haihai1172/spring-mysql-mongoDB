package com.seeenergy.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="v2_data_beopdata_ghkj")
public class M5DataBeopdataGhkj {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Field("value")
    private String value;
    
    @Field("pointname")
    private String pointname;
    
    @Field("time")
    private Date time;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPointname() {
		return pointname;
	}

	public void setPointname(String pointname) {
		this.pointname = pointname;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
    
}
