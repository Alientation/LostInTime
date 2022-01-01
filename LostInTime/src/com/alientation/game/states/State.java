package com.alientation.game.states;

import java.awt.Graphics;

/*
 * TODO: this honestly needs to be deleted
 */
public abstract class State {
	public abstract void tick();
	public abstract void render(Graphics g);
}