package lesson1;
import javax.swing.JOptionPane;
public class ChoosingOption {

	public static void main(String[] args) {
		int option = JOptionPane.showConfirmDialog(null, 
				"Do you want to change to first class ticket");

	

		JOptionPane.showMessageDialog(
		    null,
		    "You have chosen " + (option == JOptionPane.YES_OPTION ? "Yes" : "No")
		);
		System.exit(0);


	}

}