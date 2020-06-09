package complements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Resposta {
	
	private static Resposta resp;
	
	private Resposta() {
		
	}
	
	// Método que recebe a resposta do link fornecido e retorna uma String
	public static String get(String link) throws Exception {
		if(resp == null) {
			resp = new Resposta();
		}
		
		StringBuffer response = null;
		URL way = new URL(link);
		HttpURLConnection con = (HttpURLConnection) way.openConnection();
		int responseCode = con.getResponseCode();
		System.out.println("Response Code : "+responseCode);
		if(responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String linha;
			response = new StringBuffer();
			while((linha = in.readLine()) != null) {
				response.append(linha);
			}
			in.close(); con.disconnect();
			System.out.println(response.toString());
		} else {
			// Solta um aviso de que não foi possível receber informações por qualquer motivo
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Corona Virus-Covid19 no Mundo !"); alert.setHeaderText("Aviso !");
			alert.setContentText("Não foi possível receber informações !!");
			alert.show();
			con.disconnect();
			return null;
		}
		
		return response.toString();
	}

}
