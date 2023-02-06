import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinPicture extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	//----------------------------2022/12/20-------------------------------------
	/**
	 * Create the dialog.
	 */
	public WinPicture(String picPath) {
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 458, 655);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblPic = new JLabel();
			lblPic.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
			});
			contentPanel.add(lblPic, BorderLayout.CENTER);
			lblPic.setText("<html><img src='"+picPath+"' width=458 height=656></html>");
		}
	}

}
