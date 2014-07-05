package web.work.infohandle;

import java.sql.Connection;
import java.util.List;

import web.work.model.Customer;
import web.work.model.Shop;
import wob.work.util.RsponseConstant;
import xidian.edu.db.DBUtil;
import xidian.edu.util.GetSqlConnection;

public class ModelRequest {
	Connection conn;
	DBUtil db;
	List list;
	Class clazz;
	public ModelRequest(Class clazz){
		this.clazz=clazz;
		init();
	}
	private void init(){
		conn = new GetSqlConnection().getConnection();
		db = new DBUtil(conn,clazz);
	}
	public  int check(Object c){
		list = (List)db.findFromId(c);
		if(list.size()==0){
			return RsponseConstant.NORECORD;
		}
		if(c instanceof Customer){
			Customer c2=(Customer)c;
			Customer c1 = (Customer)list.get(0);
			if(c1.getPassword().equals(c2.getPassword())){
				return RsponseConstant.RIGHT;
			}
		}else if(c instanceof Shop){
			Shop c2 = (Shop)c;
			Shop c1 = (Shop)list.get(0);
			System.out.println(c1.getPassword()+">>>>>>>>."+c2.getPassword());
			if(c1.getPassword().equals(c2.getPassword())){
				return RsponseConstant.RIGHT;
			}
		}
		return RsponseConstant.WRONG;
	}
	
	//add an object
	public int add(Object c){
		list = (List)db.findFromId(c);
		if(list.size()!=0){
			return RsponseConstant.ACCOUNTEXIST;
		}
		boolean flag = db.add(c);
		if(flag=true){
			return RsponseConstant.RIGHT;
		}else{
			return RsponseConstant.WRONG;
		}
	}
	
	public boolean update(Object obj){
		 return db.update(obj);
	}
	public Object get(Object obj){
		list = (List)db.findFromId(obj);
		return list.get(0);
	}
	
}
