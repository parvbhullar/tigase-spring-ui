package tigase.db.mixed;

import java.util.List;
import java.util.Map;

import tigase.db.AuthorizationException;
import tigase.db.DBInitException;
import tigase.db.TigaseDBException;
import tigase.db.UserExistsException;
import tigase.db.UserNotFoundException;
import tigase.xmpp.BareJID;


public interface AuthAndUserRepository {
	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide desired authentication protocol to the authentication logic.
	 */
	public static final String PROTOCOL_KEY = "protocol";

	/**
	 * Property value for <code>otherAuth</code> method call. It is used to
	 * provide desired authentication SASL protocol to the authentication logic.
	 */
	public static final String PROTOCOL_VAL_SASL = "sasl";

	/**
	 * Property value for <code>otherAuth</code> method call. It is used to
	 * provide desired authentication NON-SASL protocol to the authentication
	 * logic.
	 */
	public static final String PROTOCOL_VAL_NONSASL = "nonsasl";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide authentication handshaking data during login process. Some
	 * authentication mechanisms require exchanging requests between the client
	 * and the server. This property key points back to the data which need to
	 * be sent back to the client.
	 */
	public static final String RESULT_KEY = "result";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide desired authentication mechanism to the authentication logic.
	 */
	public static final String MACHANISM_KEY = "mechanism";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide authentication realm to the authentication logic. In most cases,
	 * the realm is just a domain name.
	 */
	public static final String REALM_KEY = "realm";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide authentication domain to the authentication logic. It is highly
	 * recommended that this property is always set, even if the authentication
	 * protocol/mechanism does not need it strictly.
	 */
	public static final String SERVER_NAME_KEY = "server-name";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide an extra authentication data by the client to the authentication
	 * logic. Please note the <code>RESULT_KEY</code> property key is used to
	 * provide authentication data from the server to the client. This property
	 * is used to provide authentication data from the client to the server.
	 */
	public static final String DATA_KEY = "data";

	/**
	 * Property key name for <code>otherAuth</code> method call. It is used to
	 * provide a user ID on successful user login. Please note, the key points
	 * to the object of <code>BareJID</code> type.
	 */
	public static final String USER_ID_KEY = "user-id";
	

	// ~--- methods
	// --------------------------------------------------------------

	/**
	 * Describe <code>addUser</code> method here.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value
	 * @param password
	 *            a <code>String</code> value
	 * @exception UserExistsException
	 *                if an error occurs
	 * @exception TigaseDBException
	 *                if an error occurs
	 */
	void addUser(BareJID user, String password) throws UserExistsException,
			TigaseDBException;

	/**
	 * <code>digestAuth</code> method performs non-sasl, digest authentication
	 * as described in non-sasl authentication <a
	 * href="http://www.xmpp.org/extensions/xep-0078.html">XEP-0078</a> For now
	 * it is empty and always returns <code>false</code> as I don't have
	 * description for database with passwords.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user name
	 * @param digest
	 *            a <code>String</code> value password digest sum
	 * @param id
	 *            a <code>String</code> value session ID used for digest sum
	 *            calculation.
	 * @param alg
	 *            a <code>String</code> value of algorithm ID used for digest
	 *            sum calculation.
	 * @return a <code>boolean</code> value <code>true</code> on successful
	 *         authentication, <code>false</code> on authentication failure.
	 * @exception UserNotFoundException
	 *                if an given user name is not found in the authentication
	 *                repository.
	 * @exception TigaseDBException
	 *                if an error occurs during during accessing database;
	 * @exception AuthorizationException
	 *                if an error occurs during authentication process.
	 */
	boolean digestAuth(BareJID user, String digest, String id, String alg)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException;

	// ~--- get methods
	// ----------------------------------------------------------

	/**
	 * <code>getResourceUri</code> method returns database connection string.
	 * 
	 * @return a <code>String</code> value of database connection string.
	 */
	String getResourceUri();

	/**
	 * This method is only used by the server statistics component to report
	 * number of registered users.
	 * 
	 * @return a <code>long</code> number of registered users in the repository.
	 */
	long getUsersCount();



	// ~--- methods
	// --------------------------------------------------------------



	/**
	 * Describe <code>logout</code> method here.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value
	 * @exception UserNotFoundException
	 *                if an error occurs
	 * @exception TigaseDBException
	 *                if an error occurs
	 */
	void logout(BareJID user) throws UserNotFoundException, TigaseDBException;

	/**
	 * Describe <code>otherAuth</code> method here.
	 * 
	 * @param authProps
	 *            a <code>Map</code> value
	 * @return a <code>boolean</code> value
	 * @exception UserNotFoundException
	 *                if an error occurs
	 * @exception TigaseDBException
	 *                if an error occurs
	 * @exception AuthorizationException
	 *                if an error occurs
	 */
	boolean otherAuth(Map<String, Object> authProps)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException;

	/**
	 * <code>plainAuth</code> method performs non-sasl, plain authentication as
	 * described in non-sasl authentication <a
	 * href="http://www.xmpp.org/extensions/xep-0078.html">XEP-0078</a>.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user name
	 * @param password
	 *            a <code>String</code> value of plain user password.
	 * @return a <code>boolean</code> value <code>true</code> on successful
	 *         authentication, <code>false</code> on authentication failure.
	 * @exception UserNotFoundException
	 *                if an given user name is not found in the authentication
	 *                repository.
	 * @exception TigaseDBException
	 *                if an error occurs during during accessing database;
	 * @exception AuthorizationException
	 *                if an error occurs during authentication process.
	 */
	boolean plainAuth(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException;

	/**
	 * <code>queryAuth</code> returns mechanisms available for authentication.
	 * 
	 * @param authProps
	 *            a <code>Map</code> value with parameters for authentication.
	 */
	void queryAuth(Map<String, Object> authProps);

	/**
	 * Describe <code>removeUser</code> method here.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value
	 * @exception UserNotFoundException
	 *                if an error occurs
	 * @exception TigaseDBException
	 *                if an error occurs
	 */
	void removeUser(BareJID user) throws UserNotFoundException,
			TigaseDBException;

	/**
	 * Describe <code>updatePassword</code> method here.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value
	 * @param password
	 *            a <code>String</code> value
	 * @throws UserNotFoundException
	 * @exception TigaseDBException
	 *                if an error occurs
	 */
	void updatePassword(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>addDataList</code> method adds mode entries to existing data list
	 * associated with given key in repository under given node path. This
	 * method is very similar to <code>setDataList(...)</code> except it doesn't
	 * remove existing data.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the specified values list is
	 *            to be associated.
	 * @param list
	 *            a <code>String[]</code> is an array of values to be associated
	 *            with the specified key.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void addDataList(BareJID user, String subnode, String key, String[] list)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * This <code>addUser</code> method allows to add new user to repository. It
	 * <b>must</b> throw en exception <code>UserExistsException</code> if such
	 * user already exists because user <b>must</b> be unique within user
	 * repository data base.<br/>
	 * As one <em>XMPP</em> server can support many virtual internet domains it
	 * is required that <code>user</code> id consists of user name and domain
	 * address: <em>username@domain.address.net</em> for example.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user id consisting of user
	 *            name and domain address.
	 * @exception UserExistsException
	 *                if user with the same id already exists.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void addUser(BareJID user) throws UserExistsException, TigaseDBException;

	// ~--- get methods
	// ----------------------------------------------------------

	/**
	 * <code>getData</code> method returns a value associated with given key for
	 * user repository in given subnode. If key is not found in repository given
	 * default value is returned.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the needed value is
	 *            associated.
	 * @param def
	 *            a <code>String</code> value which is returned in case if data
	 *            for specified key does not exixist in repository.
	 * @return a <code>String</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String getData(BareJID user, String subnode, String key, String def)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>getData</code> method returns a value associated with given key for
	 * user repository in given subnode. If key is not found in repository
	 * <code>null</code> value is returned.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the needed value is
	 *            associated.
	 * @return a <code>String</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String getData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>getData</code> method returns a value associated with given key for
	 * user repository in default subnode. If key is not found in repository
	 * <code>null</code> value is returned.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param key
	 *            a <code>String</code> with which the needed value is
	 *            associated.
	 * @return a <code>String</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String getData(BareJID user, String key) throws UserNotFoundException,
			TigaseDBException;

	/**
	 * <code>getDataList</code> method returns array of values associated with
	 * given key or <code>null</code> if given key does not exist for given user
	 * ID in given node path.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the needed values list is
	 *            associated.
	 * @return a <code>String[]</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String[] getDataList(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>getKeys</code> method returns list of all keys stored in given
	 * subnode in user repository. There is a value (or list of values)
	 * associated with each key. It is up to user (developer) to know what key
	 * keeps one value and what key keeps list of values.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @return a <code>String[]</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String[] getKeys(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>getKeys</code> method returns list of all keys stored in default
	 * user repository node. There is some a value (or list of values)
	 * associated with each key. It is up to user (developer) to know what key
	 * keeps one value and what key keeps list of values.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored or retrieved. User ID consists of user name and domain
	 *            name.
	 * @return a <code>String[]</code> value
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String[] getKeys(BareJID user) throws UserNotFoundException,
			TigaseDBException;



	/**
	 * <code>getSubnodes</code> method returns list of all direct subnodes from
	 * given node.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @return a <code>String[]</code> value is an array of all direct subnodes.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String[] getSubnodes(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>getSubnodes</code> method returns list of all <em>root</em> nodes
	 * for given user.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @return a <code>String[]</code> value is an array of all <em>root</em>
	 *         nodes for given user.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	String[] getSubnodes(BareJID user) throws UserNotFoundException,
			TigaseDBException;

	/**
	 * Returns a user unique ID number within the given repository. Please note
	 * it is also possible that the ID number is unique only for the user
	 * domain. The ID is a positive number if the user exists and negative if
	 * the user was not found in the repository.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored or retrieved. User ID consists of user name and domain
	 *            name.
	 * @return a user inique ID number within the repository or domain. The ID
	 *         is a positive number if the user exists and negative if the user
	 *         was not found in the repository.
	 * @throws TigaseDBException
	 *             if there is a problem with accessing user repository.
	 */
	long getUserUID(BareJID user) throws TigaseDBException;

	/**
	 * This method is only used by the data conversion tools. They attempt to
	 * copy whole user repositories from one to another database. Databases
	 * might not be compatible but as long as the API is implemented and they
	 * support adding user the user database can be copied to a different data
	 * source.
	 * 
	 * @return returns a collection of all user IDs (Jabber IDs) stored in the
	 *         user repository.
	 * @throws tigase.db.TigaseDBException
	 */
	List<BareJID> getUsers() throws TigaseDBException;

	


	/**
	 * This method is only used by the server statistics component to report
	 * number of registered users for given domain.
	 * 
	 * @param domain
	 * @return a <code>long</code> number of registered users in the repository.
	 */
	long getUsersCount(String domain);

	// ~--- methods
	// --------------------------------------------------------------

	/**
	 * The method is called to initialize the data repository. Depending on the
	 * implementation all the initialization parameters can be passed either via
	 * <code>resource_uri</code> parameter as the database connection string or
	 * via <code>params</code> map if the required repository parameters are
	 * more complex or both.
	 * 
	 * @param resource_uri
	 *            value in most cases representing the database connection
	 *            string.
	 * @param params
	 *            is a <code>Map</code> with repository properties necessary to
	 *            initialize and perform all the functions. The initialization
	 *            parameters are implementation dependent.
	 * @throws tigase.db.DBInitException
	 *             if there was an error during repository initialization. Some
	 *             implementations, though, perform so called lazy
	 *             initialization so even though there is a problem with the
	 *             underlying repository it may not be signaled through this
	 *             method call.
	 */
	void initRepository(String resource_uri, Map<String, String> params)
			throws DBInitException;

	/**
	 * <code>removeData</code> method removes pair (key, value) from user
	 * repository in given subnode. If the key exists in user repository there
	 * is always a value associated with this key - even empty
	 * <code>String</code>. If key does not exist the <code>null</code> value is
	 * returned from repository backend or given default value.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> for which the value is to be removed.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void removeData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>removeData</code> method removes pair (key, value) from user
	 * repository in default repository node. If the key exists in user
	 * repository there is always a value associated with this key - even empty
	 * <code>String</code>. If key does not exist the <code>null</code> value is
	 * returned from repository backend or given default value.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param key
	 *            a <code>String</code> for which the value is to be removed.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void removeData(BareJID user, String key) throws UserNotFoundException,
			TigaseDBException;

	/**
	 * <code>removeSubnode</code> method removes given subnode with all subnodes
	 * in this node and all data stored in this node and in all subnodes.
	 * Effectively it removes entire repository tree starting from given node.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path to subnode which
	 *            has to be removed. Node path has the same form as directory
	 *            path on file system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void removeSubnode(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException;


	// ~--- set methods
	// ----------------------------------------------------------

	/**
	 * <code>setData</code> method sets data value for given user ID in
	 * repository under given node path and associates it with given key. If
	 * there already exists value for given key in given node, old value is
	 * replaced with new value. No warning or exception is thrown in case if
	 * methods overwrites old value.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the specified value is to be
	 *            associated.
	 * @param value
	 *            a <code>String</code> value to be associated with the
	 *            specified key.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void setData(BareJID user, String subnode, String key, String value)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * This <code>setData</code> method sets data value for given user ID
	 * associated with given key in default repository node. Default node is
	 * dependent on implementation and usually it is root user node. If there
	 * already exists value for given key in given node, old value is replaced
	 * with new value. No warning or exception is thrown in case if methods
	 * overwrites old value.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param key
	 *            a <code>String</code> with which the specified value is to be
	 *            associated.
	 * @param value
	 *            a <code>String</code> value to be associated with the
	 *            specified key.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void setData(BareJID user, String key, String value)
			throws UserNotFoundException, TigaseDBException;

	/**
	 * <code>setDataList</code> method sets list of values for given user
	 * associated given key in repository under given node path. If there
	 * already exist values for given key in given node, all old values are
	 * replaced with new values. No warning or exception is thrown in case if
	 * methods overwrites old value.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value of user ID for which data must be
	 *            stored. User ID consists of user name and domain name.
	 * @param subnode
	 *            a <code>String</code> value is a node path where data is
	 *            stored. Node path has the same form as directory path on file
	 *            system:
	 * 
	 *            <pre>
	 * /root/subnode1/subnode2
	 * </pre>
	 * 
	 *            .
	 * @param key
	 *            a <code>String</code> with which the specified values list is
	 *            to be associated.
	 * @param list
	 *            a <code>String[]</code> is an array of values to be associated
	 *            with the specified key.
	 * @exception UserNotFoundException
	 *                if user id hasn't been found in repository.
	 * @throws TigaseDBException
	 *             if database backend error occurs.
	 */
	void setDataList(BareJID user, String subnode, String key, String[] list)
			throws UserNotFoundException, TigaseDBException;

	// ~--- methods
	// --------------------------------------------------------------

	/**
	 * Method <code>userExists</code> checks whether the user (or repository top
	 * node) exists in the database. The method doesn't throw any exception nor
	 * it creates the user in case it is missing. It just checks whether the
	 * user is already in the database.
	 * 
	 * Please don't overuse this method. All other methods throw
	 * <code>UserNotFoundException</code> exception in case the user is missing
	 * for which you executed the method. The exception is thrown unless
	 * <code>userAutoCreate</code> property is set to true. In such case the
	 * exception is never thrown and the methods are executed for given
	 * parameters prior to creating user entry if it is missing.
	 * 
	 * Therefore this method should be used only to check whether the account
	 * exists without creating it.
	 * 
	 * @param user
	 *            a <code>BareJID</code> value
	 * @return a <code>boolean</code> value
	 */
	boolean userExists(BareJID user);
}
