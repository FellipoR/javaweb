package cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Principal {

	public static void main(String[] args) {
		try {
			
			Scanner in = new Scanner(System.in);
			String cep = in.nextLine();
			in.close();
			
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
			System.out.println(new String(dados.getBytes()));
		} catch (IOException ex) {
		}
	}
}

