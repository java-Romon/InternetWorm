package com.lm.MyTop250;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Imgloader implements Runnable{

	Film film;
	public Imgloader(Film film) {
		this.film =film;
	}
	
	@Override
	public void run() {
		File f = new File("lm");
		if(!f.exists()) {
		f.mkdir();
	}
		try {
			getNetworkImage(film.getPath(), "lm",film);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ok");
	}

	public static void getNetworkImage(String path, String outPath,Film film) throws IOException {
		// 输入输出流
		FileOutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedInputStream bis = null;
		Document doument;
		Elements elements;
		try {
		
				// 读取网站所有图片
				String outImage = film.getId()+ ".jpg";
				// 创建连接
				URL imgUrl = new URL(path);
				// 获取输入流
				inputStream = imgUrl.openConnection().getInputStream();
				// 将输入流信息放入缓冲流提升读写速度
				bis = new BufferedInputStream(inputStream);
				// 读取字节娄
				byte[] buf = new byte[1024];
				// 生成文件
				outputStream = new FileOutputStream(new File(outPath , outImage));
				int size = 0;
				// 边读边写
				while ((size = bis.read(buf)) != -1) {
					outputStream.write(buf, 0, size);
				}
				// 刷新文件流
				outputStream.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 释放资源 遵循先开后关原则
			if (outputStream != null)
				outputStream.close();
			if (bis != null)
				bis.close();
			if (inputStream != null)
				inputStream.close();
		}
	}
}
