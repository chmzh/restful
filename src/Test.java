import java.util.List;

import com.google.gson.Gson;

public class Test {
	public static void main(String[] args) {
		String data = "[{\"headers\":{\"logType\":\"PaySucInfo\",\"gameId\":\"zlsg\",\"sign\":"
    			+ "\"c070f3eef781bf5ffa6158aa73df9cd1\",\"timestamp\":1457663577104},\"body\":"
    			+ "\"s1,orderid1457663577,1,1,1,abc,13.0,RMB,130,alipay,qq,123,10010,\u89d2\u8272\u540d,\u5e10\u53f7\u540d,1457663577\"}]";
		
		Gson gson = new Gson();
		//DvJson jsons =gson.fromJson(data, DvJson.class);
		
		DvJson[] arr = gson.fromJson(data, DvJson[].class);
		
		
		System.out.println(arr[0].getHeaders().getGameId());
	}
}
