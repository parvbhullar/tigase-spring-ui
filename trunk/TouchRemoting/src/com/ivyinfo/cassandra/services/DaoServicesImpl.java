package com.ivyinfo.cassandra.services;

import static me.prettyprint.hector.api.factory.HFactory.createMultigetSuperSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createMutator;
import static me.prettyprint.hector.api.factory.HFactory.createRangeSuperSlicesQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumnQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.KsDef;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.service.ThriftCfDef;
import me.prettyprint.cassandra.service.ThriftCluster;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.OrderedSuperRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.beans.SuperRow;
import me.prettyprint.hector.api.beans.SuperRows;
import me.prettyprint.hector.api.beans.SuperSlice;
import me.prettyprint.hector.api.ddl.HCfDef;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.MutationResult;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.MultigetSliceQuery;
import me.prettyprint.hector.api.query.MultigetSuperSliceQuery;
import me.prettyprint.hector.api.query.Query;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import me.prettyprint.hector.api.query.RangeSuperSlicesQuery;
import me.prettyprint.hector.api.query.SuperColumnQuery;

public class DaoServicesImpl implements DaoServices{

	private final static String HOST_PORT = "192.168.1.163:9160";
//	private final static String HOST_PORT = "192.168.167.75:9160";
	private final static String CLUSTER_NAME = "Test Cluster";

	/**
	 * 获取cluster
	 * 
	 * @param clusterName
	 * @param hostIp
	 * @return
	 */
	public  Cluster getCluster(String clusterName, String hostIp) {
		return HFactory.getOrCreateCluster(CLUSTER_NAME, HOST_PORT);
	}

	/**
	 * 关闭连接
	 * 
	 * @param cluster
	 * @return
	 */
	public  void shutdownCluster(Cluster cluster) {
		cluster.getConnectionManager().shutdown();
	}

	/**
	 * 创建keyspace
	 * 
	 * @param keyspace
	 * @param cluster
	 * @return
	 */
	public String createKeyspace(String keyspace, Cluster c) {
		KsDef d = new KsDef();
		d.setName(keyspace);
		d.setStrategy_class("org.apache.cassandra.locator.SimpleStrategy");
	    return c.addKeyspace(new ThriftKsDef(d));
	    //c.dropKeyspace("DynKeyspace");
	}

	/**
	 * 创建columnFamily
	 * 
	 * @param columnFamily
	 * @param cluster
	 * @param keyspace
	 * @param comparatorType
	 * @return
	 */
	public String createColumnFamily(String columnFamily, Cluster c, String keyspace, String columnType, String comparatorType) {
//		CfDef d = new CfDef();
//		d.setKeyspace(keyspace);
//		d.setName(columnFamily);
//		d.setColumn_type(columnType); //"Super"
//		d.setComparator_type(comparatorType);  //UTF8Type
//		d.setGc_grace_seconds(86400);
//		d.setMax_compaction_threshold(32);
//		d.setMin_compaction_threshold(4);
//		HCfDef cfDef = new ThriftCfDef(d);
		
		HCfDef cfDef = new ThriftCfDef(keyspace, columnFamily);
		cfDef.setColumnType(columnType);
		cfDef.setComparatorType(comparatorType);
	    return c.addColumnFamily(cfDef);
	    //String cfid2 = c.dropColumnFamily("Keyspace1", "DynCf");
	}
	
	/**
	 * 创建keyspace时顺便创建多个columnfamily
	 * @param keyspace
	 * @param columnFamily
	 * @param columnType
	 * @param comparatorType
	 * @return
	 */
	public String createKeyspaceAndColumnFamily(String keyspace, Cluster c, String[] columnFamily, String[] columnType, String[] comparatorType){
		List ls = new ArrayList();
		for(int i=0; i<columnFamily.length; i++){
//			CfDef cfd = new CfDef();
//			cfd.setKeyspace(keyspace);
//			cfd.setName(columnFamily[i]);
//			cfd.setColumn_type(columnType[i]); //"Super"
//			cfd.setComparator_type(comparatorType[i]);  //UTF8Type
//			cfd.setGc_grace_seconds(86400);
//			cfd.setMax_compaction_threshold(32);
//			cfd.setMin_compaction_threshold(4);
//			cfd.setMemtable_flush_after_mins(memtable_flush_after_mins);
//			cfd.setMemtable_operations_in_millions(memtable_operations_in_millions);
//			cfd.setMemtable_throughput_in_mb(memtable_throughput_in_mb);
			
			HCfDef cfDef = new ThriftCfDef(keyspace, columnFamily[i]);
			cfDef.setColumnType(columnType[i]);
			cfDef.setComparatorType(comparatorType[i]);
			ls.add(cfDef);
		}
	    return c.addKeyspace(new ThriftKsDef(keyspace, "org.apache.cassandra.locator.SimpleStrategy", ls.size(), ls));
	}
	
	public Keyspace getKeyspace(String keyspaceName,String columnFamilyName, String columnType, String comparatorType, Cluster cluster) {
		Keyspace keyspace = null;
		try{
			keyspace = HFactory.createKeyspace(keyspaceName, cluster);
			if(!"Super".equals(columnType)){
				get("1", "name",StringSerializer.get(), keyspace,columnFamilyName);
			}else{
				getSuperColumnNameValue("1","1", "name",StringSerializer.get(), keyspace,columnFamilyName);
			}
			return keyspace;
		}catch(Exception ex){

			if(ex.getMessage()!=null && ex.getMessage().indexOf("why:Keyspace")>-1 && ex.getMessage().indexOf("does not exist")>-1){
				String[] columnFamilys = new String[]{columnFamilyName};
				String[] columnTypes = new String[]{columnType};
				String[] comparatorTypes = new String[]{comparatorType};
				createKeyspaceAndColumnFamily(keyspaceName,cluster,columnFamilys,columnTypes,comparatorTypes);
				keyspace = getKeyspace(keyspaceName,columnFamilyName,columnType,comparatorType,cluster);
			}
			
			if(ex.getMessage()!=null && ex.getMessage().indexOf("why:unconfigured columnfamily")>-1){
				createColumnFamily(columnFamilyName, cluster, keyspaceName, columnType, comparatorType);
				keyspace = getKeyspace(keyspaceName,columnFamilyName,columnType,comparatorType,cluster);
			}
			
			return keyspace;
		}
	}

/***************************************************************************************************************************************************************/
	
	/**
	 * 新增数据
	 * 
	 * @param <K>
	 * @param key
	 *            key关键字，可以是id，或者是时间戳。。。
	 * @param ColumnName
	 *            字段名，比如“邮件标题”，“邮件时间”，“邮件体”
	 * @param value
	 *            值
	 * @param keySerializer
	 *            字段类型
	 * @param keyspace
	 * @param CF_NAME
	 */
	public <K> void insert(final K key, final String ColumnName,final String value, Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();
		HFactory.createMutator(keyspace, keySerializer).insert(key,columnFamilyName,HFactory.createColumn(ColumnName, value, serializer,serializer));
	}

	/**
	 * 插入多个字段
	 * 
	 * @param <K>
	 * @param key
	 * @param ColumnNames
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 */
	public <K> void insertMultiColumn(final K key,Map<String, String> ColumnNames, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();

		Mutator<K> m = HFactory.createMutator(keyspace, keySerializer);
		for (Map.Entry<String, String> columnName : ColumnNames.entrySet()) {
			m.addInsertion(key, columnFamilyName, HFactory.createColumn(
					columnName.getKey(), columnName.getValue(), keyspace
							.createClock(), serializer, serializer));
		}
		m.execute();
	}

	/**
	 * 插入多个关键字的多个字段
	 * 
	 * @param <K>
	 * @param keyValues
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 */
	public <K> void insertMultiKeyMultiColumn(Map<K, Map<String, String>> keyValues, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();

		Mutator<K> m = HFactory.createMutator(keyspace, keySerializer);
		for (Map.Entry<K, Map<String, String>> keyValue : keyValues.entrySet()) {
			for (Map.Entry<String, String> columnName : keyValue.getValue()
					.entrySet()) {
				m.addInsertion(keyValue.getKey(), columnFamilyName, HFactory
						.createColumn(columnName.getKey(), columnName
								.getValue(), keyspace.createClock(),
								serializer, serializer));
			}
		}
		m.execute();
	}

	/**
	 * 获取关键字中字段的值
	 * 
	 * @param <K>
	 * @param key
	 * @param ColumnName
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 * @return
	 * @throws HectorException
	 */
	public <K> String get(final K key, final String ColumnName,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) throws HectorException {
		StringSerializer serializer = StringSerializer.get();

		ColumnQuery<K, String, String> q = HFactory.createColumnQuery(keyspace,
				keySerializer, serializer, serializer);
		QueryResult<HColumn<String, String>> r = q.setKey(key).setName(
				ColumnName).setColumnFamily(columnFamilyName).execute();
		HColumn<String, String> c = r.get();
		return c == null ? null : c.getValue();
	}

	/**
	 * 获取多个关键字中的一个字段的值
	 * 
	 * @param <K>
	 * @param Keys
	 * @param ColumnName
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 * @return
	 */
	public <K> Map<K, String> getMultiKeyColumnValue(K[] Keys,final String ColumnName, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();

		MultigetSliceQuery<K, String, String> q = HFactory
				.createMultigetSliceQuery(keyspace, keySerializer, serializer,
						serializer);
		q.setColumnFamily(columnFamilyName);
		q.setKeys(Keys);
		q.setColumnNames(ColumnName);

		QueryResult<Rows<K, String, String>> r = q.execute();
		Rows<K, String, String> rows = r.get();
		Map<K, String> ret = new HashMap<K, String>(Keys.length);
		for (K k : Keys) {
			HColumn<String, String> c = rows.getByKey(k).getColumnSlice()
					.getColumnByName(ColumnName);
			if (c != null && c.getValue() != null) {
				ret.put(k, c.getValue());
			}
		}
		return ret;
	}

	/**
	 * 获取多个关键字中多个字段的值
	 * 
	 * @param <K>
	 * @param Keys
	 * @param ColumnNames
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 * @return
	 */
	public <K> Map<K, Map<String, String>> getMultiKeyMultiColumnValue(K[] Keys, String[] ColumnNames, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();

		MultigetSliceQuery<K, String, String> q = HFactory
				.createMultigetSliceQuery(keyspace, keySerializer, serializer,
						serializer);
		q.setColumnFamily(columnFamilyName);
		q.setKeys(Keys);
		q.setColumnNames(ColumnNames);

		QueryResult<Rows<K, String, String>> r = q.execute();
		Rows<K, String, String> rows = r.get();
		Map<K, Map<String, String>> ret = new HashMap<K, Map<String, String>>(
				Keys.length);
		for (K k : Keys) {
			Map<String, String> columns = new HashMap<String, String>(
					ColumnNames.length);
			for (String columnName : ColumnNames) {
				HColumn<String, String> c = rows.getByKey(k).getColumnSlice()
						.getColumnByName(columnName);
				if (c != null && c.getValue() != null) {
					columns.put(columnName, c.getValue());
				}
			}
			ret.put(k, columns);
		}
		return ret;
	}

	/**
	 * Delete multiple values
	 */
	public <K> void deleteColumn(K[] Keys, String[] ColumnNames,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();

		Mutator<K> m = HFactory.createMutator(keyspace, keySerializer);
		for (K key : Keys) {
			for (String columnName : ColumnNames) {
				m.addDeletion(key, columnFamilyName, columnName, serializer);
			}
		}
		m.execute();
	}
	
//	public <K> void deleteKeys(K[] Keys, Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) {
//		Mutator<K> m = HFactory.createMutator(keyspace, keySerializer);
//		for (K key : Keys) {
//				m.addDeletion(key, columnFamilyName);
//		}
//		m.execute();
//	}

	/**
	 * 获取key的数量
	 * @param keyspace
	 * @param columnFamilyName
	 * @param columnName
	 * @return
	 * @throws HectorException
	 */
	public String[] getCountKey(Keyspace keyspace, String columnFamilyName,String start, String finish, int RowCount, String... columnName) throws HectorException {
		if(start==null||"".equals(start)) start = "-1";
		if(finish==null||"".equals(finish)) finish = "-1";
		if(RowCount == -1) RowCount = 1000;
		
		RangeSlicesQuery<String, String, String> q = HFactory.createRangeSlicesQuery(keyspace, StringSerializer.get(),
						StringSerializer.get(), StringSerializer.get());
		q.setKeys("", "");
		q.setColumnFamily(columnFamilyName);
		q.setColumnNames(columnName);
		q.setRange(start, finish, true, RowCount);
//		q.setRowCount(RowCount);
		QueryResult<OrderedRows<String, String, String>> r = q.execute();

		OrderedRows<String, String, String> rows = r.get();
		String[] keys = new String[rows.getCount()];
		int m = 0;
		for (Iterator it = rows.iterator(); it.hasNext();) {
			Row row = (Row) it.next();
			keys[m] = String.valueOf(row.getKey());
			m++;
		}
		return keys;
	}
	
/***************************************************************************************************************************************************************/

	/**
	 * 插入SuperColumn记录
	 */
	@SuppressWarnings("unchecked")
	public <K> void insertSuper(final K key,  Serializer<K> keySerializer, String superColumn, Map<String,String> Columns, Keyspace keyspace,String columnFamilyName) {
		StringSerializer serializer = StringSerializer.get();
		
		List ls = new ArrayList();
		Mutator<K> m = HFactory.createMutator(keyspace, keySerializer);
		for (Iterator it = Columns.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			HColumn hc = HFactory.createColumn(String.valueOf(e.getKey()), String.valueOf(e.getValue()), serializer, serializer);
			ls.add(hc);
		}
		HSuperColumn hsc = HFactory.createSuperColumn(superColumn, ls, serializer, serializer, serializer);
		m.addInsertion(key,columnFamilyName,hsc);
		m.execute();
		
	}
	
	/**
	 * 获取SuperColumn单条记录
	 * @param <K>
	 * @param key
	 * @param SuperColumnName
	 * @param ColumnName
	 * @param keySerializer
	 * @param keyspace
	 * @param columnFamilyName
	 * @return
	 * @throws HectorException
	 */
	public <K> String getSuperColumnNameValue(final K key, final String SuperColumnName, final String ColumnName,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) throws HectorException {
		StringSerializer serializer = StringSerializer.get();

		SuperColumnQuery<K, String, String, String> q = HFactory.createSuperColumnQuery(keyspace,keySerializer, serializer, serializer, serializer);
		q.setSuperName(SuperColumnName).setColumnFamily(columnFamilyName);
		QueryResult<HSuperColumn<String, String, String>> r = q.setKey(key).execute();
		HSuperColumn<String, String, String> sc = r.get();
		HColumn<String, String> c = sc.get(0);
		return c == null ? "" : c.getValue();
	}
	
	/**
	 * 获取SuperColumn 字段和值
	 * @param keys
	 * @param keyspace
	 * @param columnFamilyName
	 * @param SuperColumnName
	 * @param RowCount
	 * @return
	 * @throws HectorException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getSuperColumnValue(String[] keys, Keyspace keyspace, String columnFamilyName, String startSuperColumnName, String endSuperColumnName, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(startSuperColumnName, endSuperColumnName, false, RowCount); // SuperColumn 可以为""
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	//slice.getColumnByName("testSuperMultigetSliceQuery1").getColumns().get(0).getValue();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    		for(HColumn<String, String> column : SuperColumn.getColumns()){
	    			mapRow.put(column.getName(), column.getValue());
	    		}
	    		mapRows.put(SuperColumn.getName(), mapRow);
	    	}
	    }
	    return mapRows;
	    
	}
	
	/**
	 * 根据读取标记或者消息类型查询IM消息信息
	 * @param keys
	 * @param keyspace
	 * @param columnFamilyName
	 * @param SuperColumnName
	 * @param fieldname  查询的字段名
	 * @param value  查询的值
	 * @param RowCount
	 * @return
	 * @throws HectorException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getSuperColumnValue_IMMessage_ReadORType(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	//slice.getColumnByName("testSuperMultigetSliceQuery1").getColumns().get(0).getValue();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断是否符合条件
	    			if(value.equals(mapRow.get(fieldname))){
	    				mapRows.put(SuperColumn.getName(), mapRow);
	    			}
	    	}
	    }
	    return mapRows;
	}
	
	/**
	 * 根据读取标记和消息类型查询IM消息信息
	 * @param keys
	 * @param keyspace
	 * @param columnFamilyName
	 * @param SuperColumnName
	 * @param typefieldname 消息类型字段名
	 * @param typevalue 消息类型字段值
	 * @param readfieldname 读取状态字段名
	 * @param readvalue 读取状态的值
	 * @param RowCount
	 * @return
	 * @throws HectorException
	 */
	public Map<String, Map<String, String>> getSuperColumnValue_IMMessage_ReadAndType(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String typefieldname, String typevalue, String readfieldname, String readvalue, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断条件
	    			if(typevalue.equals(mapRow.get(typefieldname)) && readvalue.equals(mapRow.get(readfieldname))){
	    				mapRows.put(SuperColumn.getName(), mapRow);
	    			}
	    	}
	    }
	    return mapRows;
	}
	
	public Map<String, Map<String, String>> getSuperColumnValue_AddressBook_One(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	//slice.getColumnByName("testSuperMultigetSliceQuery1").getColumns().get(0).getValue();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断是否符合条件
	    			if(!"".equals(fieldname) && !"".equals(value)){
	    				String field = (String) mapRow.get(fieldname);
	    				if(field.indexOf(value)>-1){
		    				mapRows.put(SuperColumn.getName(), mapRow);
		    			}
	    			}else if("".equals(fieldname) && !"".equals(value)){
	    				String firstname = (String) mapRow.get("firstName");
	    				String lastname = (String) mapRow.get("lastName");
	    				if(firstname.indexOf(value)>-1 || lastname.indexOf(value)>-1){
		    				mapRows.put(SuperColumn.getName(), mapRow);
		    			}
	    			}else{
	    				mapRows.put(SuperColumn.getName(), mapRow);
	    			}
	    	}
	    }
	    return mapRows;
	}
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContacts_NidORPkeyORUid(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String nidfieldname, String nidvalue, String pkeyfieldname, String pkeyvalue, String uidfieldname, String uidvalue, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断条件
	    			if(uidvalue != null && !"null".equals(uidvalue) && !"".equals(uidvalue)){
	    				if(uidvalue.equals(mapRow.get(uidfieldname))){
		    				mapRows.put(SuperColumn.getName(), mapRow);
		    			}
	    			}else{
		    			if(pkeyvalue == null && "".equals(pkeyvalue)){
		    				if(nidvalue.equals(mapRow.get(nidfieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}else{
			    			if(nidvalue.equals(mapRow.get(nidfieldname)) && pkeyvalue.equals(mapRow.get(pkeyfieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}
	    			}
	    	}
	    }
	    return mapRows;
	}
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContactsNode_PidORNode(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String pidfieldname, String pidvalue, String nodefieldname, String nodevalue, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断条件
		    			if(nodevalue == null && "".equals(nodevalue)){
		    				if(pidvalue.equals(mapRow.get(pidfieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}else{
			    			if(pidvalue.equals(mapRow.get(pidfieldname)) && nodevalue.equals(mapRow.get(nodefieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}
	    	}
	    }
	    return mapRows;
	}
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContactsNode_NidORUid(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String nidfieldname, String nidvalue, String uidfieldname, String uidvalue, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断条件
		    			if(uidvalue == null && "".equals(uidvalue)){
		    				if(nidvalue.equals(mapRow.get(nidfieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}else{
		    				if(uidvalue.equals(mapRow.get(uidfieldname))){
			    				mapRows.put(SuperColumn.getName(), mapRow);
			    			}
		    			}
	    	}
	    }
	    return mapRows;
	}
	
	public Map<String, Map<String, String>> getSuperColumnValue_Note_FolderId(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException {
		StringSerializer serializer = StringSerializer.get();
	    // get value
	    MultigetSuperSliceQuery<String, String, String, String> q = createMultigetSuperSliceQuery(keyspace, serializer, serializer, serializer, serializer);
	    q.setColumnFamily(columnFamilyName);
	    q.setKeys(keys);
	    q.setRange(SuperColumnName, SuperColumnName, false, RowCount);
	    QueryResult<SuperRows<String, String, String, String>> r = q.execute();
	    SuperRows<String, String, String, String> rows = r.get();
	    Map mapRows = new HashMap();
	    for (SuperRow<String, String, String, String> row2 : rows) {
	    	SuperSlice<String, String, String> slice = row2.getSuperSlice();
	    	//slice.getColumnByName("testSuperMultigetSliceQuery1").getColumns().get(0).getValue();
	    	for (HSuperColumn<String, String, String> SuperColumn : slice.getSuperColumns()) {
	    		Map mapRow = new HashMap();
	    			for(HColumn<String, String> column : SuperColumn.getColumns()){
		    			mapRow.put(column.getName(), column.getValue());
		    		}
	    			//判断是否符合条件
	    			if(value.equals(mapRow.get(fieldname))){
	    				mapRows.put(SuperColumn.getName(), mapRow);
	    			}
	    	}
	    }
	    return mapRows;
	}
	
	/**
	 * 删除superColumn
	 * @param key
	 * @param supercolumnName
	 * @param columnName  可以为null
	 * @param keyspace
	 * @param columnFamilyName
	 */
	public void removeSuperColumn(String key, String supercolumnName, String columnName, Keyspace keyspace, String columnFamilyName){
		StringSerializer serializer = StringSerializer.get();
	    // remove value
		 Mutator<String> m = createMutator(keyspace, serializer);
	     m.subDelete(key, columnFamilyName, supercolumnName, columnName, serializer, serializer);
	}
	
/***************************************************************************************************************************************************************/

	public static void main(String[] args) throws HectorException {
		DaoServicesImpl dao = new DaoServicesImpl();
		Cluster c = dao.getCluster("", "");
		try {
			System.err.println("======c:" + c.toString());
			// 创建keyspace
//			 dao.createKeyspace("futuo3",c);
			// 创建columnFamily
//			 dao.createColumnFamily("zhangyan", c, "futuo", "Super", "UTF8Type");

			Keyspace keyspace = dao.getKeyspace("futuo","addressbook","Super","UTF8Type",c);
			System.err.println("=========keyspace:"+keyspace.toString());
			// 插入数据
//			 dao.insert("key1", "name", "value", StringSerializer.get(),keyspace, "zhangyan3");
			
			 //String[] keys = {"1"};
			 //dao.deleteKeys(keys, StringSerializer.get(), keyspace, "mzq");
			

//			String[] keys = { "cab", "key", "key1", "key2", "abc" };
//			Map m = dao.getMultiKeyColumnValue(keys, "name", StringSerializer
//					.get(), keyspace, "zhangyan1");
//			for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
//				Map.Entry e = (Map.Entry) it.next();
//				System.out.println("key:" + e.getKey() + " value: "
//						+ e.getValue());
//			}
			
//			dao.getCountKey(keyspace, "mzq", "-1", "-1", 100, "");
			
			Map Columns = new HashMap();
			Columns.put("name", "zhangyan");
			Columns.put("pass", "123456");
			dao.insertSuper("张砚", StringSerializer.get(),"supercs", Columns, keyspace, "addressbook");
			
//			String value = dao.getSuperColumnNameValue("key", "supercs", "name", StringSerializer.get(), keyspace, "supercf");
//			System.err.println("===value:"+value);
			
//			String[] keys = {"key"};
//			dao.getSuperColumnValue(keys, keyspace, "supercf", "", 1000);
			
			System.err.println("end");
			dao.shutdownCluster(c);
		} catch (Exception ex) {
			ex.printStackTrace();
			dao.shutdownCluster(c);
		}

	}

}
