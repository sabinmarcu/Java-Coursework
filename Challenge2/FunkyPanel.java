import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial") public class FunkyPanel extends Panel implements ActionListener {

	private FunkyApplet Applet;
	private static HashMap<String,Integer> DEFAULTS = new HashMap<String,Integer>(){{
		put("shouldDraw", 0); put("R", 90); put("r", 60); put("offset", 0); put("speed", 60);
	}};
	public HashMap<String,Integer> settings = new HashMap<String,Integer>(){{ putAll(DEFAULTS); }};
	private Button
			drawButton = new Button("Draw"),
			resetButton = new Button("Reset"),
			updateButton = new Button("Update"),
			addButton = new Button("Add Object");
	private TextField
			//RField = new TextField("" + DEFAULTS.get("R")),
			//rField = new TextField("" + DEFAULTS.get("r")),
			//oField = new TextField("" + DEFAULTS.get("offset")),
			sField = new TextField("" + DEFAULTS.get("speed"));

	public FunkyPanel(GridBagLayout layout, FunkyApplet applet) {
		super(layout);
		Applet = applet;
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		add(drawButton, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		add(resetButton, c);

		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.gridx = 0;
		//c.gridy = 1;
		//c.gridwidth = 2;
		//add(RField, c);

		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.gridx = 0;
		//c.gridy = 2;
		//c.gridwidth = 2;
		//add(rField, c);

		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.gridx = 0;
		//c.gridy = 3;
		//c.gridwidth = 2;
		//add(oField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		add(sField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		add(updateButton, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		add(addButton, c);

		drawButton.addActionListener(this);
		resetButton.addActionListener(this);
		//RField.addActionListener(this);
		//rField.addActionListener(this);
		//oField.addActionListener(this);
		sField.addActionListener(this);
		updateButton.addActionListener(this);
		addButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawButton) {
			Integer orig = settings.get("shouldDraw");
			if (orig > 0) {
				orig = 0;
				drawButton.setLabel("Draw");
			}
			else {
				orig = 1;
				drawButton.setLabel("Stop Drawing");
			}
			settings.put("shouldDraw", orig);
		} else if (e.getSource() == resetButton) {
			drawButton.setLabel("Draw");
			settings.putAll(DEFAULTS);
			sField.setText("" + settings.get("speed"));
			Applet.resetObjects();
			Applet.startOver();
		} else if (e.getSource() == addButton) {
			Applet.addObject();
		} else if (e.getSource() == sField || e.getSource() == updateButton) {
			settings.put("speed", Integer.parseInt(sField.getText()));
			Applet.startOver();
		}
	}
}
