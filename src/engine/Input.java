package engine;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

public class Input {
	
	private static float x, y;
	
	private static int NUM_KEYS = 257;
	
	private static int NUM_BUTTONS = 3;
	
	private static ArrayList<Integer> keys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> justPressedKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> justReleasedKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> buttons = new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentButtons = new ArrayList<Integer>();
	
	private static ArrayList<Integer> justPressedButtons = new ArrayList<Integer>();
	
	private static ArrayList<Integer> justReleasedButtons = new ArrayList<Integer>();
	
	public static void keyInput() {
		
		glfwSetKeyCallback(main.Main.window, (window, key, scancode, action, mods) -> {
			
			if ( action == GLFW_PRESS )
				
				keys.add(key);
			
				justPressedKeys.add(key);
			
			if ( action == GLFW_RELEASE ) {
				
				keys.remove(new Integer (key));
				
			}
			
		});
		
	}
	
	public static Vector2f cursorInput() {
		
		glfwSetCursorPosCallback(main.Main.window, ( window, xpos, ypos) -> {
			
			x = (float) xpos;
			
			y = (float) ypos;
			
		});
		
		return new Vector2f(x,y);
		
	}
	
	public static void mouseButtonInput() {
		
		glfwSetMouseButtonCallback(main.Main.window, (window, button, action, mods) -> {
			
			if (action == GLFW_PRESS)
				
				buttons.add(button);
			
			if (action == GLFW_RELEASE )
				
				buttons.remove(new Integer (button));
			
		});
		
	}
	
	public static boolean getKeyPressed(int keyCode) {
		
		return justPressedKeys.contains(keyCode);
		
	}
	
	public static boolean getKeyReleased(int keyCode) {
		
		return justReleasedKeys.contains(keyCode);
		
	}
	
	public static boolean getButtonPressed(int keyCode) {
		
		return justPressedButtons.contains(keyCode);
		
	}
	
	public static boolean getButtonReleased(int keyCode) {
		
		return justReleasedButtons.contains(keyCode);
		
	}
	
	public static void update() {
		
		justReleasedKeys.clear();
		
		justReleasedButtons.clear();
		
		for (int i = 0; i < NUM_KEYS; i++) {
				
			if (!keys.contains(i) && currentKeys.contains(i)) {
					
				justReleasedKeys.add(i);
					
			}
				
		}
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			
			if (!buttons.contains(i) && currentButtons.contains(i)) {
					
				justReleasedButtons.add(i);
					
			}
				
		}
			
		justPressedKeys.clear();
		
		justPressedButtons.clear();
			
		for (int i = 0; i < NUM_KEYS; i++) {
				
			if (keys.contains(i) && !currentKeys.contains(i)) {
					
				justPressedKeys.add(i);
					
			}
				
		}
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			
			if (buttons.contains(i) && !currentButtons.contains(i)) {
					
				justPressedButtons.add(i);
					
			}
				
		}
			
		currentKeys.clear();
		
		currentButtons.clear();
			
		for (int i = 0; i < NUM_KEYS; i++) {
				
			if (keys.contains(i)) {
					
				currentKeys.add(i);
					
			}
				
		}
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			
			if (buttons.contains(i)) {
					
				currentButtons.add(i);
					
			}
				
		}
		
	}
	
}
