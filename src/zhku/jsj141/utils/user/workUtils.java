package zhku.jsj141.utils.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class workUtils {
	private static String DiskPath = "D:\\SSH_test\\main\\";// 默认存取路径
	private static String FimagePath = "D:\\SSH_test\\images\\user\\workImg";// 图片最终存放路径
	private static String userPath = "user\\";
	/*private static String DiskPath = "C:\\SSH_test\\main\\";// 默认存取路径
	private static String FimagePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\SSH_test\\images\\user\\workImg";// 图片最终存放路径
	private static String userPath = "user\\";*/
	// 读取用户作品
	public static List<String> readbook_U(String uid, String bpath)
			throws FileNotFoundException, UnsupportedEncodingException {
		String totalpath = DiskPath + userPath + uid;// 每个用户拥有自己的文件夹存放自己的作品,不进行分类了
		File dir = new File(totalpath);
		List<String> list = new LinkedList<String>();
		if (!dir.exists()) {// 若不存在，则创建
			System.out.println("用户 " + uid + "没作品");
		} else {
			File text = new File(dir, bpath);
			if (!text.exists()) {
				System.out.println("没有这个作品");
			} else {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(text),"GBK"));
					String read = null;
					boolean mssyCode = false;
					while ((read = in.readLine()) != null) {
						if(read.indexOf("�")!=-1){//乱码的话(判断的贼简陋。。。)
							mssyCode = true;
							break;
						}
						int line = 50;// 根据页面的显示要求
										// 每50个字符换一行显示(虽然我也觉得这样做挺滑稽的，或许应该交给前端
										// 方便前后端分离？)
						int num = read.length() / line;
						if (num >= 1) {
							String str1 = "";
							String str2 = "";
							for (int i = 1; i <= num; i++) {
								str1 = str1
										+ read.substring(line * (i - 1), line
												* i) + "</h4><h4>";
							}
							str2 = read.substring(num * line, read.length());
							read = str1 + str2;
						}
						read = "　　" + read;
						list.add(read);
					}
					in.close();
					if(mssyCode){
						list.clear();
						BufferedReader in1 = new BufferedReader(
								new InputStreamReader(new FileInputStream(text),"UTF-8"));
						while ((read = in1.readLine()) != null) {
							int line = 50;// 根据页面的显示要求
											// 每50个字符换一行显示(虽然我也觉得这样做挺滑稽的，或许应该交给前端
											// 方便前后端分离？)
							int num = read.length() / line;
							if (num >= 1) {
								String str1 = "";
								String str2 = "";
								for (int i = 1; i <= num; i++) {
									str1 = str1
											+ read.substring(line * (i - 1), line
													* i) + "</h4><h4>";
								}
								str2 = read.substring(num * line, read.length());
								read = str1 + str2;
							}
							read = "　　" + read;
							list.add(read);
						}
						in1.close();
					}
					return list;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	public String adjust(String str){
		
		return str;
	}
	// 用户上传作品(标题不允许重复)
	public static String uploadbook_U(File upload, String uid,
			String uploadContentType, String wname) {
		String totalpath = DiskPath + userPath + uid + "\\" + wname;// 每个用户拥有自己的文件夹存放自己的作品,不进行分类了
		File dir = new File(totalpath);
		String ctype = null;// 文件后缀
		if (upload != null) {
			if (uploadContentType.equals("text/plain")) {
				ctype = ".txt";
			} else if (uploadContentType.equals("application/msword")) {
				ctype = ".doc";
			} else if (uploadContentType
					.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				ctype = ".docx";
			}
			if (ctype != null) {
				if (!dir.exists()) {// 若文件夹不存在，则创建
					dir.mkdir();

					try {
						String time = "" + System.currentTimeMillis();
						time = time
								.substring(time.length() - 10, time.length());// 保留后10位的时间戳
						FileUtils.moveFile(upload, new File(dir, uid + time
								+ ctype));// 存放到磁盘的时候重新命名，以免重复
						return wname + "\\" + uid + time + ctype;// 返回部分路径名
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					return "dirfalse";//作品已存在
				}
			} else {
				return "typefalse";// 文件类型不符
			}
		}
		return "";
	}

	// 用户上传作品的封面
	public static String uploadbookI_U(File image, String uid,
			String uploadContentType) {
		File dir = new File(FimagePath);
		String ctype = null;// 文件后缀
		if (image != null) {
			if (uploadContentType.equals("image/jpeg")) {
				ctype = ".jpg";
			}
			if (ctype != null) {
				try {
					String time = "" + System.currentTimeMillis();
					time = time.substring(time.length() - 10, time.length());// 保留后10位的时间戳
					FileUtils.moveFile(image, new File(dir, uid + time + ctype));// 存放到磁盘的时候重新命名，以免重复
					return   "\\" + uid + time + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return "typefalse";// 文件类型不符
			}
		}
		return "";
	}
	//删除上传的用户作品封面
	public static boolean removeWorkI(String path){//删除磁盘中存放的封面
		try{
			FileUtils.forceDelete(new File(FimagePath+"\\"+path));
			return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	//删除磁盘中的用户某个作品
	public static boolean removeWork(String uid,String wname){
		String totalpath = DiskPath + userPath + uid;
		try{
			FileUtils.forceDelete(new File(totalpath+"\\"+wname));
			return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	//删除磁盘中的用户所有作品
	public static boolean removeWork(String uid){
		String totalpath = DiskPath + userPath + uid;
		try{
			if(new File(totalpath).exists()){//假如存在用户作品文件夹
				FileUtils.forceDelete(new File(totalpath));
			}
			return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}

}
