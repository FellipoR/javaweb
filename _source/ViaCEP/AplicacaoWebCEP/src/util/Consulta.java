package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Consulta {

	public static String consultarCEP(String cep){
		try {
			String url = "https://viacep.com.br/ws/" + cep + "/json";
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			if (con.getResponseCode() != 200) {
				System.out.println("Problema Identificado " + con.getResponseCode() + url);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			String dados = "";
			String linha;
			
			while ((linha = br.readLine()) != null) {
				dados += linha;
			}
			con.disconnect();
			return new String(dados.getBytes());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
