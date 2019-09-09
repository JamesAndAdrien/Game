package engine;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import static org.lwjgl.glfw.GLFW.*;

public class Game {
	
	public Game() {
		
	}
	
	public void input() {
		
		if (Input.getKeyPressed(GLFW_KEY_ESCAPE)) {
			
			glfwSetWindowShouldClose(main.Main.window, true);
			
		}
		
		if (Input.getKeyPressed(GLFW_KEY_A)) {
			
			System.out.println("A pressed!");
			
		}
		
		if (Input.getKeyReleased(GLFW_KEY_A)) {
			
			System.out.println("A released!");
			
		}
		
		if (Input.getButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
			
			System.out.println("Left clicked at" + Input.cursorInput().toString());
			
		}
		
		if (Input.getButtonReleased(GLFW_MOUSE_BUTTON_LEFT)) {
			
			System.out.println("Left mouse button released!");
			
		}
		
		if (Input.getButtonPressed(GLFW_MOUSE_BUTTON_RIGHT)) {
			
			System.out.println("Right clicked at " + Input.cursorInput().toString());
			
		}
		
		if (Input.getButtonReleased(GLFW_MOUSE_BUTTON_RIGHT)) {
			
			System.out.println("Right mouse button released!");
			
		}
		
	}
	
	public void update() {
		
		
		
	}
	
	public void render() {
		
		
	}
	
}
