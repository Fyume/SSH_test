package zhku.jsj141.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.User;

public class UserDaoImpl {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public Serializable add(User user){
		Serializable s = hibernateTemplate.save(user);
		return s;
	}
	public List<User> select(User user){
		List<User> list= (List<User>) hibernateTemplate.find("from User where uid=?",user.getUid());
		for (User user2 : list) {
			System.out.println("uid:"+user2.getUid()+" username:"+user2.getUsername());
		}
		return list;
	}
}
