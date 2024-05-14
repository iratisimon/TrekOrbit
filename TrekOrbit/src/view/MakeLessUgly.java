package view;

import java.awt.Color;
import java.awt.Font;
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
}
