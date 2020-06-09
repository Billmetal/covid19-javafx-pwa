package complements;


import org.json.JSONArray;
import org.json.JSONException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GetImage {
	
	private static GetImage getImg;
	
	private GetImage() {
		
	}
	
	public static void colocaBandeira(String pais,ImageView imageView) throws JSONException, Exception {
		if(getImg == null) {
			getImg = new GetImage();
		}
		
		int selection = 0;
		
		switch(pais) {
		case "S. Korea":
			pais = "Korea";
			selection = 1;
			break;
		case "N. Korea":
			pais = "Korea";
			selection = 0;
			break;
		case "India":
			selection = 1;
			break;
		default:
			selection = 0;
			break;
	}
		
		String caminhoCodigo = "https://restcountries.eu/rest/v2/name/"+pais;
		JSONArray obj = new JSONArray(Resposta.get(caminhoCodigo));
		if(!obj.isEmpty()) {
			Image bandeira = new Image("images/"+obj.getJSONObject(selection).getString("alpha2Code").toLowerCase()+".png");
			if(bandeira != null) {
				imageView.setImage(bandeira);
				System.out.println("Bandeira carregada");
			} 
		} else {
			imageView.setImage(null);
		}
	}

}
