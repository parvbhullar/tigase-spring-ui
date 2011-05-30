package com.ivyinfo.framework.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.test.bean.TestBean;

public class BaseDao<T extends BaseBean> extends SqlMapClientDaoSupport implements IBaseDAO<T>  {

}
