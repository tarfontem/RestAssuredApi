package files;

public class payload {
	
	public static String AddPlace() {
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383444,\r\n"
				+ "    \"lng\": 33.427333\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"House of lords\",\r\n"
				+ "  \"phone_number\": \"(+237) 652 371 308\",\r\n"
				+ "  \"address\": \"29, side by side, cohen 02\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String UpdatePlace(String placeID,String newAddress) {
		
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\",\r\n"
				+ "    \"address\":\""+newAddress+"\",\r\n"
				+ "    \"name\": \"Lav Samba ye Ghaa mbang\",\r\n"
				+ "    \"phone_number\": \"(+237) 697 315 491\",\r\n"
				+ "    \"key\":\"qaclick123\"\r\n"
				+ "} ";
				
				
	}
	
	
	public static String CoursePrice() {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, String aisle) {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String getDeleteBook(String isbn, String aisle)
	{
		String id = isbn + aisle;
		return "{\r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ "}";
		
	}
	
	public static String getLoginData()
	{
		return "{ \"username\": \"ivanfti\", \"password\": \"Fti221987\" }";
	}
	
	public static String addComment(String comment)
	{
		return "{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
	
	public static String createIssue(String key, String summary, String description, String name) 
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \""+key+"\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \""+summary+"\",\r\n"
				+ "       \"description\": \""+description+"\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \""+name+"\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
	}
	
	public static String addCommentToIssue(String comment)
	{
		return "{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
	
	public static String updateComment(String comment)
	{
		return "{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}

}


