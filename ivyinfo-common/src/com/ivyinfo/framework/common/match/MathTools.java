package com.ivyinfo.framework.common.match;
import com.ivyinfo.framework.common.exception.IvyinfoException;
import java.math.BigDecimal;
import java.util.LinkedList;
/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class MathTools {
	//默认除法运算精度
	private static final int DEFAULT_DIV_SCALE = 10;
	//这个类不能实例化
	private MathTools() {}

   /**
     * 检测改值是不是数字型
     * @param value
     * @return
     */
    public static boolean isNumber(String value) throws IvyinfoException {
        if (value == null) {
            return false;
        }
        try {
            Double.parseDouble(value.trim());
        } catch (NumberFormatException nfe) {
            throw new IvyinfoException(value+" 不是数字型！");
        }
        return true;
    }
	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static String add(String v1, String v2) throws IvyinfoException {
		try {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.add(b2).toString();
		} catch (Exception e) {
			throw new IvyinfoException("add错误："+e.getMessage());
		}
	}

        /**
         * 数组中的数字相加
         * @param vs
         * @return
         */
	public static String add(String[] vs) throws IvyinfoException {
		if (vs == null || vs.length == 0)
			return "";
		BigDecimal result = new BigDecimal("0");
		try {
			for (int i = 0; i < vs.length; i++) {
				BigDecimal b = new BigDecimal(vs[i]);
				result = result.add(b);
			}
			return result.toString();
		} catch (Exception e) {
			throw new IvyinfoException("add错误："+e.getMessage());
		}
	}
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static String sub(String v1, String v2) throws IvyinfoException {
		try {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.subtract(b2).toString();
		} catch (Exception e) {
			throw new IvyinfoException("sub错误："+e.getMessage());
		}
	}
        /**
         * 数组之间想减
         * @param v1
         * @param vs
         * @return
         */
	public static String sub(String v1, String[] vs) throws IvyinfoException {
		if (vs == null || vs.length == 0)
			return v1;
		try {
			BigDecimal result = new BigDecimal(v1);
			for (int i = 0; i < vs.length; i++) {
				BigDecimal b = new BigDecimal(vs[i]);
				result = result.subtract(b);
			}
			return result.toString();
		} catch (Exception e) {
			throw new IvyinfoException("sub错误："+e.getMessage());
		}
	}
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static String mul(String v1, String v2) throws IvyinfoException {
		try {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.multiply(b2).toString();
		} catch (Exception e) {
			throw new IvyinfoException("mul错误："+e.getMessage());
		}
	}
        /**
         * 数组之间相乘
         * @param vs
         * @return
         */
	public static String mul(String[] vs) throws IvyinfoException {
		if (vs == null || vs.length == 0)
			return "";
		try {
			BigDecimal result = new BigDecimal("1");
			for (int i = 0; i < vs.length; i++) {
				BigDecimal b = new BigDecimal(vs[i]);
				result = result.multiply(b);
			}
			return result.toString();
		} catch (Exception e) {
			throw new IvyinfoException("mul错误："+e.getMessage());
		}
	}
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 小数点以后10位，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2) throws IvyinfoException {
		return div(v1, v2, DEFAULT_DIV_SCALE);
	}
        /**
         * 数组想除
         * @param v1
         * @param vs
         * @return
         */
	public static String div(String v1, String[] vs) throws IvyinfoException {
		return div(v1, vs, DEFAULT_DIV_SCALE);
	}
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2, int scale) throws IvyinfoException {
		try {
			BigDecimal b1 = new BigDecimal(v1);
			BigDecimal b2 = new BigDecimal(v2);
			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			throw new IvyinfoException("div错误："+e.getMessage());
		}
	}
        /**
         * 数组想除
         * @param v1
         * @param vs
         * @param scale
         * @return
         */
	public static String div(String v1, String vs[], int scale) throws IvyinfoException {
		if (vs == null || vs.length == 0)
			return round(v1, scale);
		try {
			BigDecimal result = new BigDecimal(v1);
			for (int i = 0; i < vs.length; i++) {
				BigDecimal b = new BigDecimal(vs[i]);
				result = result.divide(b, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
			}
			return round(result.toString(), scale);
		} catch (Exception e) {
			throw new IvyinfoException("div错误："+e.getMessage());
		}
	}
	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static String round(String v, int scale) throws IvyinfoException {
		if (v == null||v.equals(""))
			return "";
		try {
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			throw new IvyinfoException("round错误："+e.getMessage());
		}
	}

    /**
     * 根据传入的公式进行计算
     * @param s
     * @return
     */
    public static double JISUAN(String s) {
        LinkedList<Token> oper = new LinkedList<Token>();
        oper.addFirst(new Token('#', -1));
        LinkedList<Double> num = new LinkedList<Double>();
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                oper.addFirst(new Token('(', 0));
            }
            if (s.charAt(i) == ')') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                while (true) {
                    Token cur = oper.removeFirst();
                    if (cur.c == '(') {
                        break;
                    }
                    double d2 = num.removeFirst();
                    double d1 = num.removeFirst();
                    if (cur.c == '+') {
                        num.addFirst(d1 + d2);
                    }
                    if (cur.c == '-') {
                        num.addFirst(d1 - d2);
                    }
                    if (cur.c == '*') {
                        num.addFirst(d1 * d2);
                    }
                    if (cur.c == '/') {
                        if (d2 == 0) {
                            System.out.println("除数为0");
                        }
                        num.addFirst(d1 / d2);
                    }
                }
            }
            if (s.charAt(i) == '+') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                Token tnew = new Token('+', 1);
                while (true) {
                    Token cur = oper.removeFirst();
                    if (tnew.level > cur.level) {
                        oper.addFirst(cur);
                        oper.addFirst(tnew);
                        break;
                    } else {
                        double d2 = num.removeFirst();
                        double d1 = num.removeFirst();
                        if (cur.c == '+') {
                            num.addFirst(d1 + d2);
                        }
                        if (cur.c == '-') {
                            num.addFirst(d1 - d2);
                        }
                        if (cur.c == '*') {
                            num.addFirst(d1 * d2);
                        }
                        if (cur.c == '/') {
                            if (d2 == 0) {
                                System.out.println("除数为0");
                            }
                            num.addFirst(d1 / d2);
                        }
                    }
                }
            }
            if (s.charAt(i) == '-') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                Token tnew = new Token('-', 1);
                while (true) {
                    Token cur = oper.removeFirst();
                    if (tnew.level > cur.level) {
                        oper.addFirst(cur);
                        oper.addFirst(tnew);
                        break;
                    } else {
                        double d2 = num.removeFirst();
                        double d1 = num.removeFirst();
                        if (cur.c == '+') {
                            num.addFirst(d1 + d2);
                        }
                        if (cur.c == '-') {
                            num.addFirst(d1 - d2);
                        }
                        if (cur.c == '*') {
                            num.addFirst(d1 * d2);
                        }
                        if (cur.c == '/') {
                            if (d2 == 0) {
                                System.out.println("除数为0");
                            }
                            num.addFirst(d1 / d2);
                        }
                    }
                }

            }
            if (s.charAt(i) == '*') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                Token tnew = new Token('*', 2);
                while (true) {
                    Token cur = oper.removeFirst();
                    if (tnew.level > cur.level) {
                        oper.addFirst(cur);
                        oper.addFirst(tnew);
                        break;
                    } else {
                        double d2 = num.removeFirst();
                        double d1 = num.removeFirst();
                        if (cur.c == '+') {
                            num.addFirst(d1 + d2);
                        }
                        if (cur.c == '-') {
                            num.addFirst(d1 - d2);
                        }
                        if (cur.c == '*') {
                            num.addFirst(d1 * d2);
                        }
                        if (cur.c == '/') {
                            if (d2 == 0) {
                                System.out.println("除数为0");
                            }
                            num.addFirst(d1 / d2);
                        }
                    }
                }
            }
            if (s.charAt(i) == '/') {
                if (t.equals("") != true) {
                    num.addFirst(new Double(t));
                    t = "";
                }
                Token tnew = new Token('/', 2);
                while (true) {
                    Token cur = oper.removeFirst();
                    if (tnew.level > cur.level) {
                        oper.addFirst(cur);
                        oper.addFirst(tnew);
                        break;
                    } else {
                        double d2 = num.removeFirst();
                        double d1 = num.removeFirst();
                        if (cur.c == '+') {
                            num.addFirst(d1 + d2);
                        }
                        if (cur.c == '-') {
                            num.addFirst(d1 - d2);
                        }
                        if (cur.c == '*') {
                            num.addFirst(d1 * d2);
                        }
                        if (cur.c == '/') {
                            if (d2 == 0) {
                                System.out.println("除数为0");
                            }
                            num.addFirst(d1 / d2);
                        }
                    }
                }
            }
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.') {
                t += s.charAt(i);
            }
        }
        if (t.equals("") != true) {
            num.addFirst(new Double(t));
        }
        while (oper.size() > 1) {
            Token cur = oper.removeFirst();
            double d2 = num.removeFirst();
            double d1 = num.removeFirst();
            if (cur.c == '+') {
                num.addFirst(d1 + d2);
            }
            if (cur.c == '-') {
                num.addFirst(d1 - d2);
            }
            if (cur.c == '*') {
                num.addFirst(d1 * d2);
            }
            if (cur.c == '/') {
                if (d2 == 0) {
                    System.out.println("除数为0");
                }
                num.addFirst(d1 / d2);
            }
        }
        return num.getFirst();
    }


   public static void main(String[] args) {
        String a = "1000*5-800*0.17";
        double b = JISUAN(a);
        System.out.println(b);
        System.out.println(JISUAN("3*(2+3)/(5)-(1-2)*2"));
        System.out.println(JISUAN("3*(2+3)/(5-5)-(1-2)*2"));
        System.out.println(JISUAN("1000*5-800*0.17"));
    }
}

class Token {

    public char c;
    public int level;// 运算优先级 ：(:0 +:1 -:1 *:2 /:2 ):3

    public Token(char c, int level) {
        this.c = c;
        this.level = level;
    }

    public String toString() {
        return "" + c + "   " + level;
    }
}
