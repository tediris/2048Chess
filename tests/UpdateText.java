package tests;

import engine.*;
import entity.*;
import input.*;

public class UpdateText extends Component {
	private TextRenderer text;
	private UserInput user;
	public UpdateText(UserInput e, TextRenderer t) {
		super(e);
		user = e;
		text = t;
	}

	@Override
	public void update() {
		text.setText(user.buffer);
	}
}
