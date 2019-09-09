package main;

import engine.Game;

import engine.Input;

import engine.SpaceTime;
import engine.Vector3f;
import graphics.Mesh;
import graphics.RenderUtility;
import graphics.Renderer;
import graphics.Shader;
import graphics.Vertex;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryStack.stackPush;

import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;

import org.lwjgl.glfw.GLFWErrorCallback;

import org.lwjgl.glfw.GLFWVidMode;

import org.lwjgl.opengl.GL;

import org.lwjgl.system.MemoryStack;

public class Main implements Runnable {
	
	public boolean isRunning;
	
	public static long window;
	
	private static int WIDTH = 1000, HEIGHT = 600;
	
	private static String TITLE = "GAME!";
	
	public static final double FRAMERATE_CAP = 60.0;
	
	private Game game;
	
	public Renderer renderer;
	
	public Mesh mesh = new Mesh(new Vertex[] {
			
			new Vertex(new Vector3f(-0.5f,0.5f,0.0f)),
			
			new Vertex(new Vector3f(0.5f,0.5f,0.0f)),
			
			new Vertex(new Vector3f(0.5f,-0.5f,0.0f)),
			
			new Vertex(new Vector3f(-0.5f,-0.5f,0.0f))
			
	}, new int[] {
			
			0, 1, 2,
			
			0, 3, 2
			
	});
	
	public Shader shader;
	
	public void run() {

		init();
		
		loop();

		stop();
		
	}
	
	public void init() {
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		if ( !glfwInit() )
			
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 

		window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
		
		if ( window == NULL )
			
			throw new RuntimeException("Failed to create the GLFW window");
		
		Input.keyInput();
		
		Input.mouseButtonInput();
		
		Input.cursorInput();

		/*glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				
				glfwSetWindowShouldClose(window, true);
			
		});
		
		*/
		
		try ( MemoryStack stack = stackPush() ) {
			
			IntBuffer pWidth = stack.mallocInt(1);
			
			IntBuffer pHeight = stack.mallocInt(1); 

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(window,(vidmode.width() - pWidth.get(0)) / 2,(vidmode.height() - pHeight.get(0)) / 2);
			
		}

		glfwMakeContextCurrent(window);

		glfwSwapInterval(1);

		glfwShowWindow(window);
		
		GL.createCapabilities();
		
		RenderUtility.initGraphics();
		
		game = new Game();
		
		isRunning = true;
		
		mesh.create();
		
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		
		renderer = new Renderer(shader);
		
		shader.create();
		
	}
	
	public void loop() {
		
		int frames = 0;
		
		long frameCounter = 0;
		
		final double frameTime = 1.0/FRAMERATE_CAP;
		
		long previousTime = SpaceTime.getTime();
		
		double updateTime = 0.0d;

		while (isRunning) {
			
			boolean render = false;
			
			long currentTime = SpaceTime.getTime();
			
			long elapsedTime = currentTime - previousTime;
			
			previousTime = currentTime;
			
			updateTime += elapsedTime/(double)SpaceTime.SECOND;
			
			frameCounter += elapsedTime;
			
			while (updateTime > frameTime) {
				
				render = true;
				
				updateTime -= frameTime;
				
				SpaceTime.setDeltaTime(frameTime);
				
				Input.update();
				
				game.input();
				
				if (GLFW.glfwWindowShouldClose(window)) {
					
					stop();
					
				}
				
				if (frameCounter > SpaceTime.SECOND) {
					
					System.out.println("Frames: " + frames);
					
					frames = 0;
					
					frameCounter = 0;
					
				}
				
			}
			
			if (render) {
				
				render();
				
				frames ++;
				
			}
			
			else {
				
				try {
					
					Thread.sleep(1);
					
				} 
				catch (InterruptedException e) {

					e.printStackTrace();
				
				}
				
			}
			
		}
		
	}
	
	public void render() {
		
		//RenderUtility.clearScreen();
		
		renderer.renderMesh(mesh);

		glfwSwapBuffers(window); 

		glfwPollEvents();
		
	}
	
	public void stop() {
		
		glfwFreeCallbacks(window);
		
		glfwDestroyWindow(window);
		
		System.out.println("Window destroyed...");

		glfwTerminate();
		
		glfwSetErrorCallback(null).free();
		
		System.out.println("Exiting...");
		
		System.exit(0);
		
	}
	
	public static void main(String[] args) {
		
		new Main().run();
		
	}
	
}
