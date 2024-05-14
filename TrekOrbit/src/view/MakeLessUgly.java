package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.toedter.calendar.JCalendar;

public class MakeLessUgly {
	
	public static void showMessageDialog(String message, String title, int messageType) {
		
		// Establecer los colores de fondo para el JOptionPane
		UIManager.put("OptionPane.background", new Color(23, 17, 39));
		UIManager.put("Panel.background", new Color(23, 17, 39));

		// Establecer el color y la fuente del mensaje
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("OptionPane.messageFont", new Font("OCR A Extended", Font.BOLD, 20));

		// Mostrar el JOptionPane con el mensaje personalizado y la imagen
		JOptionPane.showMessageDialog(null, message, title, messageType);
	}

	public static void customizeCalendar(JCalendar calendar) {
		// Configuración del color de fondo del calendario
		calendar.setBackground(new Color(23, 17, 39));
		// Configuración del color de la letra del calendario
		calendar.setForeground(Color.WHITE);
		// Configuración del color de fondo del área de título
		calendar.setDecorationBackgroundColor(new Color(23, 17, 39));
		// Configuración del color de la letra del selector de año
		calendar.getYearChooser().getSpinner().setForeground(Color.WHITE);
		// Configuración del color de fondo del selector de año
		calendar.getYearChooser().getSpinner().setBackground(new Color(23, 17, 39));
		// Configuración del color de la letra del selector de mes
		calendar.getMonthChooser().getComboBox().setForeground(Color.WHITE);
		// Configuración del color de fondo del selector de mes
		calendar.getMonthChooser().getComboBox().setBackground(new Color(23, 17, 39));
		// Configuración del color de fondo del selector de día
		calendar.getDayChooser().setBackground(new Color(23, 17, 39));
		calendar.getDayChooser().getDayPanel().setBackground(new Color(23, 17, 39));
		// Configuración del color de la letra del selector de día
		calendar.getDayChooser().setForeground(Color.BLACK);
		// Ocultar el número de la semana
		calendar.setWeekOfYearVisible(false);
		// Configuración de la fuente y tamaño de la letra del calendario
		calendar.setFont(new Font("OCR A Extended", Font.BOLD, 12));
	}
	  public static Cursor setAlienCursor(Component component) {
	        // Verifica si el componente es un JButton o un JLabel
	        if (component instanceof JButton || component instanceof JLabel) {
	            // Carga la imagen del cursor Alien
	            ImageIcon cursorIcon = new ImageIcon(MakeLessUgly.class.getResource("/images/cursorAlien.png"));
	            Image cursorImage = cursorIcon.getImage();
	            
	            // Define el punto de la imagen que servirá como el punto "clickeable"
	            Point hotSpot = new Point(0, 0); // Cambiar las coordenadas si es necesario
	            
	            // Crea el cursor personalizado
	            Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, hotSpot, "Alien Cursor");
	            
	            // Asigna el cursor personalizado al componente deseado
	            component.setCursor(customCursor);
	            
	            // Devuelve el cursor creado
	            return customCursor;
	        } else {
	            // Si el componente no es un JButton ni un JLabel, no se hace ningún cambio en el cursor
	            return null;
	        }
	    }
	    
	    public static Cursor setDefaultCursor(Component component) {
	        // Carga la imagen del cursor Nave
	        ImageIcon cursorIcon = new ImageIcon(MakeLessUgly.class.getResource("/images/cursorNave.png"));
	        Image cursorImage = cursorIcon.getImage();
	        
	        // Define el punto de la imagen que servirá como el punto "clickeable"
	        Point hotSpot = new Point(0, 0); // Cambiar las coordenadas si es necesario
	        
	        // Crea el cursor personalizado
	        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, hotSpot, "Nave Cursor");
	        
	        // Asigna el cursor personalizado al componente deseado
	        component.setCursor(customCursor);
	        
	        // Devuelve el cursor creado
	        return customCursor;
	    }
}
