package com.ivyinfo.test.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftpclient.userManage.FtpUserManage;
import com.ivyinfo.framework.service.base.BaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.test.bean.TestBean;
import com.ivyinfo.test.dao.ITestDAO;

public class TestServicesImpl extends BaseService implements ITestServices {
	

	@SuppressWarnings("unchecked")
	public List getAllTest() throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		return testDAO.findAllTest();
	}

	public void removeTest(int id) throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		testDAO.deleteTest(id);
	}

	public void saveTest(TestBean testBean) throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		testDAO.saveTest(testBean);
	}

	public void updateTest(TestBean testBean) throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		testDAO.updateTest(testBean);
	}

	public TestBean getTestById(int id) throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		return (TestBean)testDAO.findTestById(id);
	}
	
	public List getTestByPage(int from, int len) throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		System.err.println("=======into getTestByPage==========");
		System.err.println("======testDAO:"+testDAO);
		return testDAO.findTestByPage(from, len);
	}
	
	public int count() throws RuntimeException {
		ITestDAO testDAO =(ITestDAO) SpringContextUtil.getBean("testDAO");
		// TODO Auto-generated method stub
		return testDAO.count();
	}

//	public ITestDAO getTestDAO() {
//		return testDAO;
//	}
//
//	public void setTestDAO(ITestDAO testDAO) {
//		this.testDAO = testDAO;
//	}


//	public void getTestTree(Map session) throws RuntimeException {
//		// TODO Auto-generated method stub
//		session.remove("tree.model");
//		if(session.get("tree.model") == null){
//
//	        //creating a simple tree model instance
//	        ITree tree = new Tree();
//	        tree.setSingleSelectionMode(true);
//	        
//	        ITreeNode root = new TreeNode("idRoot" , "BookInfo" , "TypeRoot");
//	        
//	        ITreeNode cateA = new TreeNode("idA" , "Category A" , "TypeA");
//	        ITreeNode cateB = new TreeNode("idB" , "Category B" , "TypeB");
//	        
//	        root.addChild(cateA);
//	        root.addChild(cateB);
//	        
//	        List beanList = testDAO.findAllTest();
//	        
//	        String id="";
//	        String name="";
//	        String type="";
//	        TestBean tb = null;
//	        for(int i=0;i<beanList.size();i++){
//	        	
//	        	tb = (TestBean)beanList.get(i);
//	        	id = tb.getId();
//	        	name = tb.getTitle();
//	        	
//	        	if(i % 2 == 0){
//	        		type = "A";
//	        		cateA.addChild(new TreeNode(id,name,type));
//	        	}
//	        	else{
//	        		type = "B";
//	        		cateB.addChild(new TreeNode(id,name,type));
//	        	}
//	        		
//	        }
//	        
//	        TreeSorter.sortRecursiveByName(root);
//	        tree.setRoot(root);
//	        
//	        session.put("tree.model", tree);
//	        
//	/*        
//	        //adding tree nodes in a tree structure to the tree model instance.
//	        ITreeNode   movies        = new TreeNode("moviesId"     , "Movies"       , "movies");
//	        ITreeNode   thrillers     = new TreeNode("thrillersId"  , "Thrillers"    , "thrillers");
//	        ITreeNode   thrillersNew  = new TreeNode("newThrillId"  , "New Thrillers", "newThrill");
//	        ITreeNode   thrillersOld  = new TreeNode("oldThrillId"  , "Old Thrillers", "oldThrill");
//	        ITreeNode   fantasy       = new TreeNode("fantasyId"    , "Fantasy"      , "fantasy");
//	        ITreeNode   comedy        = new TreeNode("comedyId"     , "Comedies"     , "comedies");
//	        ITreeNode   cartoons      = new TreeNode("cartoonsId"   , "Cartoons"     , "cartoons");
//	        ITreeNode   comedySub     = new TreeNode("comedySubId"    , "Comedy Sub"   , "comedies");
//	        ITreeNode   comedySubSub  = new TreeNode("comedySubSubId" , "Comedy Sub Sub","comedies");
//
//	        ITreeNode   basicInstinct = new TreeNode("basicInstinctId"  , "Basic Instinct"   , "thriller");
//	        ITreeNode   theFirm       = new TreeNode("theFirmId"        , "The Firm"         , "thriller");
//	        ITreeNode   lordOfTheRings= new TreeNode("lordOfTheRingsId" , "Lord of The Rings", "fantasy");
//	        ITreeNode   dumbNDumber   = new TreeNode("dumbNDumberId"    , "Dumb'n Dumber"    , "comedy");
//	        ITreeNode   lionKing      = new TreeNode("lionKingId"       , "Lion King"        , "cartoon");
//	        ITreeNode   snowWhite     = new TreeNode("snowWhiteId"      , "Snow White"       , "cartoon");
//
//	        movies.addChild(thrillers);
//	        movies.addChild(fantasy);
//	        movies.addChild(comedy);
//	        movies.addChild(cartoons);
//
//	        thrillers.addChild(thrillersNew);
//	        thrillers.addChild(thrillersOld);
//	        thrillersNew.addChild(theFirm);
//	        thrillersOld.addChild(basicInstinct);
//	        fantasy  .addChild(lordOfTheRings);
//	        cartoons .addChild(snowWhite);
//	        cartoons .addChild(lionKing);
//	        //comedy   .addChild(dumbNDumber);
//	        comedy   .addChild(comedySub);
//	        comedySub.addChild(comedySubSub);
//
//	        TreeSorter.sortRecursiveByName(movies);
//	        tree.setRoot(movies);
////	        tree.expand(movies.getId());
////	        tree.expand(cartoons.getId());
////
////	        tree.select(fantasy.getId());
//
//
//	        //storing the tree model instance in the session.
//	        //this is where the tags get the tree model from later.
//	        session.put("tree.model", tree);
////	        request.setAttribute("example", tree);
//	    */
//	    }
//	    
//	    
//	}
	
	
	public static void main(String[] args) throws Exception{
	
	//使用默认的conf/userManage.properties配置文件进行配置
//	FtpUserManage manager = new FtpUserManage();
	
	//使用其他配置文件进行配置
	FtpUserManage manager = new FtpUserManage();

	Map params = new HashMap();
	params.put(manager.USER_NAME, "mzq");
	params.put(manager.PASSWORD, "mzq");//修改用户密码
	
	boolean re;
//	re = manager.createNewUser(params);
	re = manager.modifyUser(params);
//	re = manager.deleteUser("mzq");
	
	if(re == true)
		System.out.println("SUCCESSFUL!!");
	else
		System.out.println("ERROR..");
	
	
}


}
