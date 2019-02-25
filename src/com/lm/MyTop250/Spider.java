package com.lm.MyTop250;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable {

	String url;
	List<Film> filmlist = new ArrayList<>();

	/**
	 * 将链接中的电影导入到电影列表中
	 * 
	 * @param url
	 * @param filmlist
	 */
	public Spider(String url, List<Film> filmlist) {
		this.url = url;
		this.filmlist = filmlist;
	}

	@Override
	public void run() {

		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();

			Elements es = doc.select(".grid_view .item");
			for (Element e : es) {
				Film film = new Film();

				String id = e.select("em").text();
				String title = e.select(".title").text();
				String actors = e.select(".bd p").first().text();
				String decr = e.select(".bd .quote").text();
				String star = e.select(".star .rating_num").text();
				String path = e.select(".pic img").attr("src");

				film.setId(Integer.parseInt(id));
				film.setTitle(title);
				film.setActors(actors);
				film.setDecr(decr);
				film.setStar(star);
				film.setPath(path);

				filmlist.add(film);
				
			}
			
//			System.out.println("over");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
