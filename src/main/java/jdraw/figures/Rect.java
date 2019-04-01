/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.*;
import jdraw.std.ConcreteListener;
import jdraw.std.StdDrawModel;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */


public final class Rect implements Figure{
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private final Rectangle rectangle;
        // MR use the FigureListener class here instead of your Concrete one
	public LinkedList<ConcreteListener> listeners = new LinkedList<ConcreteListener>();
	
	public StdDrawModel model;
	
	
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		rectangle = new Rectangle(x, y, w, h);
		//model = new DrawModelEvent.DrawModel()
		//model.addFigure();
		
	}
	

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		rectangle.setFrameFromDiagonal(origin, corner);
		//DrawModelEvent e = new DrawModelEvent(this.model, this, DrawModelEvent.Type.FIGURE_CHANGED);
		FigureEvent fe = new FigureEvent(this); 
		for (ConcreteListener i : listeners) {
			i.figureChanged(fe);
		}
	
	}

	@Override
	public void move(int dx, int dy) {
		rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);		
		//DrawModelEvent e = new DrawModelEvent(this.model, this, DrawModelEvent.Type.FIGURE_CHANGED);
                // MR the logic AND below should be an OR
                // otherwise it is not possible to only move the figure vertically or horizontally
		if (dx!=0 && dy!=0) {
			FigureEvent fe = new FigureEvent(this); // TODO notification of change
			for (ConcreteListener i : listeners) {
			i.figureChanged(fe);
			}
			
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return rectangle.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return rectangle.getBounds();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}


	@Override
	public Figure clone() {
		return null;
	}
	
	public void addFigureListener(ConcreteListener listener) {
		listeners.add(listener);
		
		
	}

	public void removeFigureListener(ConcreteListener listener) {
		listeners.remove(listener);		
	}
	
	public void getModel(StdDrawModel sourcemodel) {
		StdDrawModel model =  sourcemodel;
	}


	@Override
	public void addFigureListener(FigureListener listener) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeFigureListener(FigureListener listener) {
		// TODO Auto-generated method stub
		
	}


		
	
}
