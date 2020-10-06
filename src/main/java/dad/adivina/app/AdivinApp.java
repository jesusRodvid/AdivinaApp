package dad.adivina.app;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
public class AdivinApp extends Application{
	
	//texto
	private TextField number;
	
	//label
	private Label label;
	
	//alerts 
	private Alert rightchoice;
	private Alert wrongchoice;
	private Alert invalidinput;
	
	//boton
	private Button checkbutton;
	//contador de intentos
	int cont = 0;
	//Generador de numero aleatorio
	int numberguess = (int) ((Math.random() * 100) + 1);

	@Override
	public void start(Stage primaryStage) throws Exception {

		//donde introduces el numero 
		number = new TextField();
		number.setPrefColumnCount(5);
		number.setPromptText("Introduce un número del 1 al 100");
		number.setMaxWidth(150);

		//botton
		checkbutton = new Button();
		checkbutton.setText("Comprobar");
		checkbutton.setDefaultButton(true);
		//expresion funcional 
		checkbutton.setOnAction(e -> oncheckbutton(e));
		
		
		
		label = new Label();
		label.setTranslateY(-80);
		label.setText("Introduce un número del 1 al 100");
		
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(number, checkbutton, label);

		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();

	}
	// metodo que comprueba que se introduce un string entre 0-9 ambos incluidos
	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}
	
	
	// metodo que genera ventana en caso de introducir un numero no valido
	public void invalidnumber() {
		invalidinput = new Alert(AlertType.ERROR);
		invalidinput.setTitle("AdivinApp");
		invalidinput.setHeaderText("Error");
		invalidinput.setContentText("El número introducido no es válido");
		invalidinput.showAndWait();
	}
	
	
	//metodo donde se estblece el comportamiento del botton
	private void oncheckbutton(ActionEvent e) {
		String inputbox = number.getText();
		if (validate(inputbox)) {
			int inputnumber = Integer.parseInt(number.getText());
			if (inputnumber > 0 && inputnumber <= 100) {
				if (numberguess == inputnumber) {
					cont++;
					rightguess(cont);
				} else {
					cont++;
					if (inputnumber < numberguess)
						fallo("mayor", inputnumber);
					else
						fallo("menor", inputnumber);
				}
			} else {
				invalidnumber();
			}
		} else {
			invalidnumber();
		}
	}
	
	// metodo que genera ventana en caso de acierto
	public void rightguess(int cont) {
		rightchoice = new Alert(AlertType.INFORMATION);
		rightchoice.setTitle("AdivinApp");
		rightchoice.setHeaderText("¡Has ganado!");
		rightchoice.setContentText("Sólo has necesitado " + cont + " intentos" + "\n\nVuelve a jugar y hazlo mejor");
		rightchoice.showAndWait();
	}
	
	// metodo que genera ventana cuando fallas
	public void fallo(String lessormore, int inputNumber) {
		wrongchoice = new Alert(AlertType.WARNING);
		wrongchoice.setTitle("AdivinApp");
		wrongchoice.setHeaderText("¡Has fallado!");
		wrongchoice.setContentText("El número a adivinar es "+lessormore+" que "+inputNumber);
		wrongchoice.showAndWait();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
