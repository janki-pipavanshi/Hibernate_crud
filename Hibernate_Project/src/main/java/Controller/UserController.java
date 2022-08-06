package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.userDao;
import model.userModel;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action=request.getParameter("action");
		if(action.equalsIgnoreCase("register"))
		{
			userModel u=new userModel();
			u.setName(request.getParameter("name"));
			u.setAddress(request.getParameter("address"));
			u.setContact(Integer.parseInt(request.getParameter("contact")));
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			userDao.insertUser(u);
			response.sendRedirect("user-login.jsp");
			System.out.println(u);
			
		}
		else if(action.equalsIgnoreCase("login"))
		{
			userModel d=new userModel();
			d.setEmail(request.getParameter("email"));
			d.setPassword(request.getParameter("password"));
			userModel d1=userDao.checkLogin(d);
			if(d1==null)
			{
				request.setAttribute("msg","email or password is incorrect");
				request.getRequestDispatcher("user-login.jsp").forward(request,response);
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("data",d1);
				request.getRequestDispatcher("user-home.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("update"))
		{
			userModel u=new userModel();
			u.setId(Integer.parseInt(request.getParameter("id")));
			u.setName(request.getParameter("name"));
			u.setAddress(request.getParameter("address"));
			u.setContact(Integer.parseInt(request.getParameter("contact")));
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			userDao.updateUser(u);
			response.sendRedirect("user-home.jsp");
			System.out.println(u);
			
		}
	}
	
}
