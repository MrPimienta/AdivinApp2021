package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Adivinapp extends Application {
	
	private int intentos=0;
	private static int num_aleatorio;
	
	private Label tituloLabel;
	private TextField numeroText;
	private Button comprobarButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		tituloLabel = new Label();
		tituloLabel.setText("Introduce un número del 1 al 100");
		tituloLabel.setAlignment(Pos.CENTER);
		tituloLabel.setLayoutX(20);
		tituloLabel.setLayoutY(20);
		
		numeroText = new TextField();
		numeroText.setAlignment(Pos.CENTER);
		numeroText.setMaxWidth(100);
		
		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setAlignment(Pos.CENTER);
		comprobarButton.setLayoutX(20);
		comprobarButton.setLayoutY(80);
		
		comprobarButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				onComprobarAction(e);
			}
		});
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(tituloLabel, numeroText, comprobarButton);
		
		Scene escena = new Scene(root,320,250);
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void onComprobarAction(ActionEvent e) {
		//System.out.println("Esto es una prueba");
		
		intentos=intentos+1;
		
		try {
			int numero = Integer.parseInt(numeroText.getText());
			if(numero<num_aleatorio)
				fallo(numero);
			else if(numero>num_aleatorio)
				fallo(numero);
			else {
				acertado();
			}
		}catch (NumberFormatException err) {
			error();
		}
		
	}

	public static void main(String[] args) {
		num_aleatorio = (int) (Math.random() * (100-0+1) + 0);
		launch(args);

	}
	
	private void fallo(int numero) {
		String mensaje;
		if(numero<num_aleatorio)
			mensaje="El número a adivinar es MAYOR que "+numero;
		else
			mensaje="El número a adivinar es MENOR que "+numero;
		
		Alert alerta_fallo = new Alert(AlertType.WARNING);
		alerta_fallo.setTitle("AdivinApp");
		alerta_fallo.setHeaderText("¡HAS FALLADO!");
		alerta_fallo.setContentText(mensaje+"\nVuelve a intentarlo.");
		alerta_fallo.showAndWait();
	}
	
	private void acertado() {
		Alert alerta_acierto = new Alert(AlertType.INFORMATION); //VENTANA EMERGENTE DE ALERTA DE TIPO INFORMATION (AZUL)
		alerta_acierto.setTitle("AdivinApp");				 //TITULO DE LA VENTANA
		alerta_acierto.setHeaderText("¡HAS GANADO!");			 //CONTENIDO DEL HEADER
		alerta_acierto.setContentText("Solo has necesitado "+intentos+" intentos"); //TEXTO DESCRIPTIVO DEL ERROR
		alerta_acierto.showAndWait();
	}
	
	private void error() {
		Alert alerta_error = new Alert(AlertType.ERROR); //VENTANA EMERGENTE DE ALERTA DE TIPO ERROR (ROJA)
		alerta_error.setTitle("AdivinApp");				 //TITULO DE LA VENTANA
		alerta_error.setHeaderText("ERROR");			 //CONTENIDO DEL HEADER
		alerta_error.setContentText("El número introducido no es válido"); //TEXTO DESCRIPTIVO DEL ERROR
		alerta_error.showAndWait();
	}

}
