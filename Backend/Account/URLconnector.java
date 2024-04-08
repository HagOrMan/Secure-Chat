package Backend.Account;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/*
 * reference: https://www.digitalocean.com/community/tutorials/java-httpurlconnection-example-java-http-request-get-post
 */

public class URLconnector {
    //user agent is a string that is sent to the website to tell the website what browser the user is using
    private static final String USER_AGENT = "Mozilla/5.0";
    //the url that we are sending the get request to, this is the url of the server
	private static final String GET_URL = "http://100.82.74.6/8080/";
    //the url that we are sending the post request to, this is the url of the server
	private static final String POST_URL = "http://100.82.74.6/8080/";
    /*
     * GET: sends a request to the server to get information, such as a webpage or a image
     * does not change the server data, only gets the data
     */
	public static boolean sendGET(String username, String password) throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// POST
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        //send the user name and password to the server
        String postParams = "userName=" + username + "&password=" + password;
        os.write(postParams.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());
            return true;
        } else {
            System.out.println("POST request did not work.");
            return false;
        }
	}

	/*
     * POST: sends a request to the server to change the server data
     * sends the data to the server, such as a user name and password
     */
    public static void sendPOST(String username, String password) throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		String postParams = "userName=" + username + "&password=" + password;
        os.write(postParams.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
	}

    
}
