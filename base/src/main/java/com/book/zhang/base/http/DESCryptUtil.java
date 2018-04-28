package com.book.zhang.base.http;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class DESCryptUtil {


	private static final byte decodeMap[] = initDecodeMap();

	private static final char encodeMap[] = initEncodeMap();
	
	private static final String password = "eph20150923wgxxym,com,.*";

	protected static char encode(int i) {
		return encodeMap[i & 0x3f];
	}

	private static char[] initEncodeMap() {
		char map[] = new char[64];
		for (int i = 0; i < 26; i++)
			map[i] = (char) (65 + i);

		for (int i = 26; i < 52; i++)
			map[i] = (char) (97 + (i - 26));

		for (int i = 52; i < 62; i++)
			map[i] = (char) (48 + (i - 52));

		map[62] = '+';
		map[63] = '/';
		return map;
	}

	private static int calcLength(char buf[]) {
		int len = buf.length;
		int base64count = 0;
		int paddingCount = 0;
		int i;
		for (i = 0; i < len; i++) {
			if (buf[i] == '=')
				break;
			if (buf[i] >= '\u0100')
				return -1;
			if (decodeMap[buf[i]] != -1)
				base64count++;
		}

		for (; i < len; i++) {
			if (buf[i] == '=') {
				paddingCount++;
			} else {
				if (buf[i] >= '\u0100')
					return -1;
				if (decodeMap[buf[i]] != -1)
					return -1;
			}
		}

		if (paddingCount > 2)
			return -1;
		if ((base64count + paddingCount) % 4 != 0)
			return -1;
		else {
			return ((base64count + paddingCount) / 4) * 3 - paddingCount;
		}
	}

	private static byte[] initDecodeMap() {
		byte map[] = new byte[256];
		for (int i = 0; i < 256; i++)
			map[i] = -1;

		for (int i = 65; i <= 90; i++)
			map[i] = (byte) (i - 65);

		for (int i = 97; i <= 122; i++)
			map[i] = (byte) ((i - 97) + 26);

		for (int i = 48; i <= 57; i++)
			map[i] = (byte) ((i - 48) + 52);

		map[43] = 62;
		map[47] = 63;
		map[61] = 127;
		return map;
	}

	/**
	 * Base64加密
	 * @param input
	 * @return
	 * @Author zhengbaochuan
	 * @Create Date: 2015年9月28日 上午11:21:26
	 */
	public static String encodeBase64Binrary(byte input[]) {
		StringBuffer r = new StringBuffer((input.length * 4) / 3);

		for (int i = 0; i < input.length; i += 3) {
			switch ((input.length - i)) {
			case 1: // '\001'
				r.append(encode(input[i] >> 2));
				r.append(encode((input[i] & 0x3) << 4));
				r.append("==");
				break;

			case 2: // '\002'
				r.append(encode(input[i] >> 2));
				r.append(encode((input[i] & 0x3) << 4 | input[i + 1] >> 4 & 0xf));
				r.append(encode((input[i + 1] & 0xf) << 2));
				r.append("=");
				break;

			default:
				r.append(encode(input[i] >> 2));
				r.append(encode((input[i] & 0x3) << 4 | input[i + 1] >> 4 & 0xf));
				r.append(encode((input[i + 1] & 0xf) << 2 | input[i + 2] >> 6
						& 0x3));
				r.append(encode(input[i + 2] & 0x3f));
			}
		}

		return r.toString();
	}

	public static byte[] decodeBase64Binrary(String lexicalValue) {
		char buf[] = lexicalValue.toCharArray();
		int outlen = calcLength(buf);
		if (outlen == -1)
			return null;
		byte out[] = new byte[outlen];
		int o = 0;
		int len = buf.length;
		byte quadruplet[] = new byte[4];
		int q = 0;
		for (int i = 0; i < len; i++) {
			byte v = decodeMap[buf[i]];
			if (v != -1)
				quadruplet[q++] = v;
			if (q == 4) {
				out[o++] = (byte) (quadruplet[0] << 2 | quadruplet[1] >> 4);

				if (quadruplet[2] != 127) {
					out[o++] = (byte) (quadruplet[1] << 4 | quadruplet[2] >> 2);
				}

				if (quadruplet[3] != 127) {
					out[o++] = (byte) (quadruplet[2] << 6 | quadruplet[3]);
				}

				q = 0;
			}
		}

		if (q != 0)
			throw new IllegalStateException();
		else
			return out;
	}

	/**
	 * 解密
	 * @param key
	 * @param crypt
	 * @return
	 * @throws Exception
	 * @Author zhengbaochuan
	 * @Create Date: 2015年9月28日 上午11:22:29
	 */
	public static byte[] desDecrypt(SecretKey key, byte[] crypt)
			throws Exception {
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(crypt);
	}

	/**
	 * 加密
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 * @Author zhengbaochuan
	 * @Create Date: 2015年9月28日 上午11:22:21
	 */
	public static byte[] desEncrypt(SecretKey key, byte[] src)
			throws Exception {
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(src);
	}





	public static SecretKey genDESKey(byte[] key_byte) {


		return new SecretKeySpec(key_byte, "DESEDE");
	}

	public static String encrypt(String original, String key) throws Exception{
		SecretKey deskey = genDESKey(key.getBytes());
		byte[] encrypt = desEncrypt(deskey, original.getBytes("UTF-8"));
		return encodeBase64Binrary(encrypt);
	}

	public static String decrypt(String encrpty, String key) throws Exception{
		byte[] decrypt = decodeBase64Binrary(encrpty);
		SecretKey deskey = genDESKey(key.getBytes());
		byte[] decrByte = desDecrypt(deskey, decrypt);
		return new String(decrByte, "UTF-8");
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @method parseByte2HexStr
	 * @param buf
	 * @return
	 * @throws
	 * @since v1.0
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @method parseHexStr2Byte
	 * @param hexStr
	 * @return
	 * @throws
	 * @since v1.0
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getEncrypData
	 * @Description: 出参加密
	 * @param jsonObject
	 * @return String
	 * @throws
	 * 
	 * @Author dyp
	 * @Create Date: 2013-11-25 上午11:47:28
	 */
	public static String getEncrypData(String jsonObject) throws Exception{
		if(DesConstant.decodeData){
			jsonObject = DESCryptUtil.encrypt(jsonObject,password);	
		}
		return jsonObject;
	}

	/**
	 * 
	 * @Title: getDecodeData
	 * @Description: 入参解密
	 * @param jsonObject
	 * @return String
	 * @throws
	 * 
	 * @Author dyp
	 * @Create Date: 2013-11-25 上午11:54:48
	 */
	public static String getDecodeData(String jsonObject) throws Exception{
		if(DesConstant.decodeData){
			jsonObject = DESCryptUtil.decrypt(jsonObject.replace(" ", "+"),password);//dafy20131220dafy.com1,.* 111111111111111111111111
		}
		
		return jsonObject;
	}
	
	public static void main(String[] args) throws Exception {
		//String str="{\"paramsCode\":\"E05001\",\"paramsData\":{\"lenderLoginId\":1}}";//提现跳转
		String str="{\"paramsCode\":\"E05001\",\"paramsData\":{\"lenderLoginId\":1,\"bankNum\":\"309\",\"accountNum\":\"6228998877885588\",\"applyCashAmount\":1,\"realCashAmount\":1,\"handlingCharge\":0,\"cashWay\":\"2\"}}";//提现申请
		//String str = "{\"paramsCode\":\"F06002\",\"paramsData\":{\"lenderLoginId\":1,\"page\":1,\"size\":10}}";//提现列表
		//String str = "{\"paramsCode\":\"F06001\",\"paramsData\":{\"lenderLoginId\":1,\"page\":1,\"size\":10}}";//充值列表
		//String str = "{\"paramsCode\":\"D04001\",\"paramsData\":{\"lenderLoginId\":1,\"rechargeAmount\":1}}";//充值申请
		//String str = "{\"paramsCode\":\"D04002\",\"paramsData\":{\"lenderLoginId\":1,\"orderNum\":\"20150924-8108-175441401\",\"rechargeStatus\":\"2\"}}";//充值回调
		//String str = "{\"paramsCode\":\"B02010\",\"paramsData\":{\"lenderLoginId\":1,\"investId\":166}}";//投资记录详情
		//String str = "{\"paramsCode\":\"B02009\",\"paramsData\":{\"lenderLoginId\":1}}";//统计我的资产信息
		
		
		String eStr = getEncrypData(str);
		System.out.println("estr = "+eStr);
//		String dStr = getDecodeData("0/GErHNNImdaZsOYUy5L8eAZ7rhmi00vPO9l/NFXXwGfzHdSBbCeIimmoaAM/M+hIqD2H9tD7UVq8zrVZ2+Hdw==");
//		System.out.println("dstr ===== "+dStr);				
	}

}
