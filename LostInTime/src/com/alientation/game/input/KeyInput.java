package com.alientation.game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.alientation.game.GameState;
import com.alientation.game.Handler;

public class KeyInput extends KeyAdapter{
	/*
	 * TODO: speed is an attribute of player, not KeyInput
	 */
	private float speed;
	
	/*
	 * Init KeyInput
	 */
	public KeyInput(float speed) {
		this.speed = speed;
	}
	
	/*
	 * KeyPressed Event Handler
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (Handler.game.getGameState().equals(GameState.Game)) {
			switch(key) {
			case KeyEvent.VK_W:
				Handler.p.setwKeyHeldDown(true);
				break;
			case KeyEvent.VK_S:
				Handler.p.setsKeyHeldDown(true);
				break;
			case KeyEvent.VK_D:
				Handler.p.setdKeyHeldDown(true);
				break;
			case KeyEvent.VK_A:
				Handler.p.setaKeyHeldDown(true);
				break;
			default:
				break;
			}
		}
		/*
		if (Handler.game.getGameState().equals(GameState.Game)) {
			switch (key) {
			case KeyEvent.VK_W:
				
				if (handler.getPlayer().getVelXP() != 0) {
					handler.getPlayer().setVelYP((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXP(handler.getPlayer().getVelYP());
				} else if (handler.getPlayer().getVelXN() != 0) {
					handler.getPlayer().setVelYP((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXN(handler.getPlayer().getVelYP());
				} else
					handler.getPlayer().setVelYP(speed);
				break;
			case KeyEvent.VK_S:
				if (handler.getPlayer().getVelXP() != 0) {
					handler.getPlayer().setVelYN((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXP(handler.getPlayer().getVelYN());
				} else if (handler.getPlayer().getVelXN() != 0) {
					handler.getPlayer().setVelYN((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXN(handler.getPlayer().getVelYN());
				} else
					handler.getPlayer().setVelYN(speed);
				break;
			case KeyEvent.VK_D:
				if (handler.getPlayer().getVelYP() != 0) {
					handler.getPlayer().setVelYP((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXP(handler.getPlayer().getVelYP());
				} else if (handler.getPlayer().getVelYN() != 0) {
					handler.getPlayer().setVelYN((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXP(handler.getPlayer().getVelYN());
				} else
					handler.getPlayer().setVelXP(speed);
				break;
			case KeyEvent.VK_A:
				if (handler.getPlayer().getVelYP() != 0) {
					handler.getPlayer().setVelYP((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXN(handler.getPlayer().getVelYP());
				} else if (handler.getPlayer().getVelYN() != 0) {
					handler.getPlayer().setVelYN((float) (speed / Math.sqrt(2)));
					handler.getPlayer().setVelXN(handler.getPlayer().getVelYN());
				} else
					handler.getPlayer().setVelXN(speed);
				break;
			}
		}
		*/
	}
	
	/*
	 * KeyReleased Event Handler
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_W:
			Handler.p.setwKeyHeldDown(false);
			break;
		case KeyEvent.VK_S:
			Handler.p.setsKeyHeldDown(false);
			break;
		case KeyEvent.VK_D:
			Handler.p.setdKeyHeldDown(false);
			break;
		case KeyEvent.VK_A:
			Handler.p.setaKeyHeldDown(false);
			break;
		default:
			break;
		}
		
		/*
		switch (key) {
		case KeyEvent.VK_W:
			handler.getPlayer().setVelYP(0);
			if (handler.getPlayer().getVelXN() != speed && handler.getPlayer().getVelXN() > 0)
				handler.getPlayer().setVelXN(speed);
			if (handler.getPlayer().getVelXP() != speed && handler.getPlayer().getVelXP() > 0)
				handler.getPlayer().setVelXP(speed);
			break;
		case KeyEvent.VK_S:
			if (handler.getPlayer().getVelXN() != speed && handler.getPlayer().getVelXN() > 0)
				handler.getPlayer().setVelXN(speed);
			if (handler.getPlayer().getVelXP() != speed && handler.getPlayer().getVelXP() > 0)
				handler.getPlayer().setVelXP(speed);
			handler.getPlayer().setVelYN(0);
			break;
		case KeyEvent.VK_D:
			if (handler.getPlayer().getVelYN() != speed && handler.getPlayer().getVelYN() > 0)
				handler.getPlayer().setVelYN(speed);
			if (handler.getPlayer().getVelYP() != speed && handler.getPlayer().getVelYP() > 0)
				handler.getPlayer().setVelYP(speed);
			handler.getPlayer().setVelXP(0);
			break;
		case KeyEvent.VK_A:
			if (handler.getPlayer().getVelYN() != speed && handler.getPlayer().getVelYN() > 0)
				handler.getPlayer().setVelYN(speed);
			if (handler.getPlayer().getVelYP() != speed && handler.getPlayer().getVelYP() > 0)
				handler.getPlayer().setVelYP(speed);
			handler.getPlayer().setVelXN(0);
			break;
		}
		*/
	}
}