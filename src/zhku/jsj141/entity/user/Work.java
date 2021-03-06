package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;

import zhku.jsj141.entity.Upload;

public class Work {
	private int wid;// 作品ID           自增
	private String wname;// 作品名（标题）
	private long uploadtime;// 上传日期
	private String description;// 描述
	private String author;// 作者                              外键
	private String path;// 磁盘路径
	private String image;// 书本封面路径
	
	private User user;
	private Set<Favour> favour = new HashSet<Favour>();
	private Set<History> history = new HashSet<History>();
	private Set<Upload> upload = new HashSet<Upload>();
	private Set<ReviewsForReviews> rfr = new HashSet<ReviewsForReviews>();
	private Set<ReviewsForBook> rfb = new HashSet<ReviewsForBook>();
	public Set<ReviewsForBook> getRfb() {
		return rfb;
	}
	public void setRfb(Set<ReviewsForBook> rfb) {
		this.rfb = rfb;
	}
	public Set<ReviewsForReviews> getRfr() {
		return rfr;
	}
	public void setRfr(Set<ReviewsForReviews> rfr) {
		this.rfr = rfr;
	}
	public Set<Upload> getUpload() {
		return upload;
	}
	public void setUpload(Set<Upload> upload) {
		this.upload = upload;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Favour> getFavour() {
		return favour;
	}
	public void setFavour(Set<Favour> favour) {
		this.favour = favour;
	}
	public Set<History> getHistory() {
		return history;
	}
	public void setHistory(Set<History> history) {
		this.history = history;
	}
	
	
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public long getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(long uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Work( String wname, long uploadtime, String description,
			String author,String path,String image) {
		super();
		this.wname = wname;
		this.uploadtime = uploadtime;
		this.description = description;
		this.author = author;
		this.path = path;
		this.image = image;
	}
	public Work() {
	}
	@Override
	public String toString() {
		return "Work [wid=" + wid + ", wname=" + wname + ", uploadtime="
				+ uploadtime + ", description=" + description + ", author="
				+ author + ", path=" + path + ", image=" + image + "]";
	}
}
