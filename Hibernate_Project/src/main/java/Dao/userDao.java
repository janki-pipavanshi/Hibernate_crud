package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Connection.DBconnection;
import model.userModel;

public class userDao {

	public static void insertUser(userModel u)
	{
		try {
			SessionFactory sf=DBconnection.createSession();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
			session.save(u);
			tx.commit();
			session.close();
			sf.close();
			
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static userModel checkLogin(userModel u)
	{
		userModel u1=null;
		List<userModel> list=null;
		try
		{
			SessionFactory sf=DBconnection.createSession();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
			Query q=session.createQuery("from userModel u where u.email=:email and u.password=:password");
			q.setParameter("email", u.getEmail());
			q.setParameter("password", u.getPassword());
			list=q.list();
			u1=list.get(0);
			tx.commit();
			session.close();
			sf.close();
		}
		catch (Exception e)
		{
		e.printStackTrace();	
		}
		return u1;
	}
	public static void updateUser(userModel u)
	{
		
		try 
		{
			SessionFactory sf=DBconnection.createSession();
			Session session=sf.openSession();
			Transaction tx=session.beginTransaction();
			Query q=session.createQuery("update userModel u set u.address=:address,u.contact=:contact,u.email=:email,u.name=:name,u.password=:password where u.id=:id");
			q.setParameter("address",u.getAddress());
			q.setParameter("contact", u.getContact());
			q.setParameter("email", u.getEmail());
			q.setParameter("name", u.getName());
			q.setParameter("password", u.getPassword());
			q.setParameter("id", u.getId());
			q.executeUpdate();
			tx.commit();
			session.close();
			sf.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		
		}
	}
}
