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
import wob.work.util.CheckInfo;
import wob.work.util.RsponseConstant;


public class UserHandleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Customer c = new Customer();
		ModelRequest mr = new ModelRequest(Customer.class);
		
		//get the request type
		String type=request.getParameter("type");
		
		if(type.equals("login")){
			//check the username and passwd
			out.println("login");
			String userName=request.getParameter("textfield");
			String userPwd=request.getParameter("password2");
			c = new Customer();
			c.setId(userName);
			c.setPassword(userPwd);
			mr = new ModelRequest(Customer.class);
			if(mr.check(c)==RsponseConstant.RIGHT){
				request.getRequestDispatcher("/jsp/userHomepage.jsp").forward(request, response);
			}else{
				
				request.setAttribute("isRegisterRight", "账号密码不匹配");
				request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
			}
				
			/*if(ubo.login(userName, userPwd)){
				UserBean ub=ubo.getUserInfo(userName);
				request.getSession().setAttribute("userInfo", ub);
				request.getRequestDispatcher("GoodsHandleServlet?type=showByPage").forward(request, response);
			}else{
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			}*/
		}else if(type.equals("register")){
			String userName=request.getParameter("userName");
			String userPwd=request.getParameter("userPwd");
			String pwdConfirm=request.getParameter("pwdConfirm");
			String userPhone=request.getParameter("userPhone");
			String userAccount=request.getParameter("userAccount");
			
			if(!userPwd.equals(pwdConfirm)){
				out.print(RsponseConstant.PASSWDISNOTEQUAL);
				out.flush();
				return;
			}
			if(!CheckInfo.isEmpty(userAccount)){
				out.print(RsponseConstant.ACCOUNTISNULL);
				out.flush();
				return;
			}
			c.setId(userName);
			c.setPassword(userPwd);
			c.setPhone(userPhone);
			Account account =new Account();
			account.setId(userAccount);
			int accountAdd=new ModelRequest(Account.class).add(account);
			c.setAccount(account);
			int isRight = mr.add(c);
			
			if(RsponseConstant.RIGHT==isRight){
				request.getRequestDispatcher("/jsp/userHomepage.jsp").forward(request, response);
			} else{
				request.setAttribute("isRegisterRight", "注册失败,账号可能已存在");
				request.setAttribute("userName", userName);
				request.setAttribute("userPwd", userPwd);
				request.setAttribute("pwdConfirm", pwdConfirm);
				request.setAttribute("userPhone", userPhone);
				request.setAttribute("userAccount", userAccount);
				System.out.println("register fail");
				request.setAttribute("isRegisterRight", "注册失败,账号可能已存在");
				request.getRequestDispatcher("/jsp/userRegister.jsp").forward(request, response);
			}
			/*//c.setAccount(userAccount);
			if(ubbBeanBO.register(userName, userPwd, userTrueName, userPhone, userAddress, userEmail)){
				request.getRequestDispatcher("/homepage.jsp").forward(request, response);
			}else{
				request.setAttribute("flag", "failed");
				request.getRequestDispatcher("/userRegister.jsp").forward(request, response);
			}*/
		}else if(type.equals("change")){
			/*System.out.println("come in");
			String userName=request.getParameter("userName");
			System.out.println("chheckUserName:"+userName);
			UserBeanBO ubbBeanBO=new UserBeanBO();
			int result=ubbBeanBO.checkUserName(userName);
			System.out.println("result="+result);
			out.print(result);*/
			
			String oldUserName=request.getParameter("oldUserName");
			String oldUserPwd=request.getParameter("oldUserPwd");
			String userPwd=request.getParameter("userPwd");
			String pwdConfirm=request.getParameter("pwdConfirm");
			String userPhone=request.getParameter("userPhone");
			String userAccount=request.getParameter("userAccount");
			request.setAttribute("oldUserName", oldUserName);
			request.setAttribute("oldUserPwd", oldUserPwd);
			request.setAttribute("userPwd", userPwd);
			request.setAttribute("pwdConfirm", pwdConfirm);
			request.setAttribute("userPhone", userPhone);
			request.setAttribute("userAccount", userAccount);
			c.setId(oldUserName);
			c.setPassword(oldUserPwd);
			if(mr.check(c)==RsponseConstant.RIGHT){
				
				Customer cus = (Customer) mr.get(c);
				if(!userPwd.isEmpty()){
					cus.setPassword(userPwd);
				}
				if(!userPhone.isEmpty()){
					cus.setPassword(userPwd);
				}
				if(!userAccount.isEmpty()){
					Account acc = new Account();
					acc.setId(userAccount);
					int accountAdd=new ModelRequest(Account.class).add(acc);
					c.setAccount(acc);
				}
				if(mr.update(cus)){
					request.getRequestDispatcher("/jsp/userHomepageRight.jsp").forward(request, response);
				}else{
					;
					request.setAttribute("isRegisterRight", "更改失败,数据库异常");
					request.getRequestDispatcher("/jsp/changeUserInfo.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("isRegisterRight", "更改失败,账号密码不匹配");
				request.getRequestDispatcher("/jsp/changeUserInfo.jsp").forward(request, response);
				//request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
			}
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
