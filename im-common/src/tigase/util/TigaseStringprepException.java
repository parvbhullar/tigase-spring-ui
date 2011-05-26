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
 * $Rev: 560 $
 * Last modified by $Author: kobit $
 * $Date: 2009-12-29 13:14:13 +0800 (周二, 29 十二月 2009) $
 */

package tigase.util;

/**
 *
 * @author kobit
 */
public class TigaseStringprepException extends Exception {

  private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>TigaseStringprepException</code> without detail message.
     */
	public TigaseStringprepException() {
		super();
	}


	/**
     * Constructs an instance of <code>TigaseStringprepException</code> with the specified detail message.
     * @param msg the detail message.
     */
	public TigaseStringprepException(String msg) {
		super(msg);
	}

	public TigaseStringprepException(String msg, Throwable th) {
		super(msg, th);
	}


}
