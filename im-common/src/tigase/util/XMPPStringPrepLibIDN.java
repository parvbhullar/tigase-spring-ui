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

//~--- non-JDK imports --------------------------------------------------------

import gnu.inet.encoding.Stringprep;
import gnu.inet.encoding.StringprepException;

//~--- classes ----------------------------------------------------------------

/**
 * Created: Dec 28, 2009 10:04:08 PM
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 582 $
 */
class XMPPStringPrepLibIDN implements XMPPStringPrepIfc {

	/**
	 * Method description
	 *
	 *
	 * @param domain
	 *
	 * @return
	 *
	 * @throws TigaseStringprepException
	 */
	public String nameprep(String domain) throws TigaseStringprepException {
		try {
			return Stringprep.nameprep(domain);
		} catch (StringprepException ex) {
			throw new TigaseStringprepException("nameprep unsuccessfull: ", ex);
		}
	}

	/**
	 * Method description
	 *
	 *
	 * @param localpart
	 *
	 * @return
	 *
	 * @throws TigaseStringprepException
	 */
	public String nodeprep(String localpart) throws TigaseStringprepException {
		try {
			return Stringprep.nodeprep(localpart);
		} catch (StringprepException ex) {
			throw new TigaseStringprepException("nodeprep unsuccessfull: ", ex);
		}
	}

	/**
	 * Method description
	 *
	 *
	 * @param resource
	 *
	 * @return
	 *
	 * @throws TigaseStringprepException
	 */
	public String resourceprep(String resource) throws TigaseStringprepException {
		try {
			return Stringprep.resourceprep(resource);
		} catch (StringprepException ex) {
			throw new TigaseStringprepException("resourceprep unsuccessfull: ", ex);
		}
	}
}


//~ Formatted in Sun Code Convention


//~ Formatted by Jindent --- http://www.jindent.com
