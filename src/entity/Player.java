package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * 
 */
public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH ) {
		
		this.gp = gp;
		this.keyH = keyH;		
		
		setDefaultValues();
		getPlayerImage();
		
		screenX = gp.screenWidth / 2 - (gp.finalSize / 2);
		screenY = gp.screenHeight / 2 - (gp.finalSize / 2);
	}
	public void setDefaultValues() {
		
		worldX = gp.finalSize * 23;
		worldY = gp.finalSize * 21;
		walkSpeed = 3;
		runSpeed = 5;
		direction = "idle";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle4.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle4.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle4.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle4.png"));
			
			idle1 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle1.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle2.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle3.png"));
			idle4 = ImageIO.read(getClass().getResourceAsStream("/player/girl1-idle4.png"));

			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
	    boolean moving = false;
	    
	    if (keyH.shiftPressed) {
	    	speed = runSpeed;
	    } else {
	    	speed = walkSpeed;
	    }
	    
	    if (keyH.upPressed) {
	        direction = "up";
	        worldY -= speed;
	        moving = true;
	    } else if (keyH.downPressed) {
	        direction = "down";
	        worldY += speed;
	        moving = true;
	    } else if (keyH.leftPressed) {
	        direction = "left";
	        worldX -= speed;
	        moving = true;
	    } else if (keyH.rightPressed) {
	        direction = "right";
	        worldX += speed;
	        moving = true;
	    }

	    if (moving) {
	        spriteCounter++;
	        int frameSpeed = keyH.shiftPressed ? 8 : 12;
	        if (spriteCounter > frameSpeed) {
	            spriteNum++;
	            if (spriteNum > 4) spriteNum = 1;
	            spriteCounter = 0;
	        }
	    } else {
	       
	        if (!direction.equals("idle")) {
	            direction = "idle"; // Switch to idle animation state
	            spriteNum = 1;
	            spriteCounter = 0;
	        }
	        spriteCounter++;
	        if (spriteCounter > 20) { // slower animation for idle
	            spriteNum++;
	            if (spriteNum > 4) spriteNum = 1;
	            spriteCounter = 0;
	        }
	    }
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.finalSize, gp.finalSize);
		
		BufferedImage image = null; 
		
		boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
		
		if (moving) {
			switch(direction) {
			case "up":
				if (spriteNum == 1) image = up1;
				if (spriteNum == 2) image = up2;
				if (spriteNum == 3) image = up3;
				if (spriteNum == 4) image = up4;
				break;
			case "down":
				if (spriteNum == 1) image = down1;
				if (spriteNum == 2) image = down2;
				if (spriteNum == 3) image = down3;
				if (spriteNum == 4) image = down4;
				break;
			case "left":
				if (spriteNum == 1) image = left1;
				if (spriteNum == 2) image = left2;
				if (spriteNum == 3) image = left3;
				if (spriteNum == 4) image = left4;
				break;
			case "right":
				if (spriteNum == 1) image = right1;
				if (spriteNum == 2) image = right2;
				if (spriteNum == 3) image = right3;
				if (spriteNum == 4) image = right4;
				break;
			case "idle":
		        if (spriteNum == 1) image = idle1;
		        if (spriteNum == 2) image = idle2;
		        if (spriteNum == 3) image = idle3;
		        if (spriteNum == 4) image = idle4;
		        break;
			}
		} else {
			if (spriteNum == 1) image = idle1;
			if (spriteNum == 2) image = idle2;
			if (spriteNum == 3) image = idle3;
			if (spriteNum == 4) image = idle4;
		}
		
		
		g2.drawImage(image, screenX, screenY, gp.finalSize, gp.finalSize, null);
	}
}
