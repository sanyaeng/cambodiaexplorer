import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class WebPurifyTest {

	private HttpClient httpClient = null;

	@Test
	public void test() {
		this.httpClient = new DefaultHttpClient();

		String key = "db01cebc0ba5835eebc3ea9be4ffd3f7";

		String urlVerify = "https://api1.webpurify.com/services/rest/moderate?method=webpurify.live.imgcheck&api_key="
				+ key
				+ "&imgurl=http://img.allvoices.com/thumbs/event/609/480/34172616-violance-iran.jpg";

		try {
			HttpEntity entity = getEntity(urlVerify);
			if (entity != null) {
				InputStream instream = entity.getContent();
				ByteArrayOutputStream buf = new ByteArrayOutputStream();
				int result = instream.read();
				while (result != -1) {
					byte b = (byte) result;
					buf.write(b);
					result = instream.read();
				}
				// searchResponse = JsonParser.parseItems(buf.toString());
				System.out.println(buf.toString());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fail("Not yet implemented");
	}

	private HttpEntity getEntity(String url) throws ClientProtocolException,
			IOException {
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpget);
		response.addHeader("Content-Type", "application/xml");
		HttpEntity entity = response.getEntity();
		return entity;

	}

}
