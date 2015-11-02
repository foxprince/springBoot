package cn.anthony.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/*
 * 锟斤拷锟斤拷锟斤拷锟斤拷 : 2004-3-17
 * 时锟斤拷 : 16:46:42
 *
 * 锟斤拷锟竭ｏ拷zhangjun
 */

/**
 * @author Anthony
 * 
 */
public class PhoneTools {
	public static final Set<String> mobileSet = new HashSet<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add("134");
			add("135");
			add("136");
			add("137");
			add("138");
			add("139");
			add("158");
			add("159");
		}
	};

	public static final Set<String> unicomSet = new HashSet<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add("130");
			add("131");
			add("132");
			add("133");
			add("153");
			add("156");
		}
	};
	public static Set<String> getPhonesFromString(String pstr) {
		if (pstr == null)
			return null;
		Set<String> s = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(pstr, ", ; \t\n\r\n");
		while (st.hasMoreTokens()) {
			String t = st.nextToken();
			if (isValidPhone(t))
				s.add(t);
		}
		return s;
	}
	public static final int CHINA_UNICOM = 1;

	public static final int UNKNOWN_MOBILE = 2;

	public static final int CHINA_MOBILE = -1;

	public static String remove86(String phoneNo) {
		if (phoneNo.indexOf("86") == 0)
			phoneNo = phoneNo.substring(2);
		return phoneNo;
	}
	public static String add86(String phoneNo) {
		if (phoneNo.indexOf("86") != 0)
			phoneNo = "86" + phoneNo;
		return phoneNo;
	}
	public static String getRequestHeaderStr(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String tmp = e.nextElement();
			sb.append(tmp + " : " + request.getHeader(tmp) + "\n");
		}
		return sb.toString();
	}

	public static String getRequestParamStr(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String tmp = e.nextElement();
			sb.append(tmp + " : " + request.getParameter(tmp) + "\n");
		}
		return sb.toString();
	}

	public static String getHeader(HttpServletRequest request, String name) {
		Enumeration<String> en = request.getHeaderNames();
		while (en.hasMoreElements()) {
			String key = en.nextElement();
			if (key.equalsIgnoreCase(name))
				return request.getHeader(key);
		}
		return null;
	}

	public static String getMdn(HttpServletRequest request) {
		String mdn = "13300000000";
		if (getHeader(request, "x-up-calling-line-id") != null)
			mdn = getHeader(request, "x-up-calling-line-id");
		else if (getHeader(request, "Cookie") != null) {
			String cookie = getHeader(request, "Cookie");
			try {
				int index = cookie.indexOf("x-up-calling-line-id=");
				index += 21;
				cookie = cookie.substring(index);
				int end = cookie.indexOf(";");
				mdn = cookie.substring(0, end);
			} catch (Exception e) {
			}
		}
		return remove86(mdn);
	}

	public static int whichMobile(String phone) {
		int head = Integer.parseInt(phone.substring(0, 3));
		if ((head >= 135) && (head <= 139))
			return CHINA_MOBILE;
		if ((head >= 130) && (head <= 133))
			return CHINA_UNICOM;
		else
			return UNKNOWN_MOBILE;
	}

	public static boolean isValidPhone(String mdn) {
		if(StringTools.checkNull(mdn)==null)
			return false;
		String phone = remove86(mdn);
		for (int i = 0; i < phone.length(); i++)
			if ((phone.charAt(i) < '0') || (phone.charAt(i) > '9'))
				return false;
		return true;
		/*
		if (phone.length() != 11)
			return false;
		else {
			String phoneHead = phone.substring(0, 3);
			if ((!phoneHead.equals("130")) && (!phoneHead.equals("131"))
					&& (!phoneHead.equals("132")) && (!phoneHead.equals("133"))
					&& (!phoneHead.equals("134")) && (!phoneHead.equals("135"))
					&& (!phoneHead.equals("136")) && (!phoneHead.equals("137"))
					&& (!phoneHead.equals("138")) && (!phoneHead.equals("139")))
				return false;
			else
				return true;
		}
		 */
	}

	/**
	 * @param phone
	 * @return
	 */
	public static String getAreaCode(String phone) {
		// TODO Auto-generated method stub
		// String head = phone.substring(0, 7);
		// SQLBean db = new SQLBean();
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;
		String area = "010";
		/*
		 * try { pstmt = db .prepareStatement("select district_code from
		 * sys_phone_area where phone_head=?"); pstmt.setString(1, head); rs =
		 * pstmt.executeQuery(); if (rs.next()) area = rs.getString(1);
		 * rs.close(); pstmt.close(); } catch (SQLException e) { } finally {
		 * db.close(); }
		 */
		return area;
	}

	/**
	 * @param phone
	 * @return
	 */
	public static String phoneHeadOraStr(String phone) {
		if (whichMobile(phone) == CHINA_MOBILE)
			return setOraStr(mobileSet);
		else
			return setOraStr(unicomSet);
	}

	/**
	 * @param mobileSet2
	 * @return
	 */
	private static String setOraStr(Set<String> mobileSet2) {
		StringBuffer sb = new StringBuffer("(");
		Iterator<String> it = mobileSet.iterator();
		while (it.hasNext()) {
			sb.append("'");
			sb.append(it.next());
			sb.append("'");
			if (it.hasNext())
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			RandomAccessFile rf = new RandomAccessFile("/Users/hch/tmp/q.txt", "r");
			String phone = null;
			while ((phone = rf.readLine()) != null)
				if (PhoneTools.isValidPhone(phone))
					System.out.println("success:"+phone);
				else
					System.out.println("faile:"+phone);
			rf.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static final String UNICOM_PHONE_PATTERN = "1(30|31|32|55|56|85|86)[0-9]{8}";

	public static final String TELCOME_PHONE_PATTERN = "1(33|53|80|89)[0-9]{8}";

	public static final String MOBILE_PHONE_PATTERN = "1(34|35|36|37|38|39|47|50|51|52|57|58|59|87|88)[0-9]{8}";

	public static boolean isCTCMobile(String phone) {
		Pattern p = Pattern.compile(TELCOME_PHONE_PATTERN);
		Matcher m = p.matcher(phone);
		if (m.matches())
			return true;
		return false;
	}

	public static boolean isUniMobile(String phone) {
		Pattern p = Pattern.compile(UNICOM_PHONE_PATTERN);
		Matcher m = p.matcher(phone);
		if (m.matches())
			return true;
		return false;
	}
}