package br.com.fatec.oqfazer.api.entity;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

public class Event {
	
	public static final String TABLE = "EVENT";
	public static final String COL_ID = "EVN_ID";
	public static final String COL_NAME = "EVN_NAME";
	public static final String COL_DESCRIPTION = "EVN_DESCRIPTION";
	public static final String COL_REGISTRATION_DATE = "EVN_REGISTRATION_DATE";
	public static final String COL_EVENT_DATE = "EVN_DATE";
	public static final String COL_LOCAL = "EVN_LOCAL";
	public static final String COL_IMAGEURL = "EVN_IMAGE_URL";
	public static final String COL_REGION_ID = "EVN_REGION_ID";
	public static final String COL_USER_ID = "EVN_USER_ID";
	public static final String COL_CATEGORY_ID = "EVN_CATEGORY_ID";
		
	private Long id;
	private String name;
	private String description;
	private Date registration_date;
	private Date event_date;
	private String local;
	private String imageURL;
	private Region region;
	private User owner;
	private Category category;
	
	public Event(){};
	
	public Event(Long id, String name, String description, Date registration_date, Date event_date, String local,
			String imageURL, Region region, User owner, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.registration_date = registration_date;
		this.event_date = event_date;
		this.local = local;
		this.imageURL = imageURL;
		this.region = region;
		this.owner = owner;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public static List<String> getColumns() {
		return Lists.newArrayList(COL_ID, COL_NAME, COL_DESCRIPTION, COL_DESCRIPTION, COL_EVENT_DATE, COL_LOCAL, COL_IMAGEURL, COL_REGION_ID, COL_USER_ID, COL_CATEGORY_ID);
	}

	public static String[] getColumnsArray() {
		return new String[] {COL_ID, COL_NAME, COL_DESCRIPTION, COL_DESCRIPTION, COL_EVENT_DATE, COL_LOCAL, COL_IMAGEURL, COL_REGION_ID, COL_USER_ID, COL_CATEGORY_ID };
	}
	
}
