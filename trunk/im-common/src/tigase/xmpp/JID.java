/*
 * Tigase Jabber/XMPP Server
 * Copyright (C) 2004-2008 "Artur Hefczyc" <artur.hefczyc@tigase.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 *
 * $Rev: 582 $
 * Last modified by $Author: kobit $
 * $Date: 2010-02-05 08:41:04 +0800 (周五, 05 二月 2010) $
 */

package tigase.xmpp;

import tigase.util.TigaseStringprepException;


//~--- non-JDK imports --------------------------------------------------------


//~--- classes ----------------------------------------------------------------

/**
 * The class defines an instance of a single XMPP JID identifier.
 * When the object is created all parameters are checked and processed through
 * the stringprep. An exception is thrown in case of a stringprep processing error.
 *
 *
 * Created: Dec 28, 2009 10:48:04 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 582 $
 */
public final class JID implements Comparable<JID> {
	private final BareJID bareJid;
	private final String resource;
	private final String to_string;

	//~--- constructors ---------------------------------------------------------

	/**
	 * Constructs a new <code>JID</code> instance using given <code>BareJID</code>
	 * instance as user bare JID and <code>String</code> instance as a resource part.
	 * <p/>
	 * As the <code>BareJID</code> instances are immutable the constructor
	 * doesn't create a copy of the given <code>BareJID</code>, instead it saves
	 * the reference to a given object.<br/>
	 *
	 * @param bareJid is a <code>BareJID</code> instance used to create the
	 * <code>JID</code> instance.
	 * @param resource is a <code>String</code> instance representing JID's
	 * resource part.
	 * @throws TigaseStringprepException exception if there was an error during
	 * stringprep processing.
	 */
	private JID(BareJID bareJid, String resource) {
		this.bareJid = bareJid;
		this.resource = resource;
		this.to_string = BareJID.toString(bareJid, resource);
	}

	//~--- methods --------------------------------------------------------------

	/**
	 * Constructs a new <code>JID</code> instance using given <code>BareJID</code>
	 * instance as user bare JID and <code>String</code> instance as a resource part.
	 * <p/>
	 * As the <code>BareJID</code> instances are immutable the constructor
	 * doesn't create a copy of the given <code>BareJID</code>, instead it saves
	 * the reference to a given object.<br/>
	 * The resourve parameter is parsed, checked and run through the stringprep
	 * processing. In case of stringprep error, an exception is thrown.
	 *
	 * @param bareJid is a <code>BareJID</code> instance used to create the
	 * <code>JID</code> instance.
	 * @param p_resource is a <code>String</code> instance representing JID's
	 * resource part.
	 * @return <code>JID</code> class instance.
	 * @throws TigaseStringprepException exception if there was an error during
	 * stringprep processing.
	 */
	public static JID jidInstance(BareJID bareJid, String p_resource)
					throws TigaseStringprepException {
		String f_resource = (p_resource == null)
												? null : BareJID.stringPrep.resourceprep(p_resource);

		return new JID(bareJid, f_resource);
	}

	/**
	 * Creates a new <code>JID</code> instance using given <code>BareJID</code>
	 * instance as a parameter. The resource part is set to null.<br/>
	 * As the <code>BareJID</code> instances are immutable the constructor
	 * doesn't create a copy of the given <code>BareJID</code>, instead it saves
	 * the reference to a given object.
	 *
	 * @param bareJid is a <code>BareJID</code> instance used to create the
	 * <code>JID</code> instance.
	 * @return <code>JID</code> class instance.
	 */
	public static JID jidInstance(BareJID bareJid) {
		return new JID(bareJid, null);
	}

	/**
	 * Constructs a new <code>JID</code> instance using a JID parameter given
	 * as a <code>String</code> instance. The parameter is parsed, checked and
	 * run through stringprep processing. An exception is thrown if there is
	 * an error while the JID is checked.
	 *
	 * @param jid a JID parameter given as a <code>String</code> instance.
	 *
	 *
	 * @return <code>JID</code> class instance.
	 * @throws TigaseStringprepException exception if there was an error during
	 * stringprep processing.
	 */
	public static JID jidInstance(String jid) throws TigaseStringprepException {
		String[] parsedJid = BareJID.parseJID(jid);

		return jidInstance(parsedJid[0], parsedJid[1], parsedJid[2]);
	}

	/**
	 * Constructs a new <code>JID</code> instance using given <code>String</code>
	 * parameters.
	 * <p/>
	 * All the <code>String</code> parameters are parsed, checked and run through
	 * the stringprep processing. In case of stringprep error, an exception is thrown.
	 *
	 * @param localpart is a <code>String</code> instance representing JID's
	 * localpart (nickname) part.
	 * @param domain is a <code>String</code> instance representing JID's
	 * domain part.
	 * @param resource is a <code>String</code> instance representing JID's
	 * resource part.
	 *
	 * @return <code>JID</code> class instance.
	 * @throws TigaseStringprepException exception if there was an error during
	 * stringprep processing.
	 */
	public static JID jidInstance(String localpart, String domain, String resource)
					throws TigaseStringprepException {
		return jidInstance(BareJID.bareJIDInstance(localpart, domain), resource);
	}

	/**
	 * Constructs a new <code>JID</code> instance using given <code>BareJID</code>
	 * instance as user bare JID and <code>String</code> instance as a resource part.
	 * <strong>Note, this method does not perform stringprep processing on input
	 * parameters.</strong>
	 * <p/>
	 * As the <code>BareJID</code> instances are immutable the constructor
	 * doesn't create a copy of the given <code>BareJID</code>, instead it saves
	 * the reference to a given object.<br/>
	 *
	 * @param bareJid is a <code>BareJID</code> instance used to create the
	 * <code>JID</code> instance.
	 * @param p_resource is a <code>String</code> instance representing JID's
	 * resource part.
	 * @return <code>JID</code> class instance.
	 */
	public static JID jidInstanceNS(BareJID bareJid, String p_resource) {
		return new JID(bareJid, p_resource);
	}

	/**
	 * Constructs a new <code>JID</code> instance using a JID parameter given
	 * as a <code>String</code> instance.
	 * <strong>Note, this method does not perform stringprep processing on input
	 * parameters.</strong>
	 *
	 * @param jid a JID parameter given as a <code>String</code> instance.
	 * @return <code>JID</code> class instance.
	 */
	public static JID jidInstanceNS(String jid) {
		String[] parsedJid = BareJID.parseJID(jid);

		return jidInstanceNS(parsedJid[0], parsedJid[1], parsedJid[2]);
	}

	/**
	 * Constructs a new <code>JID</code> instance using given <code>String</code>
	 * parameters.
	 * <strong>Note, this method does not perform stringprep processing on input
	 * parameters.</strong>
	 * <p/>
	 *
	 * @param localpart is a <code>String</code> instance representing JID's
	 * localpart (nickname) part.
	 * @param domain is a <code>String</code> instance representing JID's
	 * domain part.
	 * @param resource is a <code>String</code> instance representing JID's
	 * resource part.
	 *
	 * @return <code>JID</code> class instance.
	 */
	public static JID jidInstanceNS(String localpart, String domain, String resource) {
		return jidInstanceNS(BareJID.bareJIDInstanceNS(localpart, domain), resource);
	}

	/**
	 * Method compares the <code>JID</code> instance with a given object.
	 * The implementation fulfills the specification contract and returns a value
	 * as you would expect from the call:
	 * <pre>
	 * jid_1.toString().compareTo(jid_2.toString())
	 * </pre>
	 *
	 *
	 * @param o is a <code>JID</code> instance to compare to.
	 *
	 * @return an integer value which is a result of comparing the two objects.
	 */
	public int compareTo(JID o) {
		return to_string.compareTo(o.to_string);
	}

	/**
	 * The method returns a copy of the <code>JID</code> instance with a different
	 * resource part given as a parameter.
	 *
	 * @param resource is a <code>String</code> instance representing JID's
	 * new resource part.
	 *
	 * @return a new instance of the <code>JID</code> class with a new resource
	 * part.
	 *
	 * @throws TigaseStringprepException if resource stringprep processing fails.
	 */
	public JID copyWithResource(String resource) throws TigaseStringprepException {
		return jidInstance(bareJid, resource);
	}

	/**
	 * The method returns a copy of the <code>JID</code> instance with a different
	 * resource part given as a parameter.
	 *
	 * @param resource is a <code>String</code> instance representing JID's
	 * new resource part.
	 *
	 * @return a new instance of the <code>JID</code> class with a new resource
	 * part.
	 *
	 */
	public JID copyWithResourceNS(String resource) {
		return jidInstanceNS(bareJid, resource);
	}

	/**
	 * The method returns a copy of the <code>JID</code> instance with removed
	 * resource part. The result is similar to the <code>BareJID</code> instance,
	 * however there are APIs which require <code>JID</code> object to use.
	 *
	 * @return a new instance of the <code>JID</code> class with removed resource
	 * part.
	 */
	public JID copyWithoutResource() {
		return new JID(bareJid, null);
	}

	/**
	 * Method compares whether this <code>JID</code> instance represents the
	 * same user JID as the one given in parameter. It returns
	 * <code>true</code> of all: the localpart (nickname), domain part,
	 * and the resource part are the same for both objects.
	 *
	 *
	 * @param b is a <code>JID</code> object to which the instance is compared.
	 *
	 * @return a <code>boolean</code> value of <code>true</code> if both instances
	 * represent the same JID and <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object b) {
		boolean result = false;

		if (b instanceof JID) {
			JID jid = (JID) b;

			result = bareJid.equals(jid.bareJid)
							 && ((resource == jid.resource)
									 || ((resource != null) && resource.equals(jid.resource)));
		}

		return result;
	}

	//~--- get methods ----------------------------------------------------------

	/**
	 * Method returns <code>BareJID</code> instance for this JID.
	 *
	 *
	 * @return a <code>BareJID</code> instance.
	 */
	public BareJID getBareJID() {
		return bareJid;
	}

	/**
	 * Method returns a domain part of the <code>JID</code> instance.
	 *
	 *
	 * @return a domain part of the <code>JID</code> instance.
	 */
	public String getDomain() {
		return bareJid.getDomain();
	}

	/**
	 * Method a localpart (nickname) of the <code>JID</code> instance.
	 *
	 *
	 * @return a localpart (nickname) of the <code>JID</code> instance.
	 */
	public String getLocalpart() {
		return bareJid.getLocalpart();
	}

	/**
	 * Method a resource part of the <code>JID</code> instance.
	 *
	 *
	 * @return a resource part of the <code>JID</code> instance.
	 */
	public String getResource() {
		return resource;
	}

	//~--- methods --------------------------------------------------------------

	/**
	 * Method returns a hash code calculated for the <code>JID</code> instance.
	 *
	 * @return an object hash code.
	 */
	@Override
	public int hashCode() {
		return to_string.hashCode();
	}

///**
// * Method description
// *
// *
// * @param bareJid
// */
//public void setBareJID(BareJID bareJid) {
//  this.bareJid = bareJid;
//  to_string = BareJID.toString(bareJid, resource);
//}
//  /**
//   * Method sets an new resource for the <code>JID</code> instance. Due to a
//   * nature of the JID processing it is generally not recommended to change the
//   * JIDs resource part. Instead it is recommended to create a new instance with
//   * a new resource. As the <code>BareJID</code> object is shared anyway the
//   * overhead is very minimal.
//   *
//   * @param resource is a <code>String</code> instance of the new resource
//   * part set for the <code>JID</code> instance.
//   *
//   * @throws TigaseStringprepException if stringprep processing fails for the given
//   * resource parameter.
//   */
//  public final void setResource(String resource) throws TigaseStringprepException {
//    this.resource = (resource == null) ? null : BareJID.stringPrep.resourceprep(resource);
//    to_string = BareJID.toString(bareJid, resource);
//  }

	/**
	 * Method returns a <code>String</code> representation of the
	 * <code>JID</code> instance.
	 *
	 * @return a <code>String</code> representation of the <code>JID</code> instance.
	 */
	@Override
	public String toString() {
		return to_string;
	}
}


//~ Formatted in Sun Code Convention


//~ Formatted by Jindent --- http://www.jindent.com
