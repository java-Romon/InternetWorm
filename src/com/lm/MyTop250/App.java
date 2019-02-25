package com.lm.MyTop250;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class App {

	public static void main(String[] args) {

		String s1 = "https://movie.douban.com/top250?start=";
		String s2 = "&filter=";
		String url;
		List<com.lm.MyTop250.Film> filmlist = Collections.synchronizedList(new ArrayList<>());

		ExecutorService pool = Executors.newFixedThreadPool(4);

		for (int i = 0; i <= 225; i += 25) {
			url = s1 + i + s2;
			pool.execute(new Spider(url, filmlist));
		}
		pool.shutdown();
		while (true) {
			if (pool.isTerminated()) {
				// 将电影图片下载到文件里
				write(filmlist);
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}
		System.out.println("over");
		// System.out.println(filmlist);
		// 将电影对象写入文件
		toTxt(filmlist);

		//生成.xml文件
		toXml(filmlist);

	}

	private static void toXml(List<com.lm.MyTop250.Film> filmlist) {
		Serializer serializer = new Persister();
		File file = new File("film.xml");
		FilmList001 films = new FilmList001(filmlist);

		try {
			serializer.write(films, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void toTxt(List<com.lm.MyTop250.Film> filmlist) {
		try (FileWriter out = new FileWriter("lm.txt")) {
			for (Film f : filmlist) {
				out.write(String.valueOf(f));
				out.write("\n\n");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void write(List<Film> filmlist) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Collections.sort(filmlist);
		for (Film film : filmlist) {
			pool.execute(new Imgloader(film));
		}
		pool.shutdown();

	}
}
