package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;

public class BookDaoImpl implements BookDao{
	private HibernateTemplate hibernateTemplate;
	@Override
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public Serializable add(Book book) {
		Serializable s = hibernateTemplate.save(book);
		return s;
	}
	@Override
	public void update(Book book){
		hibernateTemplate.saveOrUpdate(book);
	}
	@Override
	public List<Book> selectAll(){
		List<Book> list = (List<Book>) hibernateTemplate.find("from Book");
		return list;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> select_indistinct(Book book, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Book> list = null;
		try {
			list = (List<Book>) hibernateTemplate.find("from Book where "
					+ name + " like ?","%"+
					book.getClass().getMethod("get" + name_m)
							.invoke(book)+"%");//反射机制调用方法
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Book> select(Book book, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Book> list = null;
		try {
			list = (List<Book>) hibernateTemplate.find("from Book where "
					+ name + " =?",
					book.getClass().getMethod("get" + name_m)
							.invoke(book));//反射机制调用方法
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> selectT(){
		List<Type> list = (List<Type>) hibernateTemplate.find("from Type");
		return list;
	}
}
