import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by hagai_lvi on 6/5/15.
 */
public class ForumRestClient {

	private Client client = Client.create();
	private final static String PREFIX = "http://%s:%d/forum-system/gui";
	private final static String FORUM_PATH = PREFIX + "/forum/%s";
	private String hostName;
	private int port;

	public ForumRestClient(String serverHostName, int port){
		this.hostName = serverHostName;
		this.port = port;
	}

	public static void main(String[] args) {

		ForumRestClient client= new ForumRestClient("localhost", 8080);
		System.out.println(client.getForum("A"));
//		try {
//
//			Client client = Client.create();
//
//			WebResource webResource = client
//					.resource("http://localhost:8080/forum-system/gui/pojo");
//
//			ClientResponse response = webResource.accept("application/json")
//					.get(ClientResponse.class);
//
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ response.getStatus());
//			}
//
//			String output = response.getEntity(String.class);
//
//			System.out.println("Output from Server .... \n");
//			System.out.println(output);
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}

	}

	public String getForum(String forumID){
		WebResource webResource = client.resource(String.format(FORUM_PATH, hostName, port, forumID));
		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("An error occured, response status is " + response.getStatus()); //TODO
		}
		return response.getEntity(String.class);
	}

}
