package zhku.jsj141.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.User;

public interface UserDao {

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate);
	public Serializable add(User user);
}
