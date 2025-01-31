package test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		if(id==null) {
			//如果ID为null则重定向到List
			String url="/Test1/ListBookServlet";
			response.sendRedirect(url);
			return;
		}
		Book book=BookDB.getBook(id);
		//创建或者获取用户的session对象
		HttpSession session=request.getSession();
		//从session对象中获取用户的购物车
		List<Book> cart=(List)session.getAttribute("cart");
		if(cart==null) {
			//首次购买，为用户创建一个购物车
			cart=new ArrayList<Book>();
			//将购物车存入session对象
			session.setAttribute("cart", cart);
			//将商品放入购物车
			cart.add(book);
			//创建cookie存放session的标示号
			Cookie cookie=new Cookie("JSESSIONID",session.getId());
			cookie.setMaxAge(60*30);
			cookie.setPath("/Test1");
			response.addCookie(cookie);
			String url="/Test1/CartServlet";
			response.sendRedirect(url);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
