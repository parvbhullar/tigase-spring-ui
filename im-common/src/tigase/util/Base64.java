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

/**
 * Describe class Base64 here.
 *
 *
 * Created: Thu Dec 14 18:28:08 2006
 *
 * @author <a href="mailto:artur.hefczyc@tigase.org">Artur Hefczyc</a>
 * @version $Rev: 428 $
 */
public class Base64 {

	private static final char toBase64[] = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};

	private static final byte fromBase64[] = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
		55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
		5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
		24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
	};

	private Base64() {}

	public static byte[] decode(String input) {
		int groups = input.length() / 4;
		int pads = 0;
		if (input.endsWith("=")) {
			++pads;
			if (input.charAt(input.length()-2) == '=') {
				++pads;
			} // end of if (input.charAt(input.length()-2) == '=')
		}
		byte[] result = new byte[groups * 3 - pads];
		int incnt = 0, outcnt = 0;
		groups = (pads > 0 ? groups - 1 : groups);
		for (int i = 0; i < groups; i++) {
			byte b1 = fromBase64[input.charAt(incnt++)];
			byte b2 = fromBase64[input.charAt(incnt++)];
			byte b3 = fromBase64[input.charAt(incnt++)];
			byte b4 = fromBase64[input.charAt(incnt++)];
			result[outcnt++] = (byte)((b1 << 2) | (b2 >> 4));
			result[outcnt++] = (byte)((b2 << 4) | (b3 >> 2));
			result[outcnt++] = (byte)((b3 << 6) | b4);
		} // end of for (int i = 0; i < groups; i++)
		if (pads > 0) {
			byte b1 = fromBase64[input.charAt(incnt++)];
			byte b2 = fromBase64[input.charAt(incnt++)];
			result[outcnt++] = (byte)((b1 << 2) | (b2 >> 4));
			if (pads == 1) {
				byte b3 = fromBase64[input.charAt(incnt++)];
				result[outcnt++] = (byte)((b2 << 4) | (b3 >> 2));
			} // end of if (pads == 1)
		} // end of if (pads > 0)
		return result;
	}

	public static String encode(byte[] input) {
		int groups = input.length / 3;
		int pads = (groups * 3 + 3 - input.length) % 3;
		StringBuilder result =
			new StringBuilder(groups * 4 + (pads > 0 ? 1 : 0));
		for (int i = 0; i < groups; i++) {
			int c1 = (input[i*3] & 0xff) >> 2;
			int c2 = (((input[i*3] & 0xff) << 4) & 0x3f)
				| ((input[i*3+1] & 0xff) >> 4);
			int c3 = (((input[i*3+1] & 0xff) << 2) & 0x3f )
				| ((input[i*3+2] & 0xff) >> 6);
			int c4 = input[i*3+2] & 0x3f;
			result.append(toBase64[c1]);
			result.append(toBase64[c2]);
			result.append(toBase64[c3]);
			result.append(toBase64[c4]);
		} // end of for (int i = 0; i < groups; i++)
		switch (pads) {
		case 1: {
			int c1 = (input[groups*3] & 0xff) >> 2;
			int c2 = (((input[groups*3] & 0xff) << 4) & 0x3f)
				| ((input[groups*3+1] & 0xff) >> 4);
			int c3 = ((input[groups*3+1] & 0xff) << 2) & 0x3f;
			result.append(toBase64[c1]);
			result.append(toBase64[c2]);
			result.append(toBase64[c3]);
			result.append('=');
			break;
		}
		case 2: {
			int c1 = (input[groups*3] & 0xff) >> 2;
			int c2 = (((input[groups*3] & 0xff) << 4) & 0x3f);
			result.append(toBase64[c1]);
			result.append(toBase64[c2]);
			result.append("==");
			break;
		}
		default:
			break;
		} // end of switch (pads)
		return result.toString();
	}

	/**
	 * Describe <code>main</code> method here.
	 *
	 * @param args a <code>String[]</code> value
	 */
	public static void main(final String[] args) {

		if (args[0].equals("-decode")) {
			System.out.println(new String(decode(args[1])));
		} // end of if (args[0].equals("-decode"))
		if (args[0].equals("-encode")) {
			System.out.println(encode(args[1].getBytes()));
		} // end of if (args[0].equals("-encode"))
	}


} // Base64