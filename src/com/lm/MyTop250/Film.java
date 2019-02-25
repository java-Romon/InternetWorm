package com.lm.MyTop250;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


public class Film implements Comparable<Film>{

	//ID
	@Attribute
	int id;
	//名称
	@Element
	String title;
	//演员
	@Element
	String actors;
	///描述
	@Element
	String decr;
	//评分-星级
	@Element
	String star;
	//图片文件
	@Element
	String path;
	
	public Film() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getDecr() {
		return decr;
	}
	public void setDecr(String decr) {
		this.decr = decr;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Film id=" + id + "\n\t title=" + title + "\n\t actors=" + actors + "\n\t star=" + star + "\n\t decr=" + decr
				+ "\n\t path=" + path + "";
	}
	@Override
	public int compareTo(Film o) {
		return id - o.id;
		
	}
	
	
	
}
