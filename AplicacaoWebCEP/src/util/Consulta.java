package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Consulta {

	public static String consultarCEP(String cep) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(
					"https://viacep.com.br/ws/" + cep + "/json").openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			StringBuilder result = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
			}
			return result.toString();
		} catch (IOException ex) {
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
