package com.xzb.oa.workdiary.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.util.idgenerator.IDHelper;
import org.eredlab.g4.arm.util.idgenerator.IdGenerator;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.bmf.base.BaseServiceImpl;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.ftp.FtpHelper;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Utils;

import com.xzb.oa.workdiary.service.WService;


/**
 *知识结构模型业务实现类
 * 
 * @since 2010-01-13
 */
public class WServiceImpl extends BaseServiceImpl implements WService {

	/**
	 * 获取用户信息
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto getUserInfo(Dto pDto) {
		Dto outDto = new BaseDto();
		pDto.put("lock", ArmConstants.LOCK_N);
		pDto.put("enabled", ArmConstants.ENABLED_Y);
		UserInfoVo userInfo = (UserInfoVo) g4Dao.queryForObject("Organization.getUserInfo", pDto);
		outDto.put("userInfo", userInfo);
		return outDto;
	}

	/**
	 * 查询部门信息生成部门树
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto queryWorkdiaryItems(Dto pDto) {
		Dto outDto = new BaseDto();
		List workdiaryList = g4Dao.queryForList("W.queryWorkdiarysByDto", pDto);
		outDto.put("workdiaryList", workdiaryList);
		return outDto;
	}

	/**
	 * 保存部门
	 * 
	 * @param pDto
	 * @return
	 */
	public synchronized Dto saveDeptItem(Dto pDto) {
		String deptid = IdGenerator.getDeptIdGenerator(pDto.getAsString("parentid"));
		pDto.put("deptid", deptid);
		pDto.put("leaf", ArmConstants.LEAF_Y);
		// MYSQL下int类型字段不能插入空字符
		pDto.put("sortno",
				G4Utils.isEmpty(pDto.getAsString("sortno")) ? Integer.valueOf("0") : pDto.getAsString("sortno"));
		pDto.put("enabled", ArmConstants.ENABLED_Y);
		g4Dao.insert("Organization.saveDeptItem", pDto);
		Dto updateDto = new BaseDto();
		updateDto.put("deptid", pDto.getAsString("parentid"));
		updateDto.put("leaf", ArmConstants.LEAF_N);
		g4Dao.update("Organization.updateLeafFieldInEaDept", updateDto);
		return null;
	}

	/**
	 * 修改部门
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto updateDeptItem(Dto pDto) {
		if (G4Utils.isEmpty(pDto.getAsString("sortno"))) {
			pDto.put("sortno", "0");
		}
		if (pDto.getAsString("parentid").equals(pDto.getAsString("parentid_old"))) {
			pDto.remove("parentid");
			g4Dao.update("Organization.updateDeptItem", pDto);
		} else {
			g4Dao.update("Organization.updateEadeptItem", pDto);
			saveDeptItem(pDto);
			pDto.put("parentid", pDto.getAsString("parentid_old"));
			updateLeafOfDeletedParent(pDto);
		}
		return null;
	}

	/**
	 * 调整被删除部门的直系父级部门的Leaf属性
	 * 
	 * @param pDto
	 */
	private void updateLeafOfDeletedParent(Dto pDto) {
		String parentid = pDto.getAsString("parentid");
		pDto.put("deptid", parentid);
		Integer countInteger = (Integer) g4Dao.queryForObject("Organization.prepareChangeLeafOfDeletedParentForEadept", pDto);
		if (countInteger.intValue() == 0) {
			pDto.put("leaf", ArmConstants.LEAF_Y);
		} else {
			pDto.put("leaf", ArmConstants.LEAF_N);
		}
		g4Dao.update("Organization.updateLeafFieldInEaDept", pDto);
	}

	/**
	 * 删除部门项
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteDeptItems(Dto pDto) {
		Dto dto = new BaseDto();
		if (pDto.getAsString("type").equals("1")) {
			// 列表复选删除
			String[] arrChecked = pDto.getAsString("strChecked").split(",");
			for (int i = 0; i < arrChecked.length; i++) {
				dto.put("deptid", arrChecked[i]);
				deleteDept(dto);
			}
		} else {
			// 部门树右键删除
			dto.put("deptid", pDto.getAsString("deptid"));
			deleteDept(dto);
		}
		return null;
	}

	/**
	 * 删除部门 类内部调用
	 * 
	 * @param pDto
	 */
	private void deleteDept(Dto pDto) {
		Dto changeLeafDto = new BaseDto();
		Dto tempDto = (BaseDto) g4Dao.queryForObject("Organization.queryDeptItemsByDto1", pDto);
		if (G4Utils.isNotEmpty(tempDto)) {
			changeLeafDto.put("parentid", tempDto.getAsString("parentid"));
		}
		g4Dao.delete("Organization.deleteEaroleAuthorizeInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEaroleInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEauserauthorizeInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEauserauthorizeInDeptManage2", pDto);
		g4Dao.delete("Organization.deleteEausermenumapInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEausersubinfoInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEausermenumapInDeptManage", pDto);
		g4Dao.delete("Organization.deleteEarolemenumapInDeptManage", pDto);
		g4Dao.update("Organization.updateEauserInDeptManage", pDto);
		g4Dao.update("Organization.updateEadeptItem", pDto);
		if (G4Utils.isNotEmpty(tempDto)) {
			updateLeafOfDeletedParent(changeLeafDto);
		}
	}

	/**
	 * 根据用户所属部门编号查询部门对象<br>
	 * 用于构造组织机构树的根节点
	 * 
	 * @param
	 * @return
	 */
	public Dto queryDeptinfoByDeptid(Dto pDto) {
		Dto outDto = new BaseDto();
		outDto.putAll((BaseDto) g4Dao.queryForObject("W.queryDeptinfoByDeptid", pDto));
		outDto.put("success", new Boolean(true));
		return outDto;
	}

	/**
	 * 保存用户主题信息
	 * 
	 * @param pDto
	 */
	public Dto saveUserTheme(Dto pDto) {
		Dto outDto = new BaseDto();
		g4Dao.update("Organization.saveUserTheme", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
	}
	/**
	 * 目录操作开始
	 */
	
	/**
	 * 若没有根目录
	 * 插入根目录数据
	 */
	public Dto initDir(Dto pDto) {
		
		g4Dao.insert("W.initDirItem", pDto);
		
		
		return null;
	}
	
	

	/**
	 * 判断是否存在
	 */
	public Dto queryDirCount(Dto pDto) {
		
		Dto outDto=new BaseDto();
		outDto=(BaseDto)g4Dao.queryForObject("W.countDir", pDto);
		return outDto;
		
	}
	/**
	 * 保存用户目录
	 */
	public Dto saveDirItem(Dto pDto) {

		/**
		 * 判断同级目录下面是否同名
		 */
		String userId=pDto.getAsString("loginuserid");
		String dirName=pDto.getAsString("dirname");
		Dto cDto=new BaseDto();
		cDto=(BaseDto)g4Dao.queryForObject("W.queryDirIsNewName", pDto);
		int count=cDto.getAsInteger("count");
		if(count<1)
		{
			/**
			 * 路径
			 */	
			Dto outD = new BaseDto();
			outD=(BaseDto)g4Dao.queryForObject("W.queryDirParentPath", pDto);
			String filepath=outD.getAsString("filepath");
			String dirId=IDHelper.getK_DIR_ID();
			pDto.put("path", userId+","+dirName);
			pDto.put("dirid", dirId);
			pDto.put("enabled", "1");
			pDto.put("leaf", "1");
			pDto.put("dirtype", "1");//1代表业务目录，0代表系统目录
			
			if(G4Utils.isEmpty(pDto.getAsString("sortNo")))
			{
				pDto.put("sortNo", "5");//如果没有填写排序，则默认为5
			}
			if(G4Utils.isEmpty(pDto.getAsString("remark")))
			{
				pDto.put("remark", "");
			}
			if(pDto.getAsString("person").equals("0"))
			{
				pDto.put("loginuserid", "");
				pDto.put("dirMold", "01");
			}else{
				pDto.put("dirMold", "02");
			}
			String[] filepaths=filepath.split(",");
			if(filepaths.length==1 &&pDto.getAsString("person").equals("1") )
			{
				pDto.put("filepath", pDto.getAsString("loginuserid")+","+dirId);
			}else
			{
				pDto.put("filepath", filepath+","+dirId);
			}
			Dto outDto = new BaseDto();		
			g4Dao.insert("W.saveDirItem", pDto);
			//createDir(pDto);
			g4Dao.update("W.updateParentDirLeaf", pDto);//将父目录leaf改为0
			outDto.put("success", "1");
			return outDto;
		}else
		{
			Dto kDto=new BaseDto();
			kDto.put("success", "0");
			return kDto;
		}
		
		
		
		
	}

	/**
	 * 修改目录名称
	 */
	public Dto updateDirItem(Dto pDto) {
		Dto outDto = new BaseDto();
		if(G4Utils.isEmpty(pDto.getAsString("modify_sortNo")))
		{
			pDto.put("modify_sortNo", null);
		}
		
		if(G4Utils.isEmpty(pDto.getAsString("modify_remark")))
		{
			pDto.put("modify_remark", null);
		}
		pDto.put("dirId", pDto.getAsString("modifyMenuid"));
		String basefile=pDto.getAsString("baseFile");
		if(G4Utils.isEmpty(pDto.getAsString("parentMenuid_")))
		{
			pDto.put("parentMenuid_", pDto.getAsString("parentMenuid"));
		}
		
		g4Dao.update("W.updateDirItem", pDto);
		/**
		 * 检查更改的父目录leaf,移动目录
		 */
		if(!pDto.getAsString("parentMenuid_").equals(pDto.getAsString("parentMenuid")))
		{
			pDto.put("parentDirId", pDto.getAsString("parentMenuid_"));
			g4Dao.update("W.updateParentDirLeaf", pDto);
		}
		//修改目录的filepath
		Dto outD = new BaseDto();
		outD=(BaseDto)g4Dao.queryForObject("W.queryDirParentPath", pDto);
		if(!G4Utils.isEmpty(outD))
		{
			String filepath=outD.getAsString("filepath");
			String[] filepaths=filepath.split(",");
			if(filepaths.length==1 &&pDto.getAsString("person_").equals("1") )
			{
				pDto.put("filepath", pDto.getAsString("loginuserid")+","+pDto.getAsString("modifyMenuid"));
			}else
			{
				pDto.put("filepath", filepath+","+pDto.getAsString("modifyMenuid"));
			}
			//pDto.put("filepath", filepath+","+pDto.getAsString("modifyMenuid"));
			g4Dao.update("W.updateDirFilePath", pDto);
			
			//对左边文档filepath做修改
			g4Dao.update("W.updateDocFilePath", pDto);
		}
		Dto CountDto=new BaseDto();
		pDto.put("dirId", pDto.getAsString("modifyMenuid"));
		CountDto=(BaseDto)g4Dao.queryForObject("W.updateDirInfoByDirid", pDto);
		 int count=CountDto.getAsInteger("count");
		 if(count<2)
		 	{
			 	pDto.put("parentDirId", pDto.getAsString("parentMenuid"));
			 	g4Dao.update("W.updateParentDirLeafByDelete", pDto);
			 
		 	}
		/**
		String filepath=outD.getAsString("filepath");
		pDto.put("filepath", filepath+","+pDto.getAsString("modifyMenuid"));
		g4Dao.update("W.updateDirFilePath", pDto);
		
		//对左边文档filepath做修改
		g4Dao.update("W.updateDocFilePath", pDto);
		*/
		
		
		//updateDirName(pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
		
	}

	/**
	 * 删除目录操作
	 */
	public Dto deleteDirItems(Dto pDto) {
		Dto outDto = new BaseDto();
		Dto CountDto = new BaseDto();
		String path=pDto.getAsString("baseFile");
		/**
		 * 判断文件夹下面是否有文档
		 */
		Dto c=(BaseDto) g4Dao.queryForObject("W.deleteDocByDirId", pDto);
		if(c.getAsInteger("count")>0)
		{
			outDto.put("success", "0");
			return outDto;
			
		}
		/**
		 * 判断是否是系统目录
		 */
		pDto.put("docDirId", pDto.getAsString("dirId"));
		Dto t=(BaseDto)g4Dao.queryForObject("W.queryDirItems",pDto);
		if(t.getAsString("dirtype").equals("0"))
		{
			outDto.put("success", "2");
			return outDto;
		}
		
		/**
		 * 先删除子目录，再删除该目录
		 * 步骤：1.查询该目录是否
		 */
		
		g4Dao.delete("W.deleteChildDirItem", pDto);
		/**
		 * 查找该目录的父目录id
		 * 找到父目录id后，判断父目录是否还有子目录
		 * 若没有则将leaf改为1
		 */
		CountDto=(BaseDto)g4Dao.queryForObject("W.queryDirInfoByDirid", pDto);
		 int count=CountDto.getAsInteger("count");
		 if(count<2)
		 	{
			 	
			 	g4Dao.update("W.updateParentDirLeafByDelete", pDto);
			 
		 	}
		g4Dao.delete("W.deleteDirItem", pDto);
		
		/**
		 * 对后台文件操作
		String file=pDto.getAsString("filepath");
		file=path+"\\"+file;
		File delFile=new File(file);
		delFiles(delFile);	
		*/
		outDto.put("success", "1");
		return outDto;
		
	}
	
	public Dto createDir(Dto pDto) {
		
		String path=pDto.getAsString("path");//目录存放路径
		
		String baseFile=pDto.getAsString("baseFile");
		
		String dirPath="";
		
		baseFile=baseFile+"\\"+"uploaddata";
		
		File base=new File(baseFile);
		if(!base.exists())
		{
			base.mkdir();
		}
		
		/**
		 * 用用户的userId建目录
		 * 逻辑：如果没有则新建一个
		 */
		
		String userId=pDto.getAsString("loginuserid");
		baseFile=baseFile+"\\"+userId;
		
		
		/**
		 * 判断当前uploaddata目录是否存在
		 */
		
		File up=new File(baseFile);//用户的id当目录
		if(!up.exists())
		{
			up.mkdir();
		}
		
		String dirname=pDto.getAsString("dirname");//目录名称
		
		baseFile=baseFile+"\\"+dirname;
		
		File o=new File(baseFile);
		if(!o.exists())
		{
			o.mkdir();
		}
		/**
		String[] p=path.split(",");
		
		
		for(int i=2;i<p.length;i++)
		{
			Dto indto=new BaseDto();
			indto.put("dirId", p[i]);
			indto=(BaseDto)g4Dao.queryForObject("W.queryDirnameByPath", indto);
			String name=indto.getAsString("dirname");
			
			
			baseFile=baseFile+"\\"+name;
			if(dirPath.equals(""))
			{
				dirPath=name;
			}else{
					dirPath=dirPath+","+name;
			}
		}
		
		if(dirPath.equals(""))
		{
			dirPath=dirname;
		}else{
			dirPath=dirPath+","+dirname;
			
		}
		
		pDto.put("baseFile", dirPath);
		try{
			File dirFile=new File(baseFile);
			if(!dirFile.exists())
			dirFile.mkdir();
			g4Dao.update("W.saveDirFilepath", pDto);
		}
		catch(Exception e)
		{
			
		}
		
	*/
		
		
		return null;
	}
	
	/**
	 * 查询目录路径
	 * @param pDto
	 * @return
	 */
	public Dto getDirItems(Dto pDto)
	{
		Dto outDto=new BaseDto();
		outDto=(BaseDto)g4Dao.queryForObject("W.queryDirItems", pDto);
		String path=outDto.getAsString("path");
		String[] paths=path.split(",");//path的结构：第一个字段是以用户id保存的目录，第二个字段是当前目录的名字
		String file="";
		/**
		for(int i=2;i<paths.length;i++)
		{
			Dto indto=new BaseDto();
			String name="";
			if(!paths[i].equals("0"))
			{
				indto.put("dirId", paths[i]);
				indto=(BaseDto)g4Dao.queryForObject("W.queryDirnameByPath", indto);
				name=indto.getAsString("dirname");
			}
			
			
			if(file.equals(""))
			{
				String p1=paths[2];
				if(!p1.equals("0"))
				{
					Dto pdto=new BaseDto();
					pdto.put("dirId", p1);
					pdto=(BaseDto)g4Dao.queryForObject("W.queryDirnameByPath", pdto);
					String n=pdto.getAsString("dirname");				
					file=n;
				}
			}else{
				file=file+"\\"+name;
			}
			
			
		}
		*/
		file="\\"+paths[0]+"\\"+paths[1]+"\\";
		outDto.put("filepath",file);
		
		return outDto;
			
	}
	
	/**
	 * 后台修改文件夹名称
	 */
	public void updateDirName(Dto dto)
	{
		Dto outdto=new BaseDto();
		String oldfile=dto.getAsString("oldfile");
		String basefile=dto.getAsString("baseFile");
		outdto=getDirItems(dto);
		String filepath=outdto.getAsString("filepath");
		String file=basefile+"\\"+filepath;
		if(!G4Utils.isEmpty(oldfile))
		{
			File newFile=new File(file);
			File old=new File(oldfile);
			if(old.exists())
			{
				old.renameTo(newFile);
			}
		}
		
	}
	
	
	
	
	
		/**
		 * 删除后台文件夹操作
		 */
		public void delFiles(File delFile)
		{
			if(delFile.exists())
			{
				if(delFile.isFile())
				{
					delFile.delete();
				}
				else if(delFile.isDirectory())
				{
					
					File files[] = delFile.listFiles(); 
					if(files.length==0)
					{
						delFile.delete();
					}else{
						for(int i=0;i<files.length;i++)
						{
						      this.delFiles(files[i]);
						     
						} 
					}
				}	
			}
			delFile.delete();
		}
	
	
	
	/**
	 * 目录操作结束
	 */
	@Override
	public Dto queryWorkdiarys(Dto pDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dto saveWorkdiary(Dto pDto) {
		String docId=IDHelper.getK_DOC_ID();
		pDto.put("id", docId);
		g4Dao.insert("W.insertWorkdiary", pDto);
		return null;
	}

	@Override
	public Dto updateWorkdiary(Dto pDto) {
		Dto outDto = new BaseDto();
		if(G4Utils.isEmpty(pDto.getAsString("sortNo")))
		{
			pDto.put("sortNo", null);
		}
		g4Dao.update("W.updateWorkdiaryDetail", pDto);
		outDto.put("success", new Boolean(true));
		return outDto;
		
	
	}

	@Override
	public Dto deleteWorkdiarys(Dto pDto) {
		Dto dto = new BaseDto();
		String[] arrChecked = pDto.getAsString("strChecked").split(",");
		for (int i = 0; i < arrChecked.length; i++) {
			dto.put("docId", arrChecked[i]);
			dto.put("enabled", "0");
			//g4Dao.update("W.updateWorkdiary", dto);
			Dto pathDto=new BaseDto();
			pathDto=(BaseDto)g4Dao.queryForObject("W.queryUpLoadPath", dto);
			
			
			try{
				g4Dao.delete("W.deleteDocUpload", dto);
				g4Dao.delete("W.deleteWorkdiary", dto);
			}catch(Exception e)
			{
				
			}
			if(!G4Utils.isEmpty(pathDto))	
			{
				String path=pathDto.getAsString("path");
				File pFile=new File(path);
				delFiles(pFile);
			}
			
		}
		return null;
	}

	@Override
	public Dto queryDocinfoByDocid(Dto pDto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 
	 */
	public Dto saveLimitis(Dto dto) {
		

		if(!G4Utils.isEmpty(dto))
		{
			String limitisType=dto.getAsString("limitisType");
			if(limitisType.equals("1"))//部门
			{
				g4Dao.update("W.saveLimitisDep", dto);
			}else if(limitisType.equals("2"))//人员
			{
				g4Dao.update("W.saveLimitisPerson", dto);
			}
			
		}
		return null;
	}


	public Dto queryLimitis(Dto dto) {
		
		String depType=dto.getAsString("depType");
		String peopleType=dto.getAsString("peopleType");
		Dto outDto = new BaseDto();
		List deptList = g4Dao.queryForList("W.queryLimitis", dto);
		List treeList=new ArrayList();
		
		Dto deptDto = new BaseDto();
		for (int i = 0; i < deptList.size(); i++) {
			deptDto = (BaseDto) deptList.get(i);
			
			
			if(depType.equals("1"))//显示部门修改的树
			{
				String editDep=deptDto.getAsString("editdep");
				if(G4Utils.isEmpty(editDep))
					return null;
				String[] editDepList=editDep.split(",");
				String editDepName=deptDto.getAsString("editdepname");
				String[] editDepNameList=editDepName.split(",");
				
				for(int j=0;j<editDepList.length;j++)
				{
					Dto treeDto = new BaseDto();
					treeDto.put("id", editDepList[j]);
					treeDto.put("text", editDepNameList[j]);
					treeDto.put("leaf", true);
					treeList.add(treeDto);
					
				}
				
			}
			else if(depType.equals("2"))
			{
				String readDep=deptDto.getAsString("readdep");
				if(G4Utils.isEmpty(readDep))
					return null;
				String[] readDepList=readDep.split(",");
				String readDepName=deptDto.getAsString("readdepname");
				String[] readDepNameList=readDepName.split(",");
				
				for(int j=0;j<readDepList.length;j++)
				{
					Dto treeDto = new BaseDto();
					treeDto.put("id", readDepList[j]);
					treeDto.put("text", readDepNameList[j]);
					treeDto.put("leaf", true);
					treeList.add(treeDto);
					
				}
			}else if(peopleType.equals("1")){
				
				String editPerson=deptDto.getAsString("editperson");
				if(G4Utils.isEmpty(editPerson))
					return null;
				String[] editPersonList=editPerson.split(",");
				String editPersonName=deptDto.getAsString("editpersonname");
				String[] editPersonNameList=editPersonName.split(",");
				
				for(int j=0;j<editPersonList.length;j++)
				{
					Dto treeDto = new BaseDto();
					treeDto.put("id", editPersonList[j]);
					treeDto.put("text", editPersonNameList[j]);
					treeDto.put("leaf", true);
					treeList.add(treeDto);
					
				}
				
				
			}else if(peopleType.equals("2"))
			{
				String readPerson=deptDto.getAsString("readperson");
				if(G4Utils.isEmpty(readPerson))
					return null;
				String[] readPersonList=readPerson.split(",");
				String readPersonName=deptDto.getAsString("readpersonname");
				String[] readPersonNameList=readPersonName.split(",");
				
				for(int j=0;j<readPersonList.length;j++)
				{
					Dto treeDto = new BaseDto();
					treeDto.put("id", readPersonList[j]);
					treeDto.put("text", readPersonNameList[j]);
					treeDto.put("leaf", true);
					treeList.add(treeDto);
					
				}
			}
			
		}
		outDto.put("jsonString", JsonHelper.encodeObject2Json(treeList));
		return outDto;
		
	}

	@Override
	public Dto deleteAtt(Dto pDto) {
		
		Dto dDto=new BaseDto();
		Dto outDto=new BaseDto();
		outDto=(BaseDto)g4Dao.queryForObject("W.getAttItems", pDto);
		if(!G4Utils.isEmpty(outDto.getAsString("uploadtype")))
		{
			if(outDto.getAsString("uploadtype").equals("1"))
			{
				Boolean success=false;
				//ftp形式文件
				String dirName=outDto.getAsString("dirname");
				String ftpPath="C:/TEMP/"+dirName;
				FtpHelper ftpHelper = new FtpHelper();
				ftpHelper.createConnection("192.168.18.239", "anonymous", "", 21);
				success=ftpHelper.removeFile(ftpPath);
				if(success)
				{
					dDto.put("success", "success");
				}
				
				
			}else if(outDto.getAsString("uploadtype").equals("0"))
			{
				String path=outDto.getAsString("path");
				int index = path.lastIndexOf("\\"); // 
				String file= path.substring(index-1);
				File pFile=new File(file);
				delFiles(pFile);
			}
		}
		g4Dao.delete("W.deleteAtt", pDto);

		return null;
	}





}
