package controles.controlesVistasFX;

import accesoDatos.ConexionBD;
import accesoDatos.DAOVoluntarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import manajador_ventanas.WindowsControlador;
import objetosNegocio.Voluntario;
import utilities.AlertBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ControlVista_Login implements Initializable{

    private WindowsControlador wc = new WindowsControlador();

    //Media
   @FXML
   private MediaView mediaView;

   //Texto
    @FXML
    private JFXTextField textoUsername;

    //
    @FXML
    private JFXPasswordField contrasena;

    //Botones
    @FXML
    private JFXButton botonInicioSesion;

    @FXML
    private JFXButton botonOlvidarContraseña;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media("file:///C:/Users/robPC/Desktop/Proyectos/PSI_AmorConPatas/ClienteFX_ACP_IntelliJ/src/resources/doggie.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        player.setAutoPlay(true);
    }

    

    public void handleInicioSesion(ActionEvent event){
        if(textoUsername.getText().isEmpty()){
            AlertBox.display("Escribe tu nombre de usuario","No hay usuario","/resources/Icons/warning.png");
            return;
        }

        if(contrasena.getText().isEmpty()){
            AlertBox.display("Escribe tu contraseña","No hay contraseña","/resources/Icons/warning.png");
            return;
        }

        String username = textoUsername.getText();
        String password = contrasena.getText();

        Object [] result = wc.validarLogin(username,password);

        if(!(Boolean)result[0]){
            AlertBox.display("La contraseña o el usuario no son correctos","Intentalo de nuevo","/resources/Icons/warning.png");
            return;
        }

        DAOVoluntarios voluntarios = new DAOVoluntarios();

        Connection bd = ConexionBD.getConection();

        String query = "SELECT * FROM Usuario WHERE username ='"+username+"' AND password='"+password+"'";

        try{
            PreparedStatement ps = bd.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int idVoluntario = 0;
            while(rs.next()){
                idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
            }

            System.out.println(idVoluntario);
            Voluntario voluntario = voluntarios.queryGetVoluntarioPorID(idVoluntario);
            wc.voluntarioActivo = voluntario;


            Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/adopcion/vistaAdopcion.fxml"));
            Scene  homePageScene = new Scene(homePageParent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(homePageScene);
            app_stage.show();


        }catch(Exception e){
            System.out.println(e.getMessage());
        }




    }

}
