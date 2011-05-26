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

package tigase.util;

//~--- interfaces -------------------------------------------------------------

/**
 * Interface defining stringprep processor API. According to RFC-3920 all JID's parts have
 * to be stringprep processed, each part should go through different algorithm. The interface
 * and XMPPStringPrepFactory allow for pluggable stringprep processor implementation.
 * See the factory documentation for more details.
 * Created: Dec 28, 2009 9:57:43 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 582 $
 */
public interface XMPPStringPrepIfc {

	/**
	 * JID's domain part stringprep processing. In RFC-3920 this is called nameprep processing.
	 * @param domain a <code>String</code> value of the JID's domain part.
	 * @return nameprep processed JID's doman part.
	 * @throws TigaseStringprepException if stringprep exception occurs.
	 */
	String nameprep(String domain) throws TigaseStringprepException;

	/**
	 * JID's localpart (or nick name) stringprep processing. In RFC-3920 this is called nodeprep
	 * processing.
	 * @param localpart a <code>String</code> value of the JID's localpart.
	 * @return nodeprep processed JID's localpart.
	 * @throws TigaseStringprepException if stringprep exception occurs.
	 */
	String nodeprep(String localpart) throws TigaseStringprepException;

	/**
	 * JID's resource stringprep proessing. In RFC-3920 this is called resourceprep processing.
	 * @param resource a <code>String</code> value of the JID's resource part.
	 * @return resourceprep processed JID's resource part.
	 * @throws TigaseStringprepException if stringprep exception occurs.
	 */
	String resourceprep(String resource) throws TigaseStringprepException;
}


//~ Formatted in Sun Code Convention


//~ Formatted by Jindent --- http://www.jindent.com
