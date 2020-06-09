package telas;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import complements.GetImage;
import complements.Resposta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import objects.Situacao;

public class InicialController implements Initializable {
	
	@FXML
    private TextField textFieldBuscador;

    @FXML
    private ComboBox<Situacao> comboBox10Paises;

    @FXML
    private ImageView imageViewBandeira;

    @FXML
    private Label labelNomePais,labelCasosConfirmados,labelCasosDiarios,labelCasosCadaMilhao,
    			labelMortes,labelMortesDiarias,labelMortesCadaMilhao,labelRecuperados,labelEmTratamento,
    			labelEstadoCritico,labelTestesRealizados,labelTestesCadaMilhao;
	
	private String link = "https://coronavirus-19-api.herokuapp.com/countries";
	private JSONArray jsonArray;
	private List<Situacao> listaSituacao;
	private ObservableList<Situacao> observe = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// configura comboBox e o campo de busca
		configComboBox();
		// Prepara o Json com as informações
		jsonArray = recebeJson(link);
		// Prepara a lista a partir das informações
		listaSituacao = preparaLista(jsonArray);
		// preenche as informações
		preencheDados(listaSituacao.get(0));
		textFieldBuscador.setText("World");
		carregaComboBox();
	}
	
	// Método que faz a conexão com um link e retorna um JSONArray caso resposta for OK 
	private JSONArray recebeJson(String caminho) {
		JSONArray json = null;
		try {
			json = new JSONArray(Resposta.get(caminho));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return json;
	}
	
	// Método que prepara a lista com as informações 
	private List<Situacao> preparaLista(JSONArray informacoes){
		List<Situacao> lista = null;
		if(informacoes != null) {
			int tamanhoArray = informacoes.length();
			lista = new ArrayList<>();
			for(int i = 0; i < tamanhoArray; i++) {
				JSONObject obj = informacoes.getJSONObject(i);
				Situacao paisInfos = new Situacao();
				paisInfos.setPais(obj.getString("country"));
				paisInfos.setCasosConfirmados(obj.getLong("cases"));
				paisInfos.setCasosDiarios(obj.getLong("todayCases"));
				paisInfos.setMortes(obj.getLong("deaths"));
				paisInfos.setMortesDiarias(obj.getLong("todayDeaths"));
				if(obj.isNull("recovered")) {
					paisInfos.setRecuperados(0);
				} else {
					paisInfos.setRecuperados((obj.getLong("recovered")));
				}
				if(obj.isNull("active")) {
					paisInfos.setEmTratamento(0);
				} else {
					paisInfos.setEmTratamento(obj.getLong("active"));
				}
				paisInfos.setEstadoCritico(obj.getLong("critical"));
				paisInfos.setCasosPorUmMilhao(obj.getLong("casesPerOneMillion"));
				paisInfos.setMortesPorUmMilhao(obj.getLong("deathsPerOneMillion"));
				paisInfos.setTestesFeitos(obj.getLong("totalTests"));
				paisInfos.setTestesPorUmMilhao(obj.getLong("testsPerOneMillion"));
				lista.add(paisInfos);
			}
		}
		return lista;
	}
	
	// Método que carrega os dados de um País nos campos 
	private void preencheDados(Situacao infos){
		NumberFormat numFormat = NumberFormat.getInstance();
		try {
			if(!infos.getPais().contains("World")) {
				GetImage.colocaBandeira(infos.getPais(),imageViewBandeira);
			} else {
				imageViewBandeira.setImage(null);
			}
			labelNomePais.setText(infos.getPais());
			labelCasosConfirmados.setText(numFormat.format(infos.getCasosConfirmados()));
			labelCasosDiarios.setText(numFormat.format(infos.getCasosDiarios()));
			labelCasosCadaMilhao.setText(numFormat.format(infos.getCasosPorUmMilhao()));
			labelMortes.setText(numFormat.format(infos.getMortes()));
			labelMortesDiarias.setText(numFormat.format(infos.getMortesDiarias()));
			labelMortesCadaMilhao.setText(numFormat.format(infos.getMortesPorUmMilhao()));
			labelRecuperados.setText(numFormat.format(infos.getRecuperados()));
			labelEmTratamento.setText(numFormat.format(infos.getEmTratamento()));
			labelEstadoCritico.setText(numFormat.format(infos.getEstadoCritico()));
			labelTestesRealizados.setText(numFormat.format(infos.getTestesFeitos()));
			labelTestesCadaMilhao.setText(numFormat.format(infos.getTestesPorUmMilhao()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Método que carrega informações no comboBox
	private void carregaComboBox() {
		observe.clear();
		observe.addAll(listaSituacao);
		comboBox10Paises.setItems(observe);
	}
	
	// Método que configura a ação do comboBox
	private void configComboBox() {
		comboBox10Paises.setCellFactory(new Callback<ListView<Situacao>, ListCell<Situacao>>() {
			
			@Override
			public ListCell<Situacao> call(ListView<Situacao> param) {
				ListCell<Situacao> cell = new ListCell<Situacao>() {
					@Override
					protected void updateItem(Situacao item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
							setGraphic(null);
						} else {
							setText(item.getPais());
						}
					}
				};
				return cell;
			}
		});
		
		comboBox10Paises.setConverter(new StringConverter<Situacao>() {
			
			@Override
			public String toString(Situacao object) {
				String nome = "";
				if(object != null) {
					nome = object.getPais();
				}
				return nome;
			}
			
			@Override
			public Situacao fromString(String string) {				
				return comboBox10Paises.getValue();
			}
		});
		
		comboBox10Paises.valueProperty().addListener(new ChangeListener<Situacao>() {

			@Override
			public void changed(ObservableValue<? extends Situacao> observable, Situacao oldValue, Situacao newValue) {
				if(newValue != null) {
					preencheDados(newValue);
					textFieldBuscador.setText("");
				}
			}
		});
	}
	
	// Ação do campo de busca do país digitado
	@FXML
	private void buscaPais() {
		String digitado = textFieldBuscador.getText().toLowerCase();
		if(!digitado.isEmpty()) {
			for(Situacao sit : listaSituacao) {
				if(sit.getPais().toLowerCase().startsWith(digitado) || sit.getPais().toLowerCase().equals(digitado)) {
					preencheDados(sit);
					textFieldBuscador.setText("");
					comboBox10Paises.setValue(null);
					break;
				}
			}
		}
	}
}
