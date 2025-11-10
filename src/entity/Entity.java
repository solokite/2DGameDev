package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed, walkSpeed, runSpeed;
	
	public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, idle1, idle2, idle3, idle4;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
