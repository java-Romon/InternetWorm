package com.lm.MyTop250;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class FilmList001 {

	@ElementList
	List<Film> films =Collections.synchronizedList(new ArrayList<>());

	public FilmList001(List<Film> films) {
		super();
		this.films = films;
	}
}
