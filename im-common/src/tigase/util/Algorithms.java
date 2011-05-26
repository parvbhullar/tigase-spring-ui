/*
 * Tigase Jabber/XMPP Utils
 * Copyright (C) 2004-2007 "Artur Hefczyc" <artur.hefczyc@tigase.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
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
 * $Rev: 428 $
 * Last modified by $Author: kobit $
 * $Date: 2007-07-19 01:38:35 +0800 (周四, 19 七月 2007) $
 */
package tigase.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Describe class Algorithms here.
 *
 *
 * Created: Wed May  4 13:24:03 2005
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 428 $
 */
public class Algorithms {

  /**
   * Creates a new <code>Algorithms</code> instance.
   *
   */
  private Algorithms() { }

  /**
   * This method encodes data using digest algorithm described in
   * <em>JEP-0078</em> documentation.
   * As a result you have <code>String</code> containing digest data which
   * can be compared with data sent by the user to authenticate him.
   *
   * @param id a <code>String</code> value of some ID value like session ID to
   * concatenate with secret word.
   * @param secret a <code>String</code> value of a secret word shared between
   * entites.
   * @param alg a <code>String</code> value of algorithm name to use for
   * generating diffest message.
   * @return a <code>String</code> value digest message as defined.
   * @exception NoSuchAlgorithmException if an error occurs during encoding
   * digest message.
   */
  public static final String hexDigest(final String id, final String secret,
    final String alg) throws NoSuchAlgorithmException {
    return bytesToHex(digest(id, secret, alg));
  }

  public static final byte[] digest(final String id, final String secret,
    final String alg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance(alg);
    String conc = id + secret;
    md.update(conc.getBytes());
    return md.digest();
  }

	public static final String bytesToHex(final byte[] buff) {
    StringBuilder res = new StringBuilder();
    for (byte b : buff) {
      char ch = Character.forDigit((b >> 4) & 0xF, 16);
      res.append(ch);
      ch = Character.forDigit(b & 0xF, 16);
      res.append(ch);
    } // end of for (b : digest)
    return res.toString();
	}

	private static String help() {
		return
			" -id id				id used to calculate digest\n"
			+ " -pass pass			password phrase for digest calculation\n"
			+ " -alg alg			algorith to use for calculating digest\n"
			;
	}

	/**
	 * Describe <code>main</code> method here.
	 *
	 * @param args a <code>String[]</code> value
	 */
	public static void main(final String[] args) throws Exception {
		String id = null;
		String pass = null;
		String alg = "MD5";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-h")) {
				System.out.println(help());
				System.exit(0);
			} // end of if (args[i].equals("-id"))
			if (args[i].equals("-id")) {
				id = args[++i];
			} // end of if (args[i].equals("-id"))
			if (args[i].equals("-pass")) {
				pass = args[++i];
			} // end of if (args[i].equals("-id"))
			if (args[i].equals("-alg")) {
				alg = args[++i];
			} // end of if (args[i].equals("-id"))
		} // end of for (int i = 0; i < args.length; i++)
		if (id == null) {
			id = "";
		} // end of if (id == null)
		System.out.println(hexDigest(id, pass, alg));
	}

} // Algorithms