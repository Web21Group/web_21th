package web.work.conroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.work.infohandle.ModelRequest;
import web.work.model.Account;
import web.work.model.Customer;
import web.work.model.Shop;
import wob.work.util.CheckInfo;
import wob.work.util.RsponseConstant;

public class ShopHandleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//操作类型
		String type=request.getParameter("type");
		
		Shop shop = new Shop();
		
		ModelRequest mr = new ModelRequest(Shop.class);
		
		if(type.equals("login")){
			String shopId = request.getParameter("shopId");
			String shopPwd = request.getParameter("shopPwd");
			shop.setId(shopId);
			shop.setPassword(shopPwd);
			int j=mr.check(shop);
			if(mr.check(shop)==RsponseConstant.RIGHT){
				request.getRequestDispatcher("/jsp/shopHomepage.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/jsp/shopLogin.jsp").forward(request, response);
			}
		}else if(type.equals("register")){
			String shopId = request.getParameter("shopId");
			String shopName = request.getParameter("shopName");
			String shopPwd = request.getParameter("shopPwd");
			String pwdConfirm = request.getParameter("pwdConfirm");
			String shopPhone = request.getParameter("shopPhone");
			String shopAccount = request.getParameter("shopAccount");
			
			if(!shopPwd.equals(pwdConfirm)){
				return;
			}
			if(!CheckInfo.isEmpty(shopAccount)){
				
				out.print(RsponseConstant.ACCOUNTISNULL);
				out.flush();
				return;
			}
			shop.setId(shopId);
			shop.setName(shopName);
			shop.setPassword(shopPwd);
			
			Account account =new Account();
			account.setId(shopAccount);
			int accountAdd=new ModelRequest(Account.class).add(account);
			shop.setAccount(account);
			int isRight = mr.add(shop);
			if(RsponseConstant.RIGHT==isRight){
				request.getRequestDispatcher("/jsp/shopHomepage.jsp").forward(request, response);
			} else{
				System.out.println("register fail");
				request.setAttribute("isRegisterRight", "注册失败,账号可能已存在");
				request.setAttribute("shopId", shopId);
				request.setAttribute("shopName", shopName);
				request.setAttribute("shopName", shopName);
				request.setAttribute("shopPwd", shopPwd);
				request.setAttribute("pwdConfirm", pwdConfirm);
				request.setAttribute("shopPhone", shopPhone);
				request.setAttribute("shopAccount", shopAccount);
				request.getRequestDispatcher("/jsp/shopRegister.jsp").forward(request, response);
			}
		}else if(type.equals("change")){
			String oldShopId = request.getParameter("oldShopId");
			String oldShopPwd = request.getParameter("oldShopPwd");
			String shopName = request.getParameter("shopName");
			String shopPwd = request.getParameter("shopPwd");
			String pwdConfirm = request.getParameter("pwdConfirm");
			String shopPhone = request.getParameter("shopPhone");
			String shopAccount = request.getParameter("shopAccount");
			
			request.setAttribute("oldShopId", oldShopId);
			request.setAttribute("oldShopPwd", oldShopPwd);
			request.setAttribute("shopName", shopName);
			request.setAttribute("shopPwd", shopPwd);
			request.setAttribute("pwdConfirm", pwdConfirm);
			request.setAttribute("shopPhone", shopPhone);
			request.setAttribute("shopAccount", shopAccount);
			shop.setId(oldShopId);
			shop.setPassword(oldShopPwd);
			if(mr.check(shop)==RsponseConstant.RIGHT){
				
				Shop sho = (Shop) mr.get(shop);
				if(!shopName.isEmpty()){
					sho.setPassword(shopName);
				}
				if(!shopPwd.isEmpty()){
					sho.setPassword(shopPwd);
				}
				if(!shopPhone.isEmpty()){
					sho.setPhone(shopPhone);
				}
				if(!shopAccount.isEmpty()){
					Account account =new Account();
					account.setId(shopAccount);
					int accountAdd=new ModelRequest(Account.class).add(account);
					sho.setAccount(account);
				}
				if(mr.update(sho)){
					request.getRequestDispatcher("/jsp/shopHomepageRight.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/jsp/changeShopInfo.jsp").forward(request, response);
				}
				
				
			}else{
				request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
			}
		}	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
