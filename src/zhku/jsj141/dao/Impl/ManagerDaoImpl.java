package zhku.jsj141.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
public class ManagerDaoImpl implements ManagerDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectAllU(){
		List<User> list = null;
		list = (List<User>) hibernateTemplate.find("from User");
		System.out.println("----ManagerDao----");
		for (User user : list) {
			System.out.println(user.toString());
		}
		return list;
	}
	@Override
	public List<Book> selectAllB(){
		List<Book> list = null;
		list = (List<Book>) hibernateTemplate.find("from Book");
		System.out.println("------ManagerDao");
		for (Book book : list) {
			System.out.println(book.toString());
		}
		return list;
	}
}