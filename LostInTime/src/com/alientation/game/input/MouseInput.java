package com.alientation.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.alientation.game.Handler;

public class MouseInput implements MouseListener, MouseMotionListener{
	private Handler handler;
	
	/*
	 * Init MouseInput
	 */
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	/*
	 * Various MouseEvents
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		handler.setMousePos(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		handler.setMousePos(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		handler.handleClick(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}