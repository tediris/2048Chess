package input;

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

/**
 * Makes handling input a lot simpler
 */
public class InputHandler implements KeyListener
{
	private boolean[] keys = new boolean[256];
	private boolean[] keysDown = new boolean[256];
	private Stack<Integer> keysPressed = new Stack<Integer>();

	/**
	* Assigns the newly created InputHandler to a Component
	* @param c Component to get input from
	*/
	public InputHandler(Component c) {
		c.addKeyListener(this);
	}

	public void refresh() {
		while (!keysPressed.empty()) {
			keysDown[keysPressed.pop()] = false;
		}
	}

	/**
	* Checks whether a specific key is down
	* @param keyCode The key to check
	* @return Whether the key is pressed or not
	*/
	public boolean isKeyDown(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keysDown[keyCode];
		}

		return false;
	}

	/**
	* Checks whether a specific key is held
	* @param keyCode The key to check
	* @return Whether the key is held or not
	*/
	public boolean isKeyPressed(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}

		return false;
	}

	/**
	* Called when a key is pressed while the component is focused
	* @param e KeyEvent sent by the component
	*/
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			if (!keys[e.getKeyCode()]) {
				keysDown[e.getKeyCode()] = true;
				keysPressed.push(e.getKeyCode());
			}
			keys[e.getKeyCode()] = true;
		}
	}

	/**
	* Called when a key is released while the component is focused
	* @param e KeyEvent sent by the component
	*/
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
	}

	/**
	* Not used
	*/
	public void keyTyped(KeyEvent e){}
}
