package com.ivyinfo.cassandra.services;

import java.util.Map;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.exceptions.HectorException;

public interface DaoServices {
	public  Cluster getCluster(String clusterName, String hostIp);
	
	public  void shutdownCluster(Cluster cluster);
	
	public String createKeyspace(String keyspace, Cluster cluster);
	
	public String createColumnFamily(String columnFamily, Cluster c, String keyspace, String columnType, String comparatorType);
	
	public Keyspace getKeyspace(String keyspaceName,String columnFamilyName, String columnType, String comparatorType, Cluster cluster);
	
	public <K> void insert(final K key, final String ColumnName,final String value, Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName);
	
	public <K> void insertMultiColumn(final K key,Map<String, String> ColumnNames, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName);
	
	public <K> void insertMultiKeyMultiColumn(Map<K, Map<String, String>> keyValues, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName);
	
	public <K> String get(final K key, final String ColumnName,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) throws HectorException;
	
	public <K> Map<K, String> getMultiKeyColumnValue(K[] Keys,final String ColumnName, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName);
	
	public <K> Map<K, Map<String, String>> getMultiKeyMultiColumnValue(K[] Keys, String[] ColumnNames, Serializer<K> keySerializer,Keyspace keyspace, String columnFamilyName);
	
	public <K> void deleteColumn(K[] Keys, String[] ColumnNames,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) ;
	
//	public <K> void deleteKeys(K[] Keys, Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) ;
	
	public String[] getCountKey(Keyspace keyspace, String columnFamilyName,String start, String finish, int RowCount, String... columnName) throws HectorException;
	
	
	public <K> void insertSuper(final K key,  Serializer<K> keySerializer, String superColumn, Map<String,String> Columns, Keyspace keyspace,String columnFamilyName);
	
	public <K> String getSuperColumnNameValue(final K key, final String SuperColumnName, final String ColumnName,Serializer<K> keySerializer, Keyspace keyspace,String columnFamilyName) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue(String[] keys, Keyspace keyspace, String columnFamilyName, String startSuperColumnName, String endSuperColumnName, int RowCount ) throws HectorException; 
	
	public void removeSuperColumn(String key, String supercolumnName, String columnName, Keyspace keyspace, String columnFamilyName);
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMMessage_ReadORType(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMMessage_ReadAndType(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String typefieldname, String typevalue, String readfieldname, String readvalue, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_AddressBook_One(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContacts_NidORPkeyORUid(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String nidfieldname, String nidvalue, String pkeyfieldname, String pkeyvalue, String uidfieldname, String uidvalue, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContactsNode_PidORNode(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String pidfieldname, String pidvalue, String nodefieldname, String nodevalue, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_IMContactsNode_NidORUid(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String nidfieldname, String nidvalue, String uidfieldname, String uidvalue, int RowCount ) throws HectorException;
	
	public Map<String, Map<String, String>> getSuperColumnValue_Note_FolderId(String[] keys, Keyspace keyspace, String columnFamilyName, String SuperColumnName, String fieldname, String value, int RowCount ) throws HectorException;
}
