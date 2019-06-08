package cm.youss.presentation;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class HighLightListener implements DocumentListener {
	
	JTextField comp = null;
	Border defaultBorder = null;
	Border highlightBorder = BorderFactory.createLineBorder(java.awt.Color.RED);
	
	

	public HighLightListener(JTextField comp) {
		defaultBorder = comp.getBorder();
		comp.getDocument().addDocumentListener(this);
		this.maybeHighLight();
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		maybeHighLight();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		maybeHighLight();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		maybeHighLight();
	}
	
	private void maybeHighLight() {
		if(!comp.getText().isEmpty()) {
			comp.setBorder(defaultBorder);
		}
		else {
			comp.setBorder(highlightBorder);
		}
	}

}
