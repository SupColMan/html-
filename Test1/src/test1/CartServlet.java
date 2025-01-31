package test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		List<Book> cart=null;
		//变量purFlag标记用户是否买过商品
		boolean purFlag=true;
		//获得用户的session
		HttpSession session =request.getSession(false);
		//如果session为null则将变量为false
		if(session==null) {
			purFlag=false;
		}else {
			//获得用户的购物车
			cart=(List)session.getAttribute("cart");
			if(cart==null) {
				purFlag=false;
			}
		}
		if(!purFlag) {
			out.write("对不起您没有购买任何商品");
		}else {
			out.write("您购买的书有：");
			double price=0;
			for(Book book:cart) {
				out.write(book.getName()+"<br />");
			}
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
